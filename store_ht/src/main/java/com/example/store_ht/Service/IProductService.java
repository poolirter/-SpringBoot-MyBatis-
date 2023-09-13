package com.example.store_ht.Service;

import com.example.store_ht.Entity.Product;

import java.util.List;

//商品业务接口
public interface IProductService {
    /**
     * 查询热销商品
     */
    List<Product> findHotList();

    /**
     * 根据id展示商品
     * @param id
     * @return
     */
    List<Product> findListById(Integer id);
}
