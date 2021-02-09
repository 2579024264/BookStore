package com.test;

import com.dao.OrderDao;
import com.dao.impl.OrderDaoImpl;
import com.pojo.Order;
import com.utils.jdbc;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDaoTest {
    OrderDao orderDao=new OrderDaoImpl();

    @Test
    public void saveOrder() {
        orderDao.saveOrder(new Order("2345678902",new Date(),new BigDecimal(200),0,3));
        //手动提交事务
        jdbc.commitAndClose();
    }

    @Test
    public void queryOrder() {
        List<Order> orders=orderDao.queryOrder(1);
        //手动提交事务
        jdbc.commitAndClose();
        for (Order order:orders) {
            System.out.println(order);
        }
    }

    @Test
    public void queryAllOrder() {
        List<Order> orders=orderDao.queryAllOrder();
        //手动提交事务
        jdbc.commitAndClose();
        for (Order order:orders) {
            System.out.println(order);
        }
    }
}