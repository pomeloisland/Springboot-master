package com.example.demo0921.controller;

import com.example.demo0921.pojo.User;
import com.example.demo0921.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    UserService userService;
    @RequestMapping("/user/login")
    //具体业务
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            //Model回显数据     @RequestParam("username") 取值保险起见    拦截器会获取session存储的值去判断
            Model model, HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        ByteSource salt = ByteSource.Util.bytes(username);
        String newPs = new SimpleHash("MD5", password, salt, 1024).toHex();
        UsernamePasswordToken token = new UsernamePasswordToken(username,newPs);
        session.setAttribute("loginUser", username);
        try{
            subject.login(token);
            return "redirect:/main.html";   //redirect:/main.html  重定向作用
        }catch (UnknownAccountException e){//用户名不存在
            model.addAttribute("msg","用户名错误");
            return "index";
        }catch (IncorrectCredentialsException e) {//密码错误
            model.addAttribute("msg","密码错误");
            return "index";
        }
//        //判断index页面传过来的值
//        if (!StringUtils.isEmpty(username) && "123".equals(password)) {
//            //将username的值命名为loginUser赋值给session传到拦截器
//
//
//            session.setAttribute("loginUser", username);
//            return "redirect:/main.html";   //redirect:/main.html  重定向作用
//
//
//
//
//        } else {
//            //告诉用户登录失败
//            model.addAttribute("msg", "用户名或者密码错误！");
//            return "index";
//
//        }
    }
    //注册功能
    @RequestMapping("/toRegist")
    public String toRegist(){
        return "regist";
    }
    @RequestMapping("/user/regist")
    public String regist(User user){
        // 将用户名作为盐值
        ByteSource salt = ByteSource.Util.bytes(user.getUsername());
        /*
         * MD5加密：
         * 使用SimpleHash类对原始密码进行加密。
         * 第一个参数代表使用MD5方式加密
         * 第二个参数为原始密码
         * 第三个参数为盐值，即用户名
         * 第四个参数为加密次数
         * 最后用toHex()方法将加密后的密码转成String
         * */
        String newPs = new SimpleHash("MD5", user.getPassword(), salt, 1024).toHex();
        user.setPassword(newPs);
        userService.addUser(user);
        return "index";
    }
    //注销功能
    @RequestMapping("/user/logout")
    public String logout(HttpSession session) {
        //删除session会话
        session.invalidate();
        return "redirect:/index.html";
    }
}
