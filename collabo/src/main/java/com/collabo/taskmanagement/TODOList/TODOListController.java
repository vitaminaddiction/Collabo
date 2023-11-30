package com.collabo.taskmanagement.TODOList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class TODOListController {
    @Autowired
    TODOListRepository todoListRepository;
    @GetMapping("/takeTODOList")
    public String takeTODOList(Model model){
        try {
            List<TODOList> tlist;
            int pIdx=1;
            for (int cIdx = 1; cIdx <= 4; cIdx++){
                tlist=todoListRepository.list(pIdx,cIdx);
                model.addAttribute("tlist"+cIdx,tlist);
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return "TODOList/takeTODOList";
    }

    @GetMapping("/TODOListresult")
    public String TODOListresult(){
        return "TODOList/TODOListresult";
    }

    @GetMapping("/TODOList")
    public String TODOList(Model model){
        try {
            int pIdx=1;
            int mIdx=1;
            List<TODOList> tlist=todoListRepository.list(pIdx,0);
            model.addAttribute("tlist",tlist);
            List<TODOList> mytlist=todoListRepository.mylist(mIdx);
            model.addAttribute("mytlist",mytlist);
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return "TODOList/TODOList";
    }

    @PostMapping("/TODOListupdate")
    public ResponseEntity<String> TODOListupdate(@RequestBody Map<String, Object> data) {
        try {
            System.out.println(1);
            List<Integer> idxList = (List<Integer>) data.get("idx");
            int M_idx=1;
            for (int index : idxList) {
                todoListRepository.update(M_idx, 1, index);
            }
            return ResponseEntity.ok("작업이 성공적으로 수행되었습니다.");
        }catch (Exception e){
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("오류가 발생했습니다.");
        }
    }
}
