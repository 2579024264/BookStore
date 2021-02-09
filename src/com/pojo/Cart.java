package com.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车对象
 */

public class Cart {
//    private Integer totalCount=0;
//    private BigDecimal totalPrice;
    /**
     *  使用Map的好处是便于遍历集合。
     *  key值是唯一的
     *  value值是商品的信息
   */
    private Map<Integer,CartItem> items=new LinkedHashMap<Integer,CartItem>();


    /**
     * 添加商品
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        //在添加商品项之前，得先要判断购物车中是否存在该商品，如果存在则商品总数增加，商品总价增加，
        // 如果不存在，则添加到购物车集合中。
        CartItem cartItem1=items.get(cartItem.getId());
        if(cartItem1==null){
            //之前没添加过此商品
            items.put(cartItem.getId(),cartItem);
        }else{
            //商品数量增加，商品总金额增加
            cartItem1.setCount(cartItem1.getCount()+cartItem.getCount());
            //更新总金额
            cartItem1.setTotalPrice(cartItem1.getPrice().multiply(new BigDecimal(cartItem1.getCount())));
        }

    }

    /**
     * 删除商品
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);

    }

    /**
     * 清空购物车
     * @param
     */
    public void clear(){
        items.clear();
    }

    /**
     * 修改商品数量
     * @param
     */
    public void updateCount(Integer id,Integer count){
        CartItem item=items.get(id);
        //判断是否存在商品，如果有修改商品数量，修改总金额
        if (item!=null){
            //修改数量
            item.setCount(count);
            //修改金额
            //更新总金额
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));

        }

    }

    public Cart(Map<Integer, CartItem> items) {
        this.items = items;
    }

    public Cart() {
    }

    public Integer getTotalCount() {
        Integer totalCount=0;
        //商品的总数量是通过计算items中商品的数量得出的
        //下面是遍历一个map的键值对
        for (Map.Entry<Integer,CartItem>entry: items.entrySet()){
            totalCount+=entry.getValue().getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice=new BigDecimal(0);
        //商品的总金额是通过计算items中的商品得出的
        //通过另一种方式遍历
        for (CartItem item:items.values()){
            //BigDecimal类型元素自增的方式使用api add();
            totalPrice=totalPrice.add(item.getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
