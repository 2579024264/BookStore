package com.dao.impl;

import com.dao.OrderItemDao;
import com.pojo.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql="insert into t_order_item(`name`,`count`,`price`, `total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotal_price(),orderItem.getOrder_id());
    }

    @Override
    public List<OrderItem> queryOrderItems(String order_id) {
        String sql="select `name`,`count`,`price`,`total_price` from t_order_item where order_id=?";
        return queryForList(OrderItem.class,sql,order_id);
    }
}
