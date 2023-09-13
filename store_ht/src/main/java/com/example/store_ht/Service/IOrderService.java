package com.example.store_ht.Service;

import com.example.store_ht.Entity.Order;
import com.example.store_ht.Entity.OrderItem;

public interface IOrderService {
    /**
     * 创建订单
     * @param username
     * @param uid
     * @param cid
     * @param aid
     */
    Order insertOrder(String username, Integer uid,Integer aid,Integer[] cid);
}
