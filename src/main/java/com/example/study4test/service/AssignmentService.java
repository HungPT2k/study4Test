package com.example.study4test.service;

import com.example.study4test.contains.ResponseResultFinal;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AssignmentService {
    void readFile(MultipartFile file,Long idU, Long idDe) throws IOException;
    ResponseResultFinal ResultFinal(Long idB) throws IOException;
    String start() ;
}
