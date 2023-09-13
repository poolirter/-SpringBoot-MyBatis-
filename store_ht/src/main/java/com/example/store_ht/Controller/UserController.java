package com.example.store_ht.Controller;

import com.example.store_ht.Service.ex.*;
import com.example.store_ht.Entity.User;
import com.example.store_ht.Service.ex.FileEmptyException;
import com.example.store_ht.Service.ex.FileSizeException;
import com.example.store_ht.Service.impl.UserServiceImpl;
import com.example.store_ht.Util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController    //@Controller+@ResponseBody
//@Controller
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    private UserServiceImpl userService;

    /*
     * 1.接收数据方式：请求处理方式的参数列表设置为pojo类型来接收前端数据
     * spring boot会将前端的url地址中的参数名和pojo类的属性名进行比较，如果比较的两个名称相同
     * 则将值注入到pojo类上面
     * */
    @RequestMapping(value = "reg", method = RequestMethod.POST)
//    @ResponseBody  //表示此方法的响应结果以json格式进行数据的响应给到前端
    public JsonResult<Void> reg(User user) {
        userService.reg(user);
        return new JsonResult<>(ok);
    }

    /*
     * 2.接收数据方式：请求处理方式的参数列表设置为String，int.....（非自己封装的pojo类型）
     * Springboot会直接将请求的参数名和方法进行直接比较，如果名称相同则自动注入
     * */
    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        User data = userService.login(username, password);
//        向session对象中完成数据的绑定（session全局的）
        session.setAttribute("uid", data.getUid().toString());
        session.setAttribute("username", data.getUsername());
//        获取session中绑定的数据
        System.out.println(getuidFromSession(session));
        System.out.println(getUsernameFromSession(session));
        return new JsonResult<>(ok, data);
    }

    @RequestMapping("updatePassword")
    public JsonResult<Void> updatePassword(String oldPassword, String newPassword, HttpSession session) {
        Integer uid = getuidFromSession(session);
        userService.updatePassword(oldPassword, uid, newPassword);
        return new JsonResult<>(ok);

    }

    @RequestMapping("getByUid")
    public JsonResult<User> getByUid(HttpSession session) {
        User data = userService.getByUid(getuidFromSession(session));
        return new JsonResult<>(ok, data);
    }

    @RequestMapping("updateUser")
    public JsonResult<Void> updateUser(User user, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.updateUser(uid, username, user);
        return new JsonResult<>(ok);
    }

    //    设置上传文件的最大值
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;

    //    限制上传文件的类型
    public static final List<String> AVATAR_TYPE = new ArrayList<>();

    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }

    /**
     * MultipartFile接口是springMVC提供的一个接口，这个接口为我们包装了
     * 获取文件类型的数据（任何类型的file都可以接收），SpringBoot它有整合了
     * SpringMvc,只需要再处理请求的方法参数列表上声明一个参数类型为MultipartFile
     * 的参数，然后springboot自动将传递给服务的文件数据赋值给这个参数
     *
     * @param session
     * @param file
     * @return
     */

    @RequestMapping("updateAvatar")
    public JsonResult<String> updateAvatar(HttpSession session, MultipartFile file) {
        if (file.isEmpty()) {
            throw new FileEmptyException("文件为空");
        }
        if (file.getSize() > AVATAR_MAX_SIZE) {
            throw new FileSizeException("上传文件过大");
        }
//        判断文件类型是否是我们规定的后缀类型
        String contentType = file.getContentType();
        if (!AVATAR_TYPE.contains(contentType)) {
            System.out.println(file.getName());
            throw new FileTypeException("文件类型不支持");
        }
//        上传的文件.../upload/文件.png
        String parent = session.getServletContext().getRealPath("upload");

//        file对象指向这个路径，File是否存在
        File dir = new File(parent);
        if (!dir.exists()) { //检测文件目录是否存在
            dir.mkdirs();  //创建目录
        }
//        获取到这个文件名称，uuid来生成一个新的字符串作为文件名  （以防重命名）
        String originalFilename = file.getOriginalFilename();
        System.out.println("OriginalFilename=" + originalFilename);
        int index = originalFilename.lastIndexOf(".");   //最后一次出现的下标
        String suffix = originalFilename.substring(index);   //subString 下标提取字符串
        String filename = UUID.randomUUID().toString().toUpperCase() + suffix;
        File dest = new File(dir, filename);  //空文件 在dir 目录下创建一个文件
        try {
            file.transferTo(dest); //将file文件中的数据写入到dest文件中
        } catch (IOException e) {
            throw new FileUploadIOException("文件读写异常");
        } catch (FileStateException e) {
            throw new FileStateException("文件状态异常");
        }
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
//        返回头像的路径/upload/test.png
        String avatar = "/upload/" + filename;
        userService.updateAvatar(uid, username, avatar);
        System.out.println(avatar);

        return new JsonResult<>(ok,avatar);

    }



//    public JsonResult<Void> reg(User user){
////创建响应结果对象
//        JsonResult<Void> result=new JsonResult<>();
//        try {
//            userService.reg(user);
//            result.setState(200);
//            result.setMessage("用户注册成功");
//        }catch (UsernameDuplicatedException e){
//            result.setState(4000);
//            result.setMessage("用户名被占用");
//        }catch (InsertException e){
//            result.setState(5000);
//            result.setMessage("注册时产生未知的异常");
//        }
//        return result;
//    }

}
