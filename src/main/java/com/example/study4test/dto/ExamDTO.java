package com.example.study4test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamDTO {
    private String dateCreate;
    private String userCreate;
    private String totalPart;
}
