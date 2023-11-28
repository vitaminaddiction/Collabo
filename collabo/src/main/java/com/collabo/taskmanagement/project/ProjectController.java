package com.collabo.taskmanagement.project;


import com.collabo.taskmanagement.auth.AuthService;
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
    AuthService authService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ProjectRepository projectRepository;


    @GetMapping("list")
    public String list(Model model){
        Member member = authService.loadUserByAuthority();
        List<Project> list = projectRepository.myProject(member);
        System.out.println(list);
        return "project/list";
    }
}
