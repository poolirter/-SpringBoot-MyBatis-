package com.example.store_ht.Controller;
import com.example.store_ht.Entity.Order;
import com.example.store_ht.Service.IOrderService;
import com.example.store_ht.Util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("order")
public class OrderController extends BaseController {
    @Autowired
    private IOrderService iOrderService;

    @RequestMapping("/create")
    public JsonResult<Order> createOrder(HttpSession httpSession, Integer aid, Integer[] cid) {
        Order order = iOrderService.insertOrder(
                getUsernameFromSession(httpSession),
                getuidFromSession(httpSession),
                aid,
                cid);
        return new JsonResult<>(ok, order);

    }

}
