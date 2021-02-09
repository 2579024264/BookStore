package com.test;

import com.pojo.Cart;
import com.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void addItem() {
        Cart cart=new Cart();
        //测试添加两件id值相同的商品
        cart.addItem(new CartItem("从入门到放弃",1,20,new BigDecimal(15.0),new BigDecimal(200.0)));
        cart.addItem(new CartItem("从入门到放弃",1,20,new BigDecimal(15.0),new BigDecimal(300.0)));
       //当使用System.out.println()时会自动调用对象的toString方法。
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart=new Cart();
        cart.addItem(new CartItem("数据库应用",2,10,new BigDecimal(20.0),new BigDecimal(200)));
        cart.addItem(new CartItem("操作系统",3,10,new BigDecimal(10),new BigDecimal(100)));
        System.out.println(cart);
        cart.deleteItem(2);
        System.out.println(cart);

    }

    @Test
    public void clear() {
    }

    @Test
    public void updateCount() {
    }
}