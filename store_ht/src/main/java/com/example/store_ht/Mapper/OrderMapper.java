package com.example.store_ht.Mapper;

import com.example.store_ht.Entity.Order;
import com.example.store_ht.Entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderMapper {
    /**
     * 创建订单
     * @param order
     * @return
     */
    Integer insertOrder(Order order);

    /**
     * 插入订单商品数据
     * @param orderItem 订单商品数据
     * @return 受影响的行数
     */
    Integer insertOrderItem(OrderItem orderItem);

}
