package com.collabo.taskmanagement.TODOList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TODOListController {
    @Autowired
    TODOListRepository todoListRepository;
    @GetMapping("/TODOList")
    public String TODOList(Model model){
        try {
            List<TODOList> tlist = todoListRepository.list();
            model.addAttribute("tlist",tlist);
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return "TODOList/TODOList";
    }

    @GetMapping("/TODOListinfo")
    public String TODOListinfo(){
        return "TODOList/TODOListinfo";
    }

    @GetMapping("/TODOListresult")
    public String TODOListresult(){
        return "TODOList/TODOListresult";
    }

    @GetMapping("/TODOListtemp")
    public String TODOListtemp(){
        return "TODOList/TODOListtemp";
    }
}
