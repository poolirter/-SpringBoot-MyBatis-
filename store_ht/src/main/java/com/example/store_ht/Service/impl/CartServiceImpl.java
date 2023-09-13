package com.example.store_ht.Service.impl;

import com.example.store_ht.Entity.Cart;
import com.example.store_ht.Entity.Product;
import com.example.store_ht.Mapper.CartMapper;
import com.example.store_ht.Mapper.ProductMapper;
import com.example.store_ht.Service.ICartService;
import com.example.store_ht.Service.ex.AccessDeniedException;
import com.example.store_ht.Service.ex.CartNotFoundException;
import com.example.store_ht.Service.ex.InsertException;
import com.example.store_ht.Service.ex.UpdateException;
import com.example.store_ht.Vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void insertCart(Integer uid, Integer pid, String username, Integer amount) {
//        查询该商品是否在购物车
        Cart cart = cartMapper.findCartByUidAndPid(uid, pid);
        if (cart == null) { //表示不在购物车
            Cart cart1 = new Cart();
            cart1.setPid(pid);
            cart1.setNum(amount);
            cart1.setUid(uid);
            Product products = productMapper.findById(pid);
            cart1.setPrice(products.getPrice());
            cart1.setModifiedUser(username);
            cart1.setModifiedTime(new Date());
            cart1.setCreatedTime(new Date());
            cart1.setCreatedUser(username);
            Integer rows = cartMapper.insertCart(cart1);
            if (rows != 1) {
                throw new InsertException("插入时产生未知异常");
            }
        } else {  //已经存在 num++
            Integer row = cartMapper.updateNum(cart.getCid(), cart.getNum() + amount, username, new Date());
            if (row != 1) {
                throw new UpdateException("更新数据时产生未知异常");
            }
        }
    }

    @Override
    public List<CartVo> showCartVo(Integer uid) {
        return cartMapper.findCartVo(uid);
    }

    @Override
    public Integer updateNum(Integer cid, Integer uid, String username) {
        Cart cart = cartMapper.findByCid(cid);
        if (cart == null) {
            throw new CartNotFoundException("购物车数据不存在");
        }
        if (!cart.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }
        Integer num = cart.getNum() + 1;
        Integer rows = cartMapper.updateNum(cid, num, username, new Date());
        if (rows != 1) {
            throw new UpdateException("更新数据时产生未知异常");
        }
        return num;
    }

    @Override
    public List<CartVo> findOrderByCid(Integer uid, Integer[] cid) {
        List<CartVo> orderVo = cartMapper.findOrderVoByCid(cid);
        for (CartVo cartVo : orderVo) {
            if (!cartVo.getUid().equals(uid)) {
                orderVo.remove(cartVo);
            }
        }
        return orderVo;
    }
}

