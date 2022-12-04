package com.example.study4test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDetail  extends BaseEntity{



    private int stt;
   // @Type(type = "jsonb")
   // private List<String> da;
   private String da;

}
