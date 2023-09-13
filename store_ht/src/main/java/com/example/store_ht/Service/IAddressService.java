package com.example.store_ht.Service;

import com.example.store_ht.Entity.Address;

import java.util.List;

//收货地址业务层
public interface IAddressService {
    /**
     * 新增收货地址
     * @param uid
     * @param username
     * @param address
     */
    void insertAddress(Integer uid, String username, Address address);

    /**
     * 查询用户地址
     * @param uid
     */
    List<Address> findByUid(Integer uid);

    /**
     *
     * @param aid
     * @param username
     * @param uid
     */
    void updateDefaultByAid(Integer aid,String username,Integer uid);

    /**
     *
     * @param aid
     * @param uid
     * @param username
     */
    void deleteAddressByAid(Integer aid,Integer uid,String username);

    /**
     * 通过aid获取收货地址信息
     * @param aid
     * @return
     */
    Address getByAid(Integer aid,Integer uid);


}
