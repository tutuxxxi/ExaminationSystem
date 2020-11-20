package com.xxx.controller;


import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.util.StringUtil;
import com.xxx.pojo.User;
import com.xxx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    @PostMapping("/register")
    public String register(User user, HttpServletRequest request){
        HttpSession session = request.getSession();

        if(userService.addUser(user)){
            session.setAttribute("userInfo", user);
            session.setMaxInactiveInterval(20 * 60);

            if(user.getRole().equals("老师")){
                return "teacherPage";
            }else{
                return "studentPage";
            }
        }else{
            session.setAttribute("errorInfo", "注册失败，检查表单是否完整，若无法解决请联系管理员!");
            return "redirect:/register.jsp";
        }
    }

    @PostMapping("/login")
    public String login(String username, String password, HttpServletRequest request) throws Exception {

        User user = userService.getUser(null, username);
        HttpSession session = request.getSession();


        if(StringUtils.isEmpty(username) || StringUtil.isEmpty(password)){
            session.setAttribute("errorInfo", "登陆失败，账号或密码为空!");
            return "redirect:/login.jsp";
        }

        if(user.getPassword().equals(password)){
            session.setAttribute("userInfo", user);
            session.setMaxInactiveInterval(20 * 60);

            if(user.getRole().equals("老师")){
                return "teacherPage";
            }else{
                return "redirect: " + request.getContextPath() + "/exam/listExam";
            }

        }else{
            session.setAttribute("errorInfo", "登陆失败，检查账号密码是否正确，若无法解决请联系管理员!");
            return "redirect:/login.jsp";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login.jsp";
    }
}
