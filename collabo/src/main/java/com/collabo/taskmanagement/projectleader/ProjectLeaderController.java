package com.collabo.taskmanagement.projectleader;


import com.collabo.taskmanagement.Result.Result;
import com.collabo.taskmanagement.Result.ResultRepository;
import com.collabo.taskmanagement.TODOList.TODOList;
import com.collabo.taskmanagement.TODOList.TODOListRepository;
import com.collabo.taskmanagement.TODOList.TODOListReq;
import com.collabo.taskmanagement.auth.AuthService;
import com.collabo.taskmanagement.auth.Member;
import com.collabo.taskmanagement.project.Project;
import com.collabo.taskmanagement.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("projectleader")
public class ProjectLeaderController {
    @Autowired
    AuthService authService;
    @Autowired
    TODOListRepository todoListRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ResultRepository resultRepository;


    @GetMapping("write/{P_idx}")
    public String write1(Model model, @PathVariable String P_idx){
        Member member = authService.loadUserByAuthority();
        model.addAttribute("member", member);
        Project project = projectRepository.selectOne(Integer.parseInt(P_idx));
        model.addAttribute("project", project);
        return "projectleader/write";
    }

    @GetMapping("list/{P_idx}")
    public String list(Model model,@PathVariable String P_idx){
        Member member = authService.loadUserByAuthority();
        model.addAttribute("member", member);
        Project project = projectRepository.selectOne(Integer.parseInt(P_idx));
        model.addAttribute("project", project);

        List<Result> list=resultRepository.list(project.getIdx());
        model.addAttribute("list", list);

        return "projectleader/list";
    }


    @PostMapping("write/{P_idx}")
    public String write(Model model, TODOListReq todoListReq, @PathVariable String P_idx) {

        System.out.println("--------------------------------");
        System.out.println(todoListReq);
        System.out.println("-------------------------------");
        Member member = authService.loadUserByAuthority();
        model.addAttribute("member", member);
        Project project = projectRepository.selectOne(Integer.parseInt(P_idx));
        model.addAttribute("project", project);
        TODOList todoList = TODOList.builder()
                .P_idx(Integer.parseInt(P_idx))
                .title(todoListReq.getTitle())
                .content(todoListReq.getContent())
                .deadline(todoListReq.getDeadline())
                .C_idx(todoListReq.getC_idx())
                .state(0)
                .M_idx(0)
                .build();
        todoListRepository.insertTODOList(todoList);
        return String.format("redirect:/project/index/%s", P_idx);
    }
}
