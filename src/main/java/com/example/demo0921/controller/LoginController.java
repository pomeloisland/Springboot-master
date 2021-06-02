package com.example.demo0921.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @RequestMapping("/user/login")
    //具体业务
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            //Model回显数据     @RequestParam("username") 取值保险起见    拦截器会获取session存储的值去判断
            Model model, HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
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
    //注销功能
    @RequestMapping("/user/logout")
    public String logout(HttpSession session) {
//删除session会话
        session.invalidate();
        return "redirect:/index.html";
    }
}
