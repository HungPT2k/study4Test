package com.example.study4test.controller;

import com.example.study4test.service.ReadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("home")
public class ReadFileController {
    @Autowired
    private ReadFileService readFileService;

    @PostMapping("/de")
    public void getInfoFile(@RequestPart MultipartFile file,@RequestParam(name = "id") Long idDe) throws IOException {
        readFileService.readFile(file,idDe);
    }
//    @PostMapping("/thangDiem")
//    public void getInfoFile(@RequestPart MultipartFile file,@RequestParam(name = "id") Long idDe) throws IOException {
//        readFileService.readFile(file,idDe);
//    }

}
