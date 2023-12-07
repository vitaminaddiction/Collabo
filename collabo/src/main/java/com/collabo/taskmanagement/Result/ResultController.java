package com.collabo.taskmanagement.Result;

import com.collabo.taskmanagement.TODOList.TODOListRepository;
import com.collabo.taskmanagement.auth.AuthService;
import com.collabo.taskmanagement.auth.Member;
import com.collabo.taskmanagement.project.Project;
import com.collabo.taskmanagement.project.ProjectRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

@Controller
public class ResultController {
    @Autowired
    ResultRepository resultRepository;
    @Autowired
    AuthService authService;
    @Autowired
    ProjectRepository projectRepository;

    @Value("${file.upload.path}")
    private String uploadPath;
    @GetMapping("/TODOListresult/{pIdx}/{TLIdx}")
    public String TODOListresult(Model model, ResultReq resultReq,@PathVariable String pIdx,@PathVariable String TLIdx){
        Member member = authService.loadUserByAuthority();
        model.addAttribute("member", member);
        Project project = projectRepository.selectOne(Integer.parseInt(pIdx));
        model.addAttribute("project", project);
        model.addAttribute("TLIdx", Integer.parseInt(TLIdx));
        model.addAttribute("first", "true");
        return "Result/TODOListresult";
    }
    @GetMapping("/TODOListresult")
    public String TODOListresulte(Model model, ResultReq resultReq){
        Member member = authService.loadUserByAuthority();
        model.addAttribute("member", member);
        Project project = projectRepository.selectOne(Integer.parseInt("1"));
        model.addAttribute("project", project);
        model.addAttribute("first", "true");
        return "Result/TODOListresult";
    }

    @PostMapping("/writeproc/{pIdx}/{TLidx}")
    public String writeproc(Model model,
                            @Valid ResultReq resultReq,
                            BindingResult result,
                            MultipartFile file, HttpServletRequest request,
                            @PathVariable String pIdx,
                            @PathVariable String TLidx) {

        String originalFilename = file.getOriginalFilename();

        File dest = new File(uploadPath+"/"+originalFilename);
        int TL_idx=Integer.parseInt(TLidx);
        int idx=Integer.parseInt(pIdx);

        try{
            file.transferTo(dest);
            // 파일이름을 boardReq에 저장
            resultReq.setFilename(originalFilename);
        }catch (Exception e){
            e.printStackTrace();
        }

        // 유효성 검사
        if (result.hasErrors()) {
            return "Result/TODOListresult";
        }
        /* 저장하는 부분 시작 */
        // boardReq 객체를 Board 객체로 변환
        Result resulted = Result.builder()
                .TL_idx(TL_idx)
                .filename(resultReq.getFilename())
                .content(resultReq.getContent())
                .title(resultReq.getTitle())
                .build();
        // db insert 하는 것
        resultRepository.insert(resulted);
        /* 저장하는 부분 끝 */
        return "redirect:/TODOList/"+idx;
    }

    @GetMapping("/attach/{filename}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable String filename)
            throws MalformedURLException {
//        FileEntity file = fileRepository.findById(id).orElse(null);
        UrlResource resource = new UrlResource("file:" + uploadPath+"/"+filename);
        String encodedFileName = UriUtils.encode(filename, StandardCharsets.UTF_8);
        // 파일 다운로드 대화상자가 뜨도록 하는 헤더를 설정해주는 것
        // Content-Disposition 헤더에 attachment; filename="업로드 파일명" 값을 준다.
        String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,contentDisposition).body(resource);
    }
}
