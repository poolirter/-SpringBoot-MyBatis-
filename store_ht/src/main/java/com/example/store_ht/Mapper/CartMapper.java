package com.example.store_ht.Mapper;

import com.example.store_ht.Entity.Cart;
import com.example.store_ht.Vo.CartVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface CartMapper {
    /**
     * 加入购物车，插入商品数据
     *
     * @param cart
     * @return
     */
    Integer insertCart(Cart cart);

    /**
     * 更新购物车的商品数量
     *
     * @param cid
     * @param num
     * @return
     */
    Integer updateNum(@Param("cid") Integer cid, @Param("num") Integer num, @Param("modifiedUser") String modifiedUser, @Param("modifiedTime") Date modifiedTime);

    /**
     * 查询购物信息是否本人
     *
     * @param uid
     * @param pid
     * @return
     */
    Cart findCartByUidAndPid(@Param("uid") Integer uid, @Param("pid") Integer pid);

    /**
     * 根据用户id和商品id展示购物车
     *
     * @param uid
     * @return
     */
    List<CartVo> findCartVo(Integer uid);

    /**
     * 查询购物车是否存在
     *
     * @param cid
     * @return
     */
    Cart findByCid(Integer cid);

    /**
     * 通过cid删除购物车
     *
     * @param cid
     * @return
     */
    Integer delCartByCid(Integer cid);

    /**
     * 根据cid显示订单商品列表
     *
     * @param cid
     * @return
     */
    List<CartVo> findOrderVoByCid(Integer[] cid);


}
