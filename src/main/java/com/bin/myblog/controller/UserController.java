package com.bin.myblog.controller;

import com.bin.myblog.pojo.User;
import com.bin.myblog.service.UserService;
import com.bin.myblog.util.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/friendlinks")
    public String toFriendlinks(){
        return "admin/friendlinks";
    }

    @GetMapping("/pictures")
    public String toPictures(){
        return "admin/pictures";
    }

    @PostMapping("/doLogin")
    public String doLogin(String username , String password, RedirectAttributes attributes,HttpSession httpSession){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, MD5Util.digest(password));
        //进行登录操作
        try {
            subject.login(token);
            User user =(User) subject.getSession().getAttribute("user");
            httpSession.setAttribute("user",user);
            return "admin/index";
        }catch (UnknownAccountException e){
            attributes.addFlashAttribute("message","用户不存在");
            return "redirect:/login";
        }catch (IncorrectCredentialsException e){
            attributes.addFlashAttribute("message","密码错误");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/";
    }
}
