package com.test;

import com.dao.OrderItemDao;
import com.dao.impl.OrderDaoImpl;
import com.dao.impl.OrderItemDaoImpl;
import com.pojo.OrderItem;
import com.utils.jdbc;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class OrderItemDaoTest {
    OrderItemDao orderItemDao=new OrderItemDaoImpl();

    @Test
    public void saveOrderItem() {
        orderItemDao.saveOrderItem(new OrderItem(null,"java入门到精通",1,new BigDecimal(100),new BigDecimal(100),"1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null,"javaScript入门到精通",2,new BigDecimal(100),new BigDecimal(200),"1234567890"));
        //手动提交事务。
        jdbc.commitAndClose();
    }

    @Test
    public void queryOrderItems() {
        List<OrderItem> orderItems=orderItemDao.queryOrderItems("16127989178901");
        for (OrderItem orderItem:orderItems) {
            System.out.println(orderItem);
        }
    }
}