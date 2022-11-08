package com.example.study4test.controller;

import com.example.study4test.service.ReadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("study4")
public class PostStudy4Controller {
    @Autowired
    private ReadFileService readFileService;

    @PostMapping("/post")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void getInfoFile(@RequestPart MultipartFile file,@RequestParam(name = "id") Long idDe) throws IOException {
        readFileService.readFile(file,idDe);
    }
  }


