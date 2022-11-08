package com.example.study4test.dto;
import com.example.study4test.entity.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDataDTO {

  private String username;


  private String email;

  private String password;

  private UserRole userRole;
}
