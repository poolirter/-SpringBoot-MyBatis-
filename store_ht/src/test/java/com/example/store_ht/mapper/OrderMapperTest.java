package com.example.store_ht.mapper;

import com.example.store_ht.Entity.Order;
import com.example.store_ht.Entity.OrderItem;
import com.example.store_ht.Mapper.OrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMapperTest {
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void insOrder() {
        Order order = new Order();
        order.setUid(24);
        order.setRecvName("JJ");
        order.setRecvAddress("901");
        Integer integer = orderMapper.insertOrder(order);
        System.out.println(integer);

    }

    @Test
    public void insertItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(1020);
        orderItem.setOid(2);
        orderItem.setPid(2121);
        orderItem.setTitle("fasfa");
        Integer integer = orderMapper.insertOrderItem(orderItem);
        System.out.println(integer);
    }
}
