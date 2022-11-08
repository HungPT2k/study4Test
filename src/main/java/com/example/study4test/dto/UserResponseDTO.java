package com.example.study4test.dto;
import com.example.study4test.entity.UserRole;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {
  private Integer id;
  private String username;
  private String email;

  private UserRole userRole;
}
