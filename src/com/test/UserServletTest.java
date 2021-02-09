package com.test;

import java.lang.reflect.Method;

public class UserServletTest {
    public void login(){
        System.out.println("我是login");
    }
    public void regist(){

    }

    public static void main(String[] args) {
        try{
             String action="login";
             //获取action业务鉴别字符串，获取响应的业务 方法反射对象。
            Method method= UserServletTest.class.getDeclaredMethod(action);
            System.out.println(method);
            method.invoke(new UserServletTest());

        }catch(Exception e){

        }
    }


}
