package com.example.study4test.dto;

import com.example.study4test.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
  private Integer id;
  private String username;
  private String email;
  private String role;
  private String token;

  //private List<UserRole> userRole;
}
