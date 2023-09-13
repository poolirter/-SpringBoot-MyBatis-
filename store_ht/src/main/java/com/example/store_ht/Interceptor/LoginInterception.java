package com.example.store_ht.Interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
//定义一个拦截器
public class LoginInterception implements HandlerInterceptor {
/*
//检测全局session对象中是否有uid数据，如果有就放行，如果没有就重定向到登录页面
* request 请求对象
* response 响应对象
* handler 处理器（url+controller：映射）
//return 如果返回值为true表示放行当前的请求，如果返回为false 表示拦截当前请求
* */

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
//        httpservletrequest对象来获取session对象
        Object object=request.getSession().getAttribute("uid");
        if (object==null){ //说明用户没有登录过系统，则重定向到首页
            response.sendRedirect("/web/login.html");
//            结束调用
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
