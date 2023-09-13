package com.example.store_ht.Service.impl;

import com.example.store_ht.Entity.User;
import com.example.store_ht.Mapper.UserMapper;
import com.example.store_ht.Service.IUserService;
import com.example.store_ht.Service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
//        user.getUsername 通过user参数来获取传递过来的username
//        userMapper.findByUsername(user.getUsername)判断用户是否被注册过
        if (userMapper.findByUsername(user.getUsername()) != null) {
//            抛出异常
            throw new UsernameDuplicatedException("用户名被占用");
        }
//        密码加密处理实现：md5算法的形式：
//        （串+password+串）-------md5算法进行加密，连续加密三次
//        ->（盐值+password +盐值） -------盐值就是一个随机的字符串
        String oldPassword = user.getPassword();
        String salt = UUID.randomUUID().toString().toUpperCase();  //大写
        String md5Password = getMD5Password(oldPassword, salt);
//加密后重新补全设置到user对象中
        user.setPassword(md5Password);
//        添加设置盐值
        user.setSalt(salt);

//        补全数据:is_delete 设置成0
        user.setIsDelete(0);
//        补全数据:4个日志字段信息
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);


//        执行注册业务功能的实现(rows=1)
        Integer rows = userMapper.insert(user);
        if (rows != 1) {
            throw new InsertException("用户注册过程中产生了未知异常");
        }
    }

    @Override
    public User login(String username, String password) {
//        根据用户名字来查询用户的数据是否存在，如果不在则抛出异常
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("用户数据不存在");

        }
//        检测用户密码是否匹配
//        1.先获取到数据库中的加密之后的密码
        String oldPassword = user.getPassword();
//        2.和用户的传递过来的密码进行比较
//        2.1 先获取盐值：上一次注册时所自动生成的盐值
        String salt = user.getSalt();
//          2.2 将用户的密码按照相同的md5算法进行加密
        String newMd5Password = getMD5Password(password, salt);
//        3.将密码进行比较
        if (!newMd5Password.equals(oldPassword)) {
            throw new PasswordNotMatchException("用户密码错误");
        }
//        判断is——delete字段的值是否为1
        if (user.getIsDelete() != 0) {
            throw new UserNotFoundException("用户数据不存在");
        }
//        调用mapper的接口来查询用户,提升系统性能，封装需要使用的三个信息
        User user1 = new User();
        user1.setUid(user.getUid());
        user1.setUsername(user.getUsername());
        user1.setAvatar(user.getAvatar());

//  只需要 id name avatar
        return user1;
    }

    //    修改密码
    @Override
    public void updatePassword(String oldPassword, Integer uid, String newPassword) {
        User user = userMapper.findByUid(uid);
        if (user == null || user.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据没有找到");
        }
//        原始密码和数据库中密码进行比较
        String oldMd5Password = user.getPassword();
        if (!getMD5Password(oldPassword, user.getSalt()).equals(oldMd5Password)) {
            throw new PasswordNotMatchException("源密码错误！");
        }
//        将新的密码设置到数据库中,将新的密码进行加密，再去更新
        String newMd5Password = getMD5Password(newPassword, user.getSalt());
        Integer rows = userMapper.updatePasswordByUid(newMd5Password, uid, user.getUsername(), new Date());
        if (rows != 1) {
            throw new UpdateException("更新数据产生未知异常");
        }
    }


    @Override
    public User getByUid(Integer uid) {
        User res = userMapper.findByUid(uid);
        if (res == null || res.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        User user = new User();
        user.setUsername(res.getUsername());
        user.setPhone(res.getPhone());
        user.setEmail(res.getEmail());
        user.setGender(res.getGender());
        return user;
    }

    //修改用户数据
    @Override
    public void updateUser(Integer uid, String username, User user) {
        User res = userMapper.findByUid(uid);
        if (res == null || res.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());

        Integer rows = userMapper.updateUser(user);
        if (rows != 1) {
            throw new UpdateException("更新数据时产生未知异常");
        }

    }

    //    更新用户头像
    @Override
    public void updateAvatar(Integer uid, String username, String avatar) {
        User user = userMapper.findByUid(uid);
        if (user == null || user.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        Integer rows = userMapper.updateAvatarByUid(uid, username, avatar, new Date());

        if (rows != 1) {
            throw new UpdateException("更新用户头像时产生未知异常");

        }


    }

    //   定义一个md5算法的加密处理
    private String getMD5Password(String password, String salt) {
        for (int i = 1; i <= 3; i++) {
            //        md5加密算法方法的调用（进行三次加密）
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
//        返回加密之后的密码
        return password;

    }
}
