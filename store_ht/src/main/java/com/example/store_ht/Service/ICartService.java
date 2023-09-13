package com.example.store_ht.Service;


import com.example.store_ht.Entity.Cart;
import com.example.store_ht.Vo.CartVo;

import java.util.List;

public interface ICartService {
    /**
     * 加入购物车
     *
     * @param uid
     * @param pid
     * @param username
     * @param amount
     */
    void insertCart(Integer uid, Integer pid, String username, Integer amount);

    /**
     * 展示购物车数据
     *
     * @param uid
     * @return
     */
    List<CartVo> showCartVo(Integer uid);

    /**
     * 更新商品数量
     *
     * @param cid
     * @param uid
     * @param username
     * @return 增加成功后新的数量
     */
    Integer updateNum(Integer cid, Integer uid, String username);

    /**
     * 根据cid查询商品信息
     * @param cid
     * @param uid
     * @return
     */
    List<CartVo> findOrderByCid(Integer uid,Integer[] cid);

}
