package com.dao;

import com.pojo.Order;
import com.pojo.OrderItem;

import java.util.List;

public interface OrderDao {
    public int saveOrder(Order order);
    public List<Order> queryOrder(Integer userId);
    public List<Order>queryAllOrder();
    public void setStatusByOrderId(String order_id);
}

