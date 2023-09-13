package com.example.store_ht;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.store_ht.Mapper")
public class StoreHtApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreHtApplication.class, args);
    }

}
