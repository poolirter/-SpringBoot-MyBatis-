package com.example.store_ht.Config;

import com.example.store_ht.Interceptor.LoginInterception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration   //加载当前拦截器并进行注册
//处理器拦截器的注册
public class LoginInterceptionConfigure implements WebMvcConfigurer {
//创建自定义的拦截器
    @Autowired
    LoginInterception loginInterception;

//配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //    配置白名单;存放在一个list集合
        List<String> patterns=new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        patterns.add("/users/reg");
        patterns.add("/users/login");

//        完成拦截器配置
        registry.addInterceptor(loginInterception)
                .addPathPatterns("/**")   //表示要拦截的url是什么
                .excludePathPatterns(patterns);   //除了这个集合之外，其他都禁止
    }
}
