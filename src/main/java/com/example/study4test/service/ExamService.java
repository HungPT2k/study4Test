package com.example.study4test.service;

import com.example.study4test.contains.ResponseCommon;
import com.example.study4test.dto.ExamDTO;
import com.example.study4test.dto.QuestionDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ExamService {
    List<QuestionDTO> ExamDetail(Long id);
    ExamDTO CreateExam(MultipartFile file2, String soP, Long id) throws IOException;

}
