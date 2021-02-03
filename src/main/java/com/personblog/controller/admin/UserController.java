package com.personblog.controller.admin;


import com.personblog.entity.User;
import com.personblog.service.UserService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
@ApiModel(value = "用户登录控制层")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiModelProperty(value = "跳转登录页面")
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    /*
        将前端传递过来的用户名和密码给service进行检验核对，并放入session域中，
        session是全局的，登录后访问其他页面或者重开页面也是登录状态。

     */
    @ApiModelProperty(value = "登录校验，成功跳转首页，失败跳转登录页")
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                         HttpSession session, RedirectAttributes attributes){
         User user = userService.checkUser(username, password);
         if(user != null){
             //登录成功之后设置密码为空，并把user对象传到前端渲染
             user.setPassword(null);
             session.setAttribute("user", user);
             return "admin/index";
         }else{
             attributes.addFlashAttribute("message", "用户名或密码错误");
             return "redirect:/admin";
         }

    }

    @ApiModelProperty(value = "退出，返回登录页面")
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
