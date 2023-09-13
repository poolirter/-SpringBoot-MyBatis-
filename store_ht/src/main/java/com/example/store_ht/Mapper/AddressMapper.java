package com.example.store_ht.Mapper;

import com.example.store_ht.Entity.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
//收获地址持久层
public interface AddressMapper {
    /**
     * 插入用户的收获地址数据
     *
     * @param address 收获地址的数据
     * @return 影响的行数
     */
    Integer insertAddress(Address address);

    /**
     * 根据用户的id统计收获地址数量
     *
     * @param uid 用户的id
     * @return 总数
     */
    Integer countByUid(Integer uid);

    /**
     * 根据用户id查询用户的收货地址
     *
     * @param uid
     * @return
     */
    List<Address> findByUid(Integer uid);

    /**
     * 根据aid查询收货地址
     *
     * @param aid
     * @return 地址数据，没有则返回null
     */
    Address findByAid(Integer aid);

    /**
     * 根据uid来修改地址的默认值
     *
     * @param uid
     * @return
     */
    Integer updateNotDefault(Integer uid);

    /**
     * 根据aid来修改默认地址
     *
     * @param aid
     * @return
     */
    Integer updateDefault(@Param("aid") Integer aid, @Param("modifiedUser") String modifiedUser, @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据aid删除地址
     * @param aid
     * @return 返回影响行数
     */
    Integer deleteAddressByAid(Integer aid);

    /**
     * 根据uid查询当前用户最后一次被修改的收货地址
     * @param uid
     * @return
     */
    Address findLastModified(Integer uid);

}
