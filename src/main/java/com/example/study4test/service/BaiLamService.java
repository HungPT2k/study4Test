package com.example.study4test.service;

import lombok.Data;
import org.apache.el.parser.ParseException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;

public interface BaiLamService {
    void readFile(MultipartFile file,Long idU, Long idDe) throws IOException;
    void KetQua(Long idB) throws IOException;
    String start() ;
}
