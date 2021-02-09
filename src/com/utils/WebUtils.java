package com.utils;

import com.pojo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class WebUtils {
    //参数完全可以用map代替request，这样之后无论哪个java文件中调用该类，都能实现注入的功能。
    //因为接收和返回的数据不确定，所以这里的形参和返回数据类型都是用T泛型。
    public static <T> T copyParamToBean(Map value, T bean){
        try {
            //注入之后，把所有的参数都注入给了bean对象.
            /**
             * 把Map中的值注入到对应的javaBean属性中。耦合度高。
             */
            BeanUtils.populate(bean,value);
            System.out.println("注入之后"+bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将字符串转换成int类型的数据
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parse(String strInt,int defaultValue){
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
