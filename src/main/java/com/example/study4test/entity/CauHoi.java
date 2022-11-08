package com.example.study4test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CauHoi extends BaseEntity {
    private int stt;
    private String texts;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nhomcauhoi_id")
    private NhomCauHoi nhomCauHoi;
    @OneToMany(mappedBy = "cauHoi", fetch = FetchType.LAZY)
    private Collection<DapAn> dapAns;
}
