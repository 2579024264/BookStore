package com.test;

import com.pojo.User;
import com.service.impl.UserService;
import com.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserService userService=new UserServiceImpl();
    @Test
    public void regisUser() {
        userService.regisUser(new User(null,"lhh2","bbj123","admin@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(3,"lhh","123456","admin@.com")));
    }

    @Test
    public void exitsUsername() {
        if(userService.exitsUsername("lhh")){
            System.out.println("用户名已存在！");
        }else{
            System.out.println("用户名可用");
        }
    }
}