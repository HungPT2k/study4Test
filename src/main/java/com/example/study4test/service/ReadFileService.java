package com.example.study4test.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ReadFileService {
    void readFile(MultipartFile file,Long idDe) throws IOException;
}
