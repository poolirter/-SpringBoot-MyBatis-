package com.example.store_ht.Controller;

import com.example.store_ht.Entity.Product;
import com.example.store_ht.Service.IProductService;
import com.example.store_ht.Util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController extends BaseController{
    @Autowired
    private IProductService productService;

    @RequestMapping("showHotList")
    public JsonResult<List<Product>> findHotList(){
        List<Product> hotList = productService.findHotList();
        return new JsonResult<>(ok,hotList);
    }

    @RequestMapping("/{id}/showList")
    public JsonResult<List<Product>> findListById(@PathVariable("id") Integer id){
        List<Product> List = productService.findListById(id);
        return new JsonResult<>(ok,List);
    }
}
