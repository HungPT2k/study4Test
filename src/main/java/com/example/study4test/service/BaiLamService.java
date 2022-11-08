package com.example.study4test.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BaiLamService {
    void readFile(MultipartFile file,Long idU, Long idDe) throws IOException;
    void KetQua(Long idB) throws IOException;
}
