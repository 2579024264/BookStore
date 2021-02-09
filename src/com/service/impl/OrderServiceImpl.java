package com.service.impl;

import com.dao.BookDao;
import com.dao.OrderDao;
import com.dao.OrderItemDao;
import com.dao.impl.BookDaoImpl;
import com.dao.impl.OrderDaoImpl;
import com.dao.impl.OrderItemDaoImpl;
import com.pojo.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl  implements  OrderService{
    OrderDao orderDao=new OrderDaoImpl();
    OrderItemDao orderItemDao=new OrderItemDaoImpl();
    BookDao bookDao =new BookDaoImpl();
    //生成订单
    @Override
    public String createOrder(Cart cart, Integer userId) {
        // 订单号必须具有唯一性，
        String order_id=System.currentTimeMillis()+""+userId;
        //创建一个订单对象
        Order order=new Order(order_id,new Date(),cart.getTotalPrice(),0,userId);
        //保存订单
        orderDao.saveOrder(order);
        //遍历购物车中每一个商品项转化成为订单项保存到数据库中
        for (Map.Entry<Integer, CartItem>entry:cart.getItems().entrySet()){
            //获取购物车的每一个商品项
            CartItem cartItem=entry.getValue();
            //创建一个订单项
            OrderItem orderItem=new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),order_id);
            //保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);
            //更新库存和销量
            Book book=bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookDao.updateBook(book);
        }
        //形成订单后清空购物车
        cart.clear();
        return order_id;
    }
    //查询订单
    @Override
    public List<Order> searchOrder(Integer userId) {
        List<Order> orderList=orderDao.queryOrder(userId);
        return orderList;
    }

    @Override
    public List<Order> searchAllOrder() {
        List<Order> orderList=orderDao.queryAllOrder();
        return orderList;
    }

    @Override
    public int sendout(String orderId) {
        //设置指定订单的状态status
        orderDao.setStatusByOrderId(orderId);
        return 0;
    }
//根据指定的订单号来查询所有的订单项
    @Override
    public List<OrderItem> searchOrderItems(String order_id) {
        return orderItemDao.queryOrderItems(order_id);

    }
}
