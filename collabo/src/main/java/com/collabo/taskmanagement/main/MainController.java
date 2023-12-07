package com.collabo.taskmanagement.main;


import com.collabo.taskmanagement.auth.AuthService;
import com.collabo.taskmanagement.auth.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    AuthService authService;
    @GetMapping("/")
    public String index(Model model) {
        Member member = authService.loadUserByAuthority();
        model.addAttribute("member", member);
        return "main/index";
    }

    @GetMapping("/test")
    public String test() {
        return "main/test";
    }
}
