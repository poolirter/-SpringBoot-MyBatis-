package com.example.store_ht;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class StoreHtApplicationTests {

    @Autowired   //自动配置
    private DataSource source;

    @Test
    void contextLoads() {
    }

    /*
    * 数据库连接迟
    * 1.DBCP
    * 2.C3P0
    * 3.Hikari: 管理数据库的连接对象
    * HikariProxyConnection@231900526 wrapping com.mysql.cj.jdbc.ConnectionImpl@5a49af50
    * 后端跑通
    * */
//后端测试
    @Test
    void getConnection() throws SQLException {
        System.out.println(source.getConnection());
    }

}
