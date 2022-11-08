package com.example.study4test.service;

import com.example.study4test.entity.Users;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    public String signin(String username, String password) ;
    public String signup(Users appUser);
    public void delete(String username);
    public Users search(String username);
    public Users whoami(HttpServletRequest req);
    public String refresh(String username);
    List<Users> getAllUser();
}
