package com.collabo.taskmanagement.projectleader;


import com.collabo.taskmanagement.TODOList.TODOList;
import com.collabo.taskmanagement.TODOList.TODOListRepository;
import com.collabo.taskmanagement.TODOList.TODOListReq;
import com.collabo.taskmanagement.auth.AuthService;
import com.collabo.taskmanagement.auth.Member;
import com.collabo.taskmanagement.project.Project;
import com.collabo.taskmanagement.project.ProjectRepository;
import com.collabo.taskmanagement.team.Team;
import com.collabo.taskmanagement.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    TeamRepository teamRepository;


    @GetMapping("write/{P_idx}")
    public String write1(Model model, @PathVariable String P_idx){
        Member member = authService.loadUserByAuthority();
        model.addAttribute("member", member);
        Project project = projectRepository.selectOne(Integer.parseInt(P_idx));
        model.addAttribute("project", project);
        return "projectleader/write";
    }


    @PostMapping("write/{P_idx}")
    public String write(Model model, TODOListReq todoListReq, @PathVariable String P_idx) {
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

    @GetMapping("invite/{P_idx}")
    public String invite(Model model, @PathVariable String P_idx){
        Member member = authService.loadUserByAuthority();
        model.addAttribute("member", member);
        Project project = projectRepository.selectOne(Integer.parseInt(P_idx));
        model.addAttribute("project", project);
        return "projectleader/invite";
    }


    @PostMapping("invite/{P_idx}")
    @ResponseBody
    public Member invite(Model model, @PathVariable String P_idx, @RequestBody EmailJSON emailJSON){

        return authService.searchMemberByEmail(emailJSON.getEmail());
    }

    @PostMapping("invitemember/{P_idx}")
    public String inviteMember(@PathVariable String P_idx, @RequestParam String M_idx){
        Team team = Team.builder()
                .P_idx(Integer.parseInt(P_idx))
                .M_idx(Integer.parseInt(M_idx))
                .build();
        teamRepository.insert(team);
        return String.format("redirect:/project/index/%s", P_idx);
    }
}
