package com.example.study4test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question extends BaseEntity  {
    private int stt;
    private String texts;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nhomcauhoi_id")
    private Part nhomCauHoi;
    private String daAn1;
    private String daAn2;
    private String daAn3;
    private String daAn4;

}
