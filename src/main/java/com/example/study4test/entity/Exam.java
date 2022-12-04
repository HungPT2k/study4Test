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
public class Exam extends BaseEntity{

    private String dapAnDung;
    private String soLuongPhan;
    @Column(name = "thang_diem", columnDefinition = "TEXT NOT NULL")
    private String thangDiem;
    @OneToMany(mappedBy = "deThi", fetch = FetchType.LAZY)
    private Collection<Assignment> baiLams;
    @OneToMany(mappedBy = "deThi", fetch = FetchType.LAZY)
    private List<Part> nhomCauHois;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

}
