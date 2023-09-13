package com.example.store_ht.mapper;

import com.example.store_ht.Entity.Product;
import com.example.store_ht.Mapper.ProductMapper;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductMapperTest {
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void findHotList() {
        List<Product> hotList = productMapper.findHotList();
        for (Product productList:hotList)
        System.out.println(productList);
    }

    @Test
    public void findListById() {
        List<Product> hotList = productMapper.findListById(10000013);
        for (Product productList:hotList)
            System.out.println(productList);
    }
}
