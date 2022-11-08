package com.example.study4test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data // Create getters and setters
@NoArgsConstructor
@AllArgsConstructor
public class Users extends BaseEntity{
//  @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
//  @Column(unique = true, nullable = false)
  private String username;
//  @Column(unique = true, nullable = false)
  private String email;

//  @Size(min = 8, message = "Minimum password length: 8 characters")
  private String password;

  @Enumerated(EnumType.ORDINAL)
  private UserRole userRole;
}
