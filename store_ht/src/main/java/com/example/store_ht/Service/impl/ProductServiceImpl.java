package com.example.store_ht.Service.impl;

import com.example.store_ht.Entity.Product;
import com.example.store_ht.Mapper.ProductMapper;
import com.example.store_ht.Service.IProductService;
import com.example.store_ht.Service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findHotList() {
        List<Product> hotList = productMapper.findHotList();
        for (Product list : hotList) {
            list.setPriority(null);
            list.setCreatedTime(null);
            list.setCreatedUser(null);
            list.setModifiedTime(null);
            list.setModifiedUser(null);

        }
        return hotList;
    }

    @Override
    public List<Product> findListById(Integer id) {
        List<Product> productList = productMapper.findListById(id);
        if (productList == null) {
            throw new ProductNotFoundException("商品不存在");
        }
        for (Product list : productList) {
            list.setPriority(null);
            list.setCreatedTime(null);
            list.setCreatedUser(null);
            list.setModifiedTime(null);
            list.setModifiedUser(null);
        }
        return productList;
    }
}
