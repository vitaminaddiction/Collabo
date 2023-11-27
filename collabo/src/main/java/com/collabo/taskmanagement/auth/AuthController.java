package com.collabo.taskmanagement.auth;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("/user-email")
    public String getUserEmail(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 사용자가 로그인되어 있으면
        if (authentication != null && authentication.isAuthenticated()) {
            String userEmail = authentication.getName(); // 현재 사용자의 이메일을 가져옵니다.
            model.addAttribute("userEmail", userEmail);
            System.out.println(authentication);
            System.out.println(userEmail);
            System.out.println(memberRepository.findByEmail(userEmail));
        } else {
            model.addAttribute("userEmail", "로그인되지 않았습니다.");
        }

        return "project/index";
    }



    @GetMapping("logout")
    public String logout(Authentication authentication, HttpServletRequest request){
        System.out.println(authentication);
        System.out.println(authentication.getPrincipal());
        // jsp 에서 session 안에 있는 모든 내용 삭제..
        //request.getSession().invalidate();

        return "redirect:/";
    }
}
