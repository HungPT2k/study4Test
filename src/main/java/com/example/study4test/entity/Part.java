package com.example.study4test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Part extends BaseEntity {
    private String noiDung;
    private int sttPhan;
    @OneToMany(mappedBy = "nhomCauHoi", fetch = FetchType.LAZY)
    private List<Question> cauHois;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deThi_id")
    private Exam deThi;

}
