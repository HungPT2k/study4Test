package com.example.study4test.service;

import com.example.study4test.entity.DeThi;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DeThiService {
    List<DeThi> getAllDe();
    void Createde(MultipartFile file,MultipartFile file2,String soP) throws IOException;
}
