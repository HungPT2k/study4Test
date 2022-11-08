package com.example.study4test.controller;

import com.example.study4test.service.BaiLamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/baiLam")
public class BaiLamController {
    @Autowired
    private BaiLamService baiLamService;
    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void PostBaiLam(@RequestPart MultipartFile file, @RequestParam(name = "idU") Long idU, @RequestParam(name = "idD") Long idD) throws IOException {
        baiLamService.readFile(file,idU,idD);
    }
    @GetMapping("/ketQua/{idb}")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public void getKetQua(@PathVariable(name = "idb") Long idb) throws IOException {
        baiLamService.KetQua(idb);
    }
    @GetMapping("/start")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public void lamBai(){
        baiLamService.start();
    }
}

