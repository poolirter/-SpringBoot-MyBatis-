package com.example.store_ht.mapper;

import com.example.store_ht.Entity.Address;
import com.example.store_ht.Mapper.AddressMapper;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTest {
    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insertAddress() {
        Address address = new Address();
        address.setUid(24);
        address.setName("ddf");
        address.setPhone("121231");
        System.out.println(addressMapper.insertAddress(address));
    }

    @Test
    public void countUid() {
        Integer count = addressMapper.countByUid(24);
        System.out.println(count);


    }

    @Test
    public void findByUid() {
        List<Address> addressList = addressMapper.findByUid(24);
        for (Address a : addressList) {
            System.out.println(a);
        }
    }

    @Test
    public void findByAid() {
       Address res = addressMapper.findByAid(3);
        System.out.println(res);

    }


    @Test
    public void updateNotDefault() {
        addressMapper.updateNotDefault(24);

    }


    @Test
    public void updateDefault() {
        addressMapper.updateDefault(3,"管理员",new Date());

    }

    @Test
    public void deleteByAid(){
        addressMapper.deleteAddressByAid(4);
    }

    @Test
    public void updateAvatarByUid(){
        Address address = addressMapper.findLastModified(24);
        System.out.println(address);
    }


}
