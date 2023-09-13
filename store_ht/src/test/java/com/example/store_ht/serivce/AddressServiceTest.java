package com.example.store_ht.serivce;

import com.example.store_ht.Entity.Address;
import com.example.store_ht.Service.IAddressService;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTest {
    @Autowired
    private IAddressService iAddressService;

    @Test
    public void insert() {
        Address address = new Address();
        address.setPhone("123121");
        address.setAddress("肇庆市");
        address.setName("11aas");

        iAddressService.insertAddress(24, "管理员", address);
    }

    @Test
    public void findByUid() {
        List<Address> addressList = iAddressService.findByUid(24);
        System.out.println(addressList);

    }

    @Test
    public void updateDefaultByAid() {
        iAddressService.updateDefaultByAid(4, "管理员员", 24);
    }

    @Test
    public void deleteAddressByAid(){
        iAddressService.deleteAddressByAid(6,24,"管理员");
    }


}
