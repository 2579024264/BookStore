package com.pojo;

import com.mysql.jdbc.PacketTooBigException;

import java.math.BigDecimal;

public class CartItem {
    //书名
    private String name;
    //书id
    private Integer id;
    //数量
    private Integer count;
    //单价
    private BigDecimal price;
    //总价
    private BigDecimal totalPrice;

    public CartItem() {
    }

    public CartItem(String name, Integer id, Integer count, BigDecimal price, BigDecimal totalPrice) {
        this.name = name;
        this.id = id;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
