package com.example.store_ht.Service;

import com.example.store_ht.Entity.User;

/*用户模块业务层接口*/
public interface IUserService {

    /*
    * 用户注册方法
    * user  用户数据的对象
    * */
    void reg(User user);

    User login(String username,String password);

    void updatePassword(String oldPassword, Integer uid, String newPassword);

/*
* 根据用户的id查询用户的数据
* return 用户数据
*
* */
    User getByUid(Integer uid);

    /**
     * 更新用户数据
     * @param uid
     * @param username
     * @param user
     */

    void updateUser(Integer uid,String username,User user);

    /**
     *修改用户头像
     * @param uid
     * @param avatar
     * @param username
     */
    void updateAvatar(Integer uid,String username,String avatar);

}
