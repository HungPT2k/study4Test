package com.example.study4test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data // Create getters and setters
@NoArgsConstructor
@AllArgsConstructor
public class Users {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
//  @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
//  @Column(unique = true, nullable = false)
  private String username;
//  @Column(unique = true, nullable = false)
  private String email;

//  @Size(min = 8, message = "Minimum password length: 8 characters")
  private String password;
  @ElementCollection(fetch = FetchType.EAGER)
  List<UserRole> userRoles;
  @OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
  private Collection<Exam> deThis;
  @OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
  private Collection<Assignment> baiLams;

}
