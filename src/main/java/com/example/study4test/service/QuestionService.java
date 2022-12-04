package com.example.study4test.service;

import com.example.study4test.dto.QuestionDTO;
import com.example.study4test.entity.Question;
import org.springframework.transaction.annotation.Transactional;

public interface QuestionService {



    @Transactional
    void saveCauHoi(Question cauHoi);

    QuestionDTO Update(Question cauHoi, Long idD, int phan, int stt);
}
