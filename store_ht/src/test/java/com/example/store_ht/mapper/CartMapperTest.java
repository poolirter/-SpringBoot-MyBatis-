package com.example.store_ht.mapper;

import com.example.store_ht.Entity.Cart;
import com.example.store_ht.Mapper.CartMapper;
import com.example.store_ht.Vo.CartVo;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartMapperTest {
    @Autowired
    private CartMapper cartMapper;

    @Test
    public void insertCart() {
        Cart cart=new Cart();
        cart.setUid(24);
        cart.setNum(1);
        cart.setPid(1);
        Integer carts = cartMapper.insertCart(cart);
        System.out.println(carts);
    }

    @Test
    public void updateNum(){
        Integer num = cartMapper.updateNum(1, 2,"dd",new Date());
        System.out.println(num);

    }
    @Test
    public void selectCart(){
        Cart cart = cartMapper.findCartByUidAndPid(24,1);
        System.out.println(cart);

    }

    @Test
    public void findCartVo(){
        List<CartVo> cartVo = cartMapper.findCartVo(24);
        for (CartVo cart:cartVo){
            System.out.println(cart);
        }
    }

    @Test
    public void findByCid(){
        Cart cartMapperByCid = cartMapper.findByCid(8);
        System.out.println(cartMapperByCid);
    }

    @Test
    public void defByCid(){
        System.out.println(cartMapper.delCartByCid(8));
    }

    @Test
    public void findOrder(){
        Integer[] cid={2,10,11};
        List<CartVo> l = cartMapper.findOrderVoByCid(cid);
        System.out.println("count:"+l);
        for (CartVo item:l){
            System.out.println(item);
        }

    }
}
