package com.example.study4test.controller;

import com.example.study4test.dto.UserDataDTO;
import com.example.study4test.entity.Users;
import com.example.study4test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final ModelMapper modelMapper;
  @GetMapping("/all")
  List<Users> getAllUser(){
    return userService.getAllUser();
  }
  @PostMapping("/signin")
  public String login(@RequestParam String username, @RequestParam String password) {
    return userService.signin(username, password);
  }

  @PostMapping("/signup")
  public String signup( @RequestBody UserDataDTO user) {
    return userService.signup(modelMapper.map(user, Users.class));
  }

//  @DeleteMapping(value = "/{username}")
//  @PreAuthorize("hasRole('ROLE_ADMIN')")
//  public String delete(@ApiParam("Username") @PathVariable String username) {
//    userService.delete(username);
//    return username;
//  }
//
//  @GetMapping(value = "/{username}")
//  @PreAuthorize("hasRole('ROLE_ADMIN')")
//  @ApiOperation(value = "${UserController.search}", response = UserResponseDTO.class, authorizations = { @Authorization(value="apiKey") })
//  @ApiResponses(
//    value = {
//      @ApiResponse(code = 400, message = "Something went wrong"),
//      @ApiResponse(code = 403, message = "Access denied"),
//      @ApiResponse(code = 404, message = "The user doesn't exist"),
//      @ApiResponse(code = 500, message = "Expired or invalid JWT token")
//    }
//  )
//  public UserResponseDTO search(@ApiParam("Username") @PathVariable String username) {
//    return modelMapper.map(userService.search(username), UserResponseDTO.class);
//  }
//
//  @GetMapping(value = "/me")
//  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
//  @ApiOperation(value = "${UserController.me}", response = UserResponseDTO.class, authorizations = { @Authorization(value="apiKey") })
//  @ApiResponses(
//    value = {
//      @ApiResponse(code = 400, message = "Something went wrong"),
//      @ApiResponse(code = 403, message = "Access denied"),
//      @ApiResponse(code = 500, message = "Expired or invalid JWT token")
//    }
//  )
//  public UserResponseDTO whoami(HttpServletRequest req) {
//    return modelMapper.map(userService.whoami(req), UserResponseDTO.class);
//  }

  @GetMapping("/refresh")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
  public String refresh(HttpServletRequest req) {
    return userService.refresh(req.getRemoteUser());
  }
}
