package com.dao.impl;

import com.dao.OrderDao;
import com.pojo.Order;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql="insert into t_order(`order_id`,`create_time`,`price`, `status`,`user_id`) values(?,?,?,?,?)";
        return update(sql,order.getOrder_Id(),order.getCreate_time(),order.getPrice(),order.getStatus(),order.getUser_Id());
    }

    @Override
    public List<Order> queryOrder(Integer userId) {
        String sql="select `order_id`,`create_time`,`price`,`status`,`user_id`from t_order where user_id=?";
        return queryForList(Order.class,sql,userId);
    }

    @Override
    public List<Order> queryAllOrder() {
        String sql="select `order_id`,`create_time`,`price`,`status`,`user_id` from t_order";
        return queryForList(Order.class,sql);
    }

    @Override
    public void setStatusByOrderId(String order_id) {
        String sql="update t_order set `status`=? where order_id=?";
        Integer status=1;
        update(sql,status,order_id);
    }
}
