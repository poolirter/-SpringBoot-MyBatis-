package com.example.store_ht.mapper;

import com.example.store_ht.Entity.User;
import com.example.store_ht.Mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

//@SpringBootTest :表示标注当前的类是一个测试类，不会随同项目一块打包
@SpringBootTest
//@RunWith:表示启动这个单元测试类(单元测试类是不能够运行的)，需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    public UserMapper userMapper;

    /*
    单元测试方法：就可以单独独立运行，不用启动整一个项目
    * 1.必须被@Test注解修饰
    * 2.返回值类型必须是void
    * 3.方法的参数列表不指定任何类型
    * 4.方法的访问修饰符必须是public
    * */
    @Test
    public void insert() {
        User user = new User();

        user.setUsername("djj");
        user.setPassword("123");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);

    }

    @Test
    public void findByUsername() {
        User user = userMapper.findByUsername("JJJ");
        System.out.println(user);
    }

    @Test
    public void updatePasswordByUid() {
        Integer user = userMapper.updatePasswordByUid("111", 1, "管理员", new Date());
        System.out.println(user);
    }

    @Test
    public void findByUid() {
        User user = userMapper.findByUid(1);
        System.out.println(user);

    }
    @Test
    public void updateUser(){
        User user=new User();
        user.setModifiedTime(new Date());
        user.setModifiedUser("管理员");
        user.setPhone("123123133");
        user.setEmail("123@qq.com");
        user.setGender(1);
        user.setUid(25);
        Integer user1=userMapper.updateUser(user);
        System.out.println(user1);
    }
    @Test
    public void updateAvatarByUid(){
        userMapper.updateAvatarByUid(24,
                "/upload/avatar.png",
                "管理员",
                new Date());
    }
}
