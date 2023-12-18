package com.example.store_ht.Controller;

import com.example.store_ht.Entity.Address;
import com.example.store_ht.Service.IAddressService;
import com.example.store_ht.Util.JsonResult;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController {
    @Autowired
    private IAddressService iAddressService;

    @RequestMapping(value = "add_newAddress")
    public JsonResult<Void> insertAddress(HttpSession session, Address address) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        iAddressService.insertAddress(uid, username, address);
        return new JsonResult<>(ok);
    }

    @RequestMapping({"", "/"})
    public JsonResult<List<Address>> findByUid(HttpSession session) {
        Integer uid = getuidFromSession(session);
        List<Address> addressList = iAddressService.findByUid(uid);
        return new JsonResult<>(ok, addressList);

    }

    //    @RequestMapping("updateDefault")
//    public JsonResult<Void> updateDefault(HttpSession session,Integer aid){
//        Integer uid=getuidFromSession(session);
//        String username=getUsernameFromSession(session);
//        iAddressService.updateDefaultByAid(aid,username,uid);
//        return new JsonResult<>(ok);
//    }
    @RequestMapping("/{aid}/set_default")
    public JsonResult<Void> updateDefault(@PathVariable("aid") Integer aid, HttpSession session) {
        iAddressService.updateDefaultByAid(aid,
                getUsernameFromSession(session),
                getuidFromSession(session));
        return new JsonResult<>(ok);

    }

    @RequestMapping("/{aid}/delete_address")
    public JsonResult<Void> deleteByAid(@PathVariable("aid") Integer aid, HttpSession session) {
        iAddressService.deleteAddressByAid(aid,
                getuidFromSession(session),
                getUsernameFromSession(session));
        return new JsonResult<>(ok);

    }
}
