package com.example.store_ht.Service.impl;

import com.example.store_ht.Entity.Address;
import com.example.store_ht.Mapper.AddressMapper;
import com.example.store_ht.Service.IAddressService;
import com.example.store_ht.Service.ex.*;
import lombok.val;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IAddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Value("${user.address.max-count}")
    private Integer MaxCount;

    @Override
    public void insertAddress(Integer uid, String username, Address address) {
        Integer result = addressMapper.countByUid(uid);
        if (result >= MaxCount) {
            throw new AddressCountLimitException("收货地址超出上限");
        }

        address.setUid(uid);
        Integer isDefault = result == 0 ? 1 : 0;   //1表示默认，0表示不是默认
        address.setIsDefault(isDefault);
        address.setModifiedUser(username);
        address.setCreatedUser(username);
        address.setModifiedTime(new Date());
        address.setCreatedTime(new Date());
        Integer rows = addressMapper.insertAddress(address);
        if (rows == 0) {
            throw new InsertException("插入时发生未知异常");
        }


    }

    @Override
    public List<Address> findByUid(Integer uid) {
        List<Address> addressList = addressMapper.findByUid(uid);
        for (Address address : addressList) {
            address.getName();
            address.getAddress();
            address.getPhone();
            address.getTag();
        }
        return addressList;
    }

    @Override
    public void updateDefaultByAid(Integer aid, String username, Integer uid) {
        Address res = addressMapper.findByAid(aid);
        if (res == null) {
            throw new AddressNotFoundException("该地址不存在");
        }
        Integer rows = addressMapper.updateNotDefault(uid);
        if (rows < 1) {
            throw new UpdateException("更新时发生未知异常");
        }
        Integer row = addressMapper.updateDefault(aid, username, new Date());
        if (row != 1) {
            throw new UpdateException("更新时发生未知异常");
        }
    }

    @Override
    public void deleteAddressByAid(Integer aid, Integer uid, String username) {
        Address res = addressMapper.findByAid(aid);
        if (res == null) {
            throw new AddressNotFoundException("用户收货地址不存在");
        }
        if (!res.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }
        Integer rows = addressMapper.deleteAddressByAid(aid);
        if (rows != 1) {
            throw new DeleteException("删除时产生未知异常");
        }
        Integer count = addressMapper.countByUid(uid);
        if (count == 0) {
            //直接中止程序
            return;
        }
        if (res.getIsDefault() == 1) {
//            把最近的收货地址设置为1
            Address address = addressMapper.findLastModified(uid);
            Integer row = addressMapper.updateDefault(address.getAid(), username, new Date());
            if (row != 1) {
                throw new UpdateException("更新数据时产生未知异常");
            }
        }
    }

    @Override
    public Address getByAid(Integer aid, Integer uid) {
        Address address = addressMapper.findByAid(aid);
        if (address == null) {
            throw new AddressNotFoundException("收货地址不存在");
        }
        if (!address.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }
        address.setProvinceCode(null);
        address.setCityCode(null);
        address.setAreaCode(null);
        address.setCreatedTime(null);
        address.setCreatedUser(null);
        return address;
    }
}
