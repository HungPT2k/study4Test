package com.example.study4test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String thangDiem;
    @OneToMany(mappedBy = "deThi", fetch = FetchType.LAZY)
    private Collection<BaiLam> baiLams;
    @OneToMany(mappedBy = "deThi", fetch = FetchType.LAZY)
    private Collection<NhomCauHoi> nhomCauHois;

}
