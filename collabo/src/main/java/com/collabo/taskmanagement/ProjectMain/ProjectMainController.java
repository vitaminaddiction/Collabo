package com.collabo.taskmanagement.ProjectMain;

import com.collabo.taskmanagement.TODOList.TODOListRepository;
import com.collabo.taskmanagement.project.Project;
import com.collabo.taskmanagement.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProjectMainController {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    TODOListRepository todoListRepository;

    @GetMapping("project/index")
    public String index(){
        return "project/index";
    }

    @GetMapping("project/index/{P_index}")
    public String index(Model model, @PathVariable String P_index){
        Project project = projectRepository.selectOne(Integer.parseInt(P_index));
        model.addAttribute("project", project);
        System.out.println(project);
        return "project/index";
    }
}
