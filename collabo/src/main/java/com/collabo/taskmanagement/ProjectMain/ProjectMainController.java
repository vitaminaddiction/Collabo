package com.collabo.taskmanagement.ProjectMain;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectMainController {
    @GetMapping("task/index")
    public String index(){
        return "task/index";
    }
}