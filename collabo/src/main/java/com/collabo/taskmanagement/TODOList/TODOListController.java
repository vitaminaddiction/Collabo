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
import java.util.Arrays;
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

    @GetMapping("/TODOListresult")
    public String TODOListresult(){
        return "TODOList/TODOListresult";
    }

    @GetMapping("/TODOListtemp")
    public String TODOListtemp(){
        return "TODOList/TODOListtemp";
    }

    @PostMapping("/TODOListupdate")
    public String TODOListupdate(@RequestParam("idx") String idxString){
        try {
            String[] idxArray = idxString.split(",");
            int[] intArray = Arrays.stream(idxArray)
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int index : intArray) {
                todoListRepository.update(1, 1, index);
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return "TODOList/TODOList";
    }
}
