package com.example.store_ht.Controller;

import com.example.store_ht.Service.ex.*;
import com.example.store_ht.Util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/*控制层类的基类*/
public class BaseController {
    // 操作成功的状态码
    public static final int ok = 200;

    //    请求处理方法，这个方法的返回值就是需要传递给前端的数据
//    自动将异常对象传递给此方法的参数列表上
    @ExceptionHandler({ServiceException.class, FileUploadException.class})  //用于统一处理抛出的异常
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicatedException) {
            result.setState(4000);
            result.setMessage("用户名已经被占用");
        } else if (e instanceof InsertException) {
            result.setState(4001);
            result.setMessage("注册时产生未知的错误");
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(4002);
            result.setMessage("用户名的密码错误异常");
        } else if (e instanceof UserNotFoundException) {
            result.setState(4003);
            result.setMessage("用户数据不存在的异常");
        } else if (e instanceof AddressCountLimitException) {
            result.setState(4004);
            result.setMessage("用户收货地址超出上限的异常");
        } else if (e instanceof UpdateException) {
            result.setState(4005);
            result.setMessage("更新数据时产生异常");
        } else if (e instanceof AccessDeniedException) {
            result.setState(4006);
            result.setMessage("非法访问异常");
        } else if (e instanceof AddressNotFoundException) {
            result.setState(4007);
            result.setMessage("收货地址不存在的异常");
        } else if (e instanceof DeleteException) {
            result.setState(4008);
            result.setMessage("删除时产生的异常");
        } else if (e instanceof ProductNotFoundException) {
            result.setState(4009);
            result.setMessage("商品信息不存在异常");
        } else if (e instanceof CartNotFoundException) {
            result.setState(4010);
            result.setMessage("购物车数据不存在异常");
        } else if (e instanceof FileEmptyException) {
            result.setState(5000);
        } else if (e instanceof FileSizeException) {
            result.setState(5001);
        } else if (e instanceof FileTypeException) {
            result.setState(5002);
        } else if (e instanceof FileStateException) {
            result.setState(5003);
        } else if (e instanceof FileUploadIOException) {
            result.setState(5004);
        }
        return result;
    }

    /*
     * 获取session对象中的uid
     * session session对象
     * return 当前登录的用户id值
     * */
    protected final Integer getuidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }


}
