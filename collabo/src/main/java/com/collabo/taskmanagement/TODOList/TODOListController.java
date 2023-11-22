package com.collabo.taskmanagement.TODOList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Controller
public class TODOListController {
    @Autowired
    TODOListRepository todoListRepository;
    @GetMapping("/TODOList")
    public String TODOList(Model model){
        try {
            List<TODOList> tlist;
            for (int cIdx = 1; cIdx <= 4; cIdx++){
                tlist=todoListRepository.list(cIdx);
                model.addAttribute("tlist"+cIdx,tlist);
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return "TODOList/TODOList";
    }

    @GetMapping("/TODOListinfo")
    public String TODOListinfo(Model model, @RequestParam("idx") int Idx){
        try {
            TODOList tlistone;
            tlistone=todoListRepository.selectone(Idx);
            model.addAttribute("tlistone",tlistone);
        }catch (Exception e){
            System.out.println(e.toString());
        }
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

    @PostMapping("/TODOListupdate")
    @ResponseBody
    public String TODOListupdate(Model model,int[] idx){
        try {
            for (int i = 0; i < idx.length; i++){
                todoListRepository.update(2,1,idx[i]);
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return "TODOList/TODOList";
    }
}
