package com.test;

import com.pojo.Cart;
import com.pojo.CartItem;
import com.service.impl.OrderService;
import com.service.impl.OrderServiceImpl;
import com.utils.jdbc;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceTest {

    @Test
    public void createOrder() {
        Cart cart=new Cart();
        cart.addItem(new CartItem("java从入门到精通",1,1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem("javaScript从精通到入门",2,1,new BigDecimal(100),new BigDecimal(100)));
        OrderService orderService=new OrderServiceImpl();
        orderService.createOrder(cart,1);
        jdbc.commitAndClose();
    }
}