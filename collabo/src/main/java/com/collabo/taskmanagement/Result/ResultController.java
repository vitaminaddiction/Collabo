package com.collabo.taskmanagement.Result;

import com.collabo.taskmanagement.TODOList.TODOListRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
public class ResultController {
    @Autowired
    ResultRepository resultRepository;

    @Value("${file.upload.path}")
    private String uploadPath;
    @GetMapping("/TODOListresult")
    public String TODOListresult(Model model, ResultReq resultReq){
        model.addAttribute("first", "true");
        return "Result/TODOListresult";
    }

    @PostMapping("/writeproc")
    public String writeproc(Model model,
                            @Valid ResultReq resultReq,
                            BindingResult result,
                            MultipartFile file, HttpServletRequest request) {

        String originalFilename = file.getOriginalFilename();

        File dest = new File(uploadPath+"/"+originalFilename);

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
                .filename(resultReq.getFilename())
                .content(resultReq.getContent())
                .title(resultReq.getTitle())
                .build();
        // db insert 하는 것
        resultRepository.insert(resulted);
        /* 저장하는 부분 끝 */
        return "redirect:/TODOList";
    }
}
