package com.example.study4test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NhomCauHoi extends BaseEntity{
    private String noiDung;
    private int sttPhan;
    @OneToMany(mappedBy = "nhomCauHoi", fetch = FetchType.LAZY)
    private Collection<CauHoi> cauHois;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deThi_id")
    private DeThi deThi;

}
