package com.example.study4test.contains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCommon {
    private String erosCode;
    private String erosType;
    private String message;
    private Object object;
}
