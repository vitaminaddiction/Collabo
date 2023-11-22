package com.collabo.taskmanagement.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth")
public class AuthController {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AuthService authService;

    @GetMapping("login")
    public String login(){
        return "auth/login";
    }

    @GetMapping("register")
    public String register(Model model){
        return "auth/register";
    }

    @GetMapping("forgot-password")
    public String forgotpassword(){
        return "auth/forgot-password";
    }
}
