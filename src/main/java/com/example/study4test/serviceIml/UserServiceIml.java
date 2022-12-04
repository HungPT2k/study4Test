package com.example.study4test.serviceIml;

import com.example.study4test.dto.UserRequestDTO;
import com.example.study4test.dto.UserResponseDTO;
import com.example.study4test.entity.Users;
import com.example.study4test.exception.customException;
import com.example.study4test.repository.UserRepository;
import com.example.study4test.securitty.JwtTokenProvider;
import com.example.study4test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
  public UserResponseDTO signin(UserRequestDTO userRequest) {
    try {
     String username= userRequest.getUsername();
     String password=userRequest.getPassword();
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      String token= jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getUserRoles());
      ModelMapper modelMapper = new ModelMapper();
      UserResponseDTO user = new UserResponseDTO();
      user.setUsername(userRepository.findByUsername(username).getUsername());
      user.setEmail(userRepository.findByUsername(username).getEmail());
      user.setRole(userRepository.findByUsername(username).getUserRoles().get(0).getAuthority());
      user.setToken(token);
      return user;
    } catch (AuthenticationException e) {
     return new UserResponseDTO(null,null,null,null,null);

    }
  }
  @Override
  public String signup(Users appUser) {
    if (!userRepository.existsByUsername(appUser.getUsername())) {
      appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
      appUser.setUserRoles(appUser.getUserRoles());
      userRepository.save(appUser);
      return jwtTokenProvider.createToken(appUser.getUsername(),  appUser.getUserRoles());
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
    return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getUserRoles());
  }

  @Override
  public List<Users> getAllUser() {
    return userRepository.findAll();
  }

}
