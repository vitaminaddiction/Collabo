package com.collabo.taskmanagement.TODOList;


import com.collabo.taskmanagement.Result.Result;
import com.collabo.taskmanagement.Result.ResultReq;
import com.collabo.taskmanagement.auth.AuthService;
import com.collabo.taskmanagement.auth.Member;
import com.collabo.taskmanagement.project.Project;
import com.collabo.taskmanagement.project.ProjectRepository;
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
    @Autowired
    ProjectRepository projectRepository;

    @GetMapping("/takeTODOList/{pIdx}")
    public String takeTODOList(Model model, @PathVariable String pIdx){
        try {
            Member member = authService.loadUserByAuthority();
            model.addAttribute("member", member);
            Project project = projectRepository.selectOne(Integer.parseInt(pIdx));
            model.addAttribute("project", project);
            List<TODOList> tlist;
            for (int cIdx = 1; cIdx <= 4; cIdx++){
                tlist=todoListRepository.list(project.getIdx(),cIdx);
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
            Member member = authService.loadUserByAuthority();
            model.addAttribute("member", member);
            Project project = projectRepository.selectOne(Integer.parseInt(pIdx));
            model.addAttribute("project", project);
            int mIdx=member.getIdx();
            List<TODOList> tlist=todoListRepository.list(project.getIdx(),0);
            model.addAttribute("tlist",tlist);
            List<TODOList> mytlist=todoListRepository.mylist(project.getIdx(),mIdx);
            model.addAttribute("mytlist",mytlist);
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return "TODOList/TODOList";
    }

    @PostMapping("/TODOListupdate/{state}")
    public ResponseEntity<String> TODOListupdate(@PathVariable String state, @RequestBody Map<String, Object> data) {
        try {
            List<Integer> idxList = (List<Integer>) data.get("idx");
            int statenum=Integer.parseInt(state);
            Member member = authService.loadUserByAuthority();
            int M_idx=member.getIdx();
            for (int index : idxList) {
                todoListRepository.update(M_idx, statenum, index);
            }
            return ResponseEntity.ok("작업이 성공적으로 수행되었습니다.");
        }catch (Exception e){
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("오류가 발생했습니다.");
        }
    }

    @PostMapping("/TODOListdelete")
    public ResponseEntity<String> TODOListdelete(@RequestBody Map<String, Object> data) {
        try {
            List<Integer> idxList = (List<Integer>) data.get("idx");
            Member member = authService.loadUserByAuthority();
            for (int index : idxList) {
                todoListRepository.delete(index);
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
            Member member = authService.loadUserByAuthority();
            model.addAttribute("member", member);
            Project project = projectRepository.selectOne(Integer.parseInt("1"));
            model.addAttribute("project", project);
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
    public String TODOList1(Model model ){
        try {
            Member member = authService.loadUserByAuthority();
            model.addAttribute("member", member);
            Project project = projectRepository.selectOne(Integer.parseInt("1"));
            model.addAttribute("project", project);
            int p_Idx=1;
            int mIdx=member.getIdx();
            List<TODOList> tlist=todoListRepository.list(p_Idx,0);
            model.addAttribute("tlist",tlist);
            List<TODOList> mytlist=todoListRepository.mylist(p_Idx,mIdx);
            model.addAttribute("mytlist",mytlist);
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return "TODOList/TODOList";
    }
}
