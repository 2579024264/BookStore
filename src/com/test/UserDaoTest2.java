package com.test;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest2 {

    @Test
    public void queryUserByUsername() {
        UserDao userDao=new UserDaoImpl();
        //获取数据库的内容
        System.out.println(userDao.queryUserByUsername("admin"));
        if (userDao.queryUserByUsername("admin")==null){
            System.out.println("用户名可用");
        }else{
            System.out.println("用户名已存在！");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        UserDao userDao=new UserDaoImpl();
        System.out.println(userDao.queryUserByUsernameAndPassword("admin","admin"));
        if (userDao.queryUserByUsernameAndPassword("admin","admin")==null) {
            System.out.println("用户名或者密码错误！");
        } else {
            System.out.println("用户存在！");
        }
    }

    @Test
    public void saveUser() {
        UserDao userDao=new UserDaoImpl();
        userDao.saveUser(new User(null,"lhh","123456","admin@.com"));
    }
}