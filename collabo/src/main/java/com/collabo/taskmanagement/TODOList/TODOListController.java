package com.collabo.taskmanagement.TODOList;


import com.collabo.taskmanagement.Result.Result;
import com.collabo.taskmanagement.Result.ResultReq;
import com.collabo.taskmanagement.auth.AuthService;
import com.collabo.taskmanagement.auth.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

@Controller
public class TODOListController {
    @Autowired
    TODOListRepository todoListRepository;
    @Autowired
    AuthService authService;

    @GetMapping("/takeTODOList/{pIdx}")
    public String takeTODOList(Model model, @PathVariable String pIdx){
        try {
            List<TODOList> tlist;
            int p_Idx=Integer.parseInt(pIdx);
            for (int cIdx = 1; cIdx <= 4; cIdx++){
                tlist=todoListRepository.list(p_Idx,cIdx);
                model.addAttribute("tlist"+cIdx,tlist);
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return "TODOList/takeTODOList";
    }

    @GetMapping("/TODOList/{pIdx}")
    public String TODOList(Model model, @PathVariable String pIdx){
        try {
            int p_Idx=Integer.parseInt(pIdx);
            Member member = authService.loadUserByAuthority();
            int mIdx=member.getIdx();
            List<TODOList> tlist=todoListRepository.list(p_Idx,0);
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
            List<Integer> idxList = (List<Integer>) data.get("idx");
            Member member = authService.loadUserByAuthority();
            int M_idx=member.getIdx();
            for (int index : idxList) {
                todoListRepository.update(M_idx, 1, index);
            }
            return ResponseEntity.ok("작업이 성공적으로 수행되었습니다.");
        }catch (Exception e){
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("오류가 발생했습니다.");
        }
    }

    //기본
    @GetMapping("/takeTODOList")
    public String takeTODOList1(Model model){
        try {
            List<TODOList> tlist;
            int p_Idx=1;
            for (int cIdx = 1; cIdx <= 4; cIdx++){
                tlist=todoListRepository.list(p_Idx,cIdx);
                model.addAttribute("tlist"+cIdx,tlist);
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return "TODOList/takeTODOList";
    }
    @GetMapping("/TODOList")
    public String TODOList1(Model model){
        try {
            int p_Idx=1;
            Member member = authService.loadUserByAuthority();
            int mIdx=member.getIdx();
            List<TODOList> tlist=todoListRepository.list(p_Idx,0);
            model.addAttribute("tlist",tlist);
            List<TODOList> mytlist=todoListRepository.mylist(mIdx);
            model.addAttribute("mytlist",mytlist);
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return "TODOList/TODOList";
    }
}
