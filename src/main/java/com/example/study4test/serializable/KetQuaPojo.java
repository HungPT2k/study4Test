package com.example.study4test.serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KetQuaPojo implements Serializable {
    private static final long serialVersionUID = 1L;
    private int stt;
    private String da;

}
