package com.example.store_ht.serivce;

import com.example.store_ht.Entity.User;
import com.example.store_ht.Service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    private IUserService userService;

    @Test
    public void reg() {
        try {
            User user = new User();
            user.setUsername("yuanyuan10");
            user.setPassword("132323");
            userService.reg(user);
            System.out.println("ok");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Test
    public void login() {
        User user = userService.login("yuanyuan10", "132323");
        System.out.println(user);
    }

    @Test
    public void updatePassword() {
        userService.updatePassword("123", 24, "123");

    }

    @Test
    public void getByUid() {
        System.out.println(userService.getByUid(24));

    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setPhone("222222");
        user.setEmail("22@qq.com");
        user.setGender(1);
        userService.updateUser(24, "嘟嘟", user);


    }

    @Test
    public void updateAvatar(){
        userService.updateAvatar(24,"/upload/test.png","小明");
    }

}
