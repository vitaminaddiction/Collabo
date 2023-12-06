package com.collabo.taskmanagement.ProjectMain;

import com.collabo.taskmanagement.TODOList.TODOList;
import com.collabo.taskmanagement.TODOList.TODOListRepository;
import com.collabo.taskmanagement.auth.AuthService;
import com.collabo.taskmanagement.auth.Member;
import com.collabo.taskmanagement.project.Project;
import com.collabo.taskmanagement.project.ProjectRepository;
import com.collabo.taskmanagement.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProjectMainController {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    TODOListRepository todoListRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    AuthService authService;


    @GetMapping("project/index")
    public String index(){
        return "project/index";
    }

    @GetMapping("project/index/{P_index}")
    public String index(Model model, @PathVariable String P_index){
        Member member = authService.loadUserByAuthority();
        model.addAttribute("member", member);
        Project project = projectRepository.selectOne(Integer.parseInt(P_index));
        model.addAttribute("project", project);
        List<TODOList> TODOList = todoListRepository.listByState(project.getIdx(), 0);
        model.addAttribute("TODOList", TODOList);
        List<TODOList> ongoingTODOList = todoListRepository.listByState(project.getIdx(), 1);
        model.addAttribute("ongoingTODOList", ongoingTODOList);
        List<TODOList> doneTODOList = todoListRepository.listByState(project.getIdx(), 2);
        model.addAttribute("doneTODOList", doneTODOList);
        int countTeam = teamRepository.teamByProjectIndex(project.getIdx());
        model.addAttribute("countTeam", countTeam);

        return "project/index";
    }
}
