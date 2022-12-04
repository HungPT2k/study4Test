package com.example.study4test.contains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResultFinal {
    private String total;
    private String reading;
    private String speaking;
    private Object detail;

}