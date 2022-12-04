package com.example.study4test.service;

import com.example.study4test.contains.ResponseCommon;
import com.example.study4test.dto.FileDTO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ReadFileService {
    @Transactional
    FileDTO readFile(MultipartFile file, Long idDe) throws IOException;
}
