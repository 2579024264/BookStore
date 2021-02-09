package com.service.impl;

import com.pojo.Cart;
import com.pojo.Order;
import com.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    //生成订单
    public String createOrder(Cart cart, Integer userId);

    //查询某个用户的订单
    public List<Order> searchOrder(Integer userId);

    //查询所有的订单
    public  List<Order>searchAllOrder();

    public int sendout(String orderId);

    public List<OrderItem> searchOrderItems(String order_id);
}
