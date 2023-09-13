package com.example.store_ht.Controller;

import com.example.store_ht.Entity.BaseEntity;
import com.example.store_ht.Entity.Cart;
import com.example.store_ht.Entity.Product;
import com.example.store_ht.Service.ICartService;
import com.example.store_ht.Service.IProductService;
import com.example.store_ht.Util.JsonResult;
import com.example.store_ht.Vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("cart")
public class CartController extends BaseController {
    @Autowired
    private ICartService iCartService;
    @Autowired
    private IProductService iProductService;

    @RequestMapping("insertCart")
    public JsonResult<List<Product>> insertCart(HttpSession session, Integer amount, Integer pid) {
        String username = getUsernameFromSession(session);
        Integer uid = getuidFromSession(session);
        iCartService.insertCart(uid, pid, username, amount);
        List<Product> list = iProductService.findListById(pid);
        return new JsonResult<>(ok, list);
    }

    @RequestMapping({"/", ""})
    public JsonResult<List<CartVo>> showCartVo(HttpSession session) {
        List<CartVo> cartVo = iCartService.showCartVo(getuidFromSession(session));
        return new JsonResult<>(ok, cartVo);
    }

    @RequestMapping("/{cid}/addNum")
    public JsonResult<Integer> updateNum(HttpSession session, @PathVariable("cid") Integer cid) {
        Integer num = iCartService.updateNum(cid, getuidFromSession(session), getUsernameFromSession(session));
        return new JsonResult<>(ok, num);

    }

    @RequestMapping("/order")
    public JsonResult<List<CartVo>> findOrderCart(Integer[] cid, HttpSession session) {
        List<CartVo> order = iCartService.findOrderByCid(getuidFromSession(session), cid);
        return new JsonResult<>(ok, order);

    }
}
