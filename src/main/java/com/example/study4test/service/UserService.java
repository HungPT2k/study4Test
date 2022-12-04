package com.example.study4test.service;

import com.example.study4test.contains.ResponseCommon;
import com.example.study4test.dto.UserRequestDTO;
import com.example.study4test.dto.UserResponseDTO;
import com.example.study4test.entity.Users;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    UserResponseDTO signin(UserRequestDTO userRequest);

    public String signup(Users appUser);
    public void delete(String username);
    public Users search(String username);
    public Users whoami(HttpServletRequest req);
    public String refresh(String username);
    List<Users> getAllUser();
}
