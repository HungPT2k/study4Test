package com.example.study4test.controller;

import com.example.study4test.service.BaiLamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/baiLam")
public class BaiLamController {
    @Autowired
    private BaiLamService baiLamService;
    @PostMapping("/create")
    public void getInfoFile(@RequestPart MultipartFile file, @RequestParam(name = "idU") Long idU, @RequestParam(name = "idD") Long idD) throws IOException {
        baiLamService.readFile(file,idU,idD);
    }
    @GetMapping("/ketQua/{idb}")
    public void getKetQua(@PathVariable(name = "idb") Long idb) throws IOException {
        baiLamService.KetQua(idb);
    }
    @GetMapping("/start")
    public void lamBai(){
        baiLamService.start();
    }
}

