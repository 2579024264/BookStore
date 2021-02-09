package com.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private String order_Id;
    private Date create_time;
    private BigDecimal price;
    //0表示未发货，1已发货，2已签收
    private Integer status=0;
    private Integer user_Id;

    public Order(String order_Id, Date create_time, BigDecimal price, Integer status, Integer user_Id) {
        this.order_Id = order_Id;
        this.create_time = create_time;
        this.price = price;
        this.status = status;
        this.user_Id = user_Id;
    }

    public Order() {
    }

    public String getOrder_Id() {
        return order_Id;
    }

    public void setOrder_Id(String order_Id) {
        this.order_Id = order_Id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(Integer user_Id) {
        this.user_Id = user_Id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_Id='" + order_Id + '\'' +
                ", create_time=" + create_time +
                ", price=" + price +
                ", status=" + status +
                ", user_Id=" + user_Id +
                '}';
    }
}
