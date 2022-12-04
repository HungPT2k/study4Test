package com.example.study4test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private int stt;
    private String texts;
    private String da1;
    private String da2;
    private String da3;
    private String da4;
}
