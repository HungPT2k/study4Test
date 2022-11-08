package com.example.study4test.serviceIml;
import com.example.study4test.entity.Users;
import com.example.study4test.exception.customException;
import com.example.study4test.repository.UserRepository;
import com.example.study4test.securitty.JwtTokenProvider;
import com.example.study4test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceIml implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;
  private final AuthenticationManager authenticationManager;
  @Override
  public String signin(String username, String password) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getUserRole());
    } catch (AuthenticationException e) {
      throw new customException("Tài khoản/ Mật khẩu cung cấp không hợp lệ!", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }
  @Override
  public String signup(Users appUser) {
    if (!userRepository.existsByUsername(appUser.getUsername())) {
      appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
      userRepository.save(appUser);
      return jwtTokenProvider.createToken(appUser.getUsername(), appUser.getUserRole());
    } else {
      throw new customException("Tài khoản đã được sử dụng!", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }
  @Override
  public void delete(String username) {
    userRepository.deleteByUsername(username);
  }
  @Override
  public Users search(String username) {
    Users appUser = userRepository.findByUsername(username);
    if (appUser == null) {
      throw new customException("Người dùng không tồn tại!", HttpStatus.NOT_FOUND);
    }
    return appUser;
  }
  @Override
  public Users whoami(HttpServletRequest req) {
    return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
  }
  @Override
  public String refresh(String username) {
    return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getUserRole());
  }

  @Override
  public List<Users> getAllUser() {
    return userRepository.findAll();
  }

}
