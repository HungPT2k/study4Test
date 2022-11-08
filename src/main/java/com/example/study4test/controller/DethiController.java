package com.example.study4test.controller;

import com.example.study4test.entity.DeThi;
import com.example.study4test.service.DeThiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/de")
public class DethiController {
    @Autowired
    private DeThiService deThiService;
    private
    @GetMapping("")
    List<DeThi> getAllDe(){
        return deThiService.getAllDe();
    }
    @PostMapping("create")
    public void getInfoFile(@RequestPart MultipartFile file,@RequestPart MultipartFile file2, @RequestParam(name = "soP") String soP) throws IOException {
        deThiService.Createde(file,file2,soP);
    }
}
