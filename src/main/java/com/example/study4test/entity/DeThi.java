package com.example.study4test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeThi extends BaseEntity{

   private String dapAnDung;
    private String soLuongPhan;
    @Column(name = "thang_diem", columnDefinition = "TEXT NOT NULL")
    private String thangDiem;
    @OneToMany(mappedBy = "deThi", fetch = FetchType.LAZY)
    private Collection<BaiLam> baiLams;
    @OneToMany(mappedBy = "deThi", fetch = FetchType.LAZY)
    private Collection<NhomCauHoi> nhomCauHois;

}
