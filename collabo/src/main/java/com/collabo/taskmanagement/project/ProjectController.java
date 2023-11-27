package com.collabo.taskmanagement.project;


import com.collabo.taskmanagement.auth.Member;
import com.collabo.taskmanagement.auth.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("project")
public class ProjectController {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ProjectRepository projectRepository;


    @GetMapping("list")
    public String list(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String userEmail = authentication.getName(); // 현재 사용자의 이메일을 가져옵니다.
            model.addAttribute("userEmail", userEmail);
            System.out.println(authentication);
            System.out.println(userEmail);

            Member member = memberRepository.findByEmail(userEmail);

            System.out.println(member);
            List<Project> projects =  projectRepository.myProject(member);
            System.out.println(projects);

        } else {
            model.addAttribute("userEmail", "로그인되지 않았습니다.");
        }
        return "project/list";
    }
}
