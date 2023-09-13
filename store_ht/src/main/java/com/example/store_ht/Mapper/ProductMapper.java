package com.example.store_ht.Mapper;

import com.example.store_ht.Entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper {
    /**
     * 查询热销商品
     * @return
     */
    List<Product> findHotList();

    /**
     * 根据商品id展示商品
     * @param id
     * @return
     */
    List<Product> findListById(Integer id);

    /**
     * 根据商品id展示商品
     * @param id
     * @return
     */
    Product findById(Integer id);

}
