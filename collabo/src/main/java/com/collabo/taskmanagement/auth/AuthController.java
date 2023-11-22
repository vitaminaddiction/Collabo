package com.collabo.taskmanagement.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth")
public class AuthController {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AuthService authService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("login")
    public String login(){
        return "auth/login";
    }

    @GetMapping("register")
    public String register(){
        return "auth/register";
    }

    @PostMapping("register")
    public String register(MemberReq memberReq){
        Member member = Member.builder()
                .email(memberReq.getEmail())
                .name(memberReq.getName())
                .phone(memberReq.getPhone())
                .password(passwordEncoder.encode(memberReq.getPassword()))
                .build();

        String ret = authService.insert(member);

        if(ret.equals("duplicate")){

            return "auth/register";
        }
        return "redirect:/auth/login";
    }

    @GetMapping("forgot-password")
    public String forgotpassword(){
        return "auth/forgot-password";
    }
}
