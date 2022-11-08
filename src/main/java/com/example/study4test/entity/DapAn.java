package com.example.study4test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DapAn extends BaseEntity{

    private int stt;
    private String noiDung;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cauhoi_id")
    private CauHoi cauHoi;

}
