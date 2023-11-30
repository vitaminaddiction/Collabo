package com.collabo.taskmanagement.project;


import com.collabo.taskmanagement.auth.AuthService;
import com.collabo.taskmanagement.auth.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("project")
public class ProjectController {
    @Autowired
    AuthService authService;
    @Autowired
    ProjectRepository projectRepository;


    @GetMapping("myList")
    public String myList(Model model, @RequestParam(required = false, defaultValue = "1") int pageNum){

        model.addAttribute("pageNum", pageNum);

        pageNum = (pageNum - 1) * 8;
        Member member = authService.loadUserByAuthority();

        Map<String, Object> params = new HashMap<>();
        params.put("member", member);
        params.put("pageNum", pageNum);

        List<Project> list = projectRepository.myProject(params);
        int countRow = projectRepository.countRow(member);

        model.addAttribute("list", list);
        model.addAttribute("countRow", countRow);

        int countPage = (int)Math.ceil(countRow / 8.0);

        model.addAttribute("countPage", countPage);
        return "project/myList";
    }

    @GetMapping("list")
    public String list(Model model, @RequestParam(required = false, defaultValue = "1") int pageNum){
        model.addAttribute("pageNum", pageNum);

        pageNum = (pageNum - 1) * 8;

        List<Project> list = projectRepository.list(pageNum);
        int countRow = projectRepository.countRowPublic();

        model.addAttribute("list", list);
        model.addAttribute("countRow", countRow);

        int countPage = (int)Math.ceil(countRow / 8.0);

        model.addAttribute("countPage", countPage);
        return "project/list";
    }
}
