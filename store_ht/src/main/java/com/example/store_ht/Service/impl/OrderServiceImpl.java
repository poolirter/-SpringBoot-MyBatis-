package com.example.store_ht.Service.impl;

import com.example.store_ht.Entity.Address;
import com.example.store_ht.Entity.Order;
import com.example.store_ht.Entity.OrderItem;
import com.example.store_ht.Mapper.AddressMapper;
import com.example.store_ht.Mapper.CartMapper;
import com.example.store_ht.Mapper.OrderMapper;
import com.example.store_ht.Mapper.ProductMapper;
import com.example.store_ht.Service.IAddressService;
import com.example.store_ht.Service.ICartService;
import com.example.store_ht.Service.IOrderService;
import com.example.store_ht.Service.ex.InsertException;
import com.example.store_ht.Vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ICartService service;

    @Transactional
    @Override
    public Order insertOrder(String username, Integer uid, Integer aid, Integer[] cid) {
        List<CartVo> cartVos = service.findOrderByCid(uid, cid);
//        计算所选中购物车的商品总价
        long total = 0;
        for (CartVo cartVo : cartVos) {
            total += cartVo.getNum() * cartVo.getRealPrice();
        }
//        创建订单
        Order order = new Order();
        order.setUid(uid);
        Address address = addressService.getByAid(aid, uid);
//        补全地址信息
        order.setRecvName(address.getName());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvAddress(address.getAddress());
        order.setRecvPhone(address.getPhone());
//        补全总价
        order.setTotalPrice(total);
        order.setStatus(0);
        order.setOrderTime(new Date());
        order.setCreatedUser(username);
        order.setCreatedTime(new Date());
        order.setModifiedUser(username);
        order.setModifiedTime(new Date());

        Integer rows = orderMapper.insertOrder(order);
        if (rows != 1) {
            throw new InsertException("插入订单数据时出现未知错误，请联系系统管理员");
        }
        for (CartVo cartVo : cartVos) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOid(order.getOid());
            orderItem.setPid(cartVo.getPid());
            orderItem.setTitle(cartVo.getTitle());
            orderItem.setImage(cartVo.getImage());
            orderItem.setPrice(cartVo.getRealPrice());
            orderItem.setNum(cartVo.getNum());
            orderItem.setCreatedTime(new Date());
            orderItem.setModifiedTime(new Date());
            orderItem.setCreatedUser(username);
            orderItem.setModifiedUser(username);
            Integer rows2 = orderMapper.insertOrderItem(orderItem);
            if (rows2 != 1) {
                throw new InsertException("插入订单数据时出现未知错误，请联系系统管理员");
            }
        }

        return order;


    }
}
