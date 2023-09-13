package com.example.store_ht.Mapper;
import com.example.store_ht.Entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.Date;

/*用户模块的持久层接口*/
@Mapper
@Repository
public interface UserMapper {
    /**
     * 插入用户的数据
     *
     * @param user
     * @return 受影响的行数
     * insert 只能是void或者是int
     */

    Integer insert(User user);


    /**
     * 根据用户名来查询用户的数据
     *
     * @param username
     * @return 如果找到对应的用户则返回这个用户的数据，如果没有找到则返回null值
     * select 语句执行的时候，查询的结果是一个对象，多个对象
     */

    User findByUsername(String username);

    /**
     * 根据uid来修改用户密码
     *
     * @param password
     * @param uid
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updatePasswordByUid(@Param("password") String password, @Param("uid") Integer uid, @Param("modifiedUser") String modifiedUser, @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据用户的id来查询用户的数据
     *
     * @param uid
     * @return 如果找到对应的用户则返回这个用户的数据，如果没有找到则返回null值
     */
    User findByUid(Integer uid);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    Integer updateUser(User user);

    /**
     * @param uid
     * @param avatar
     * @param modifiedUser
     * @param modifiedTime
     * @return
     * @Param("sql与接口变量值不一致的时候强行注入)
     */
    Integer updateAvatarByUid(@Param("uid") Integer uid,
                              @Param("modifiedUser") String modifiedUser,
                              @Param("avatar") String avatar,
                              @Param("modifiedTime") Date modifiedTime);

}
