package com.example.study4test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assignment extends BaseEntity {
    private String thoiGian;
  private String cauTraLoi;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dethi_id")
    private Exam deThi;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

}
