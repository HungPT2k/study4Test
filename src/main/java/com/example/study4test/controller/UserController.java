package com.example.study4test.controller;

import com.example.study4test.contains.ErrorCode;
import com.example.study4test.contains.ResponseCommon;
import com.example.study4test.dto.UserDataDTO;
import com.example.study4test.dto.UserRequestDTO;
import com.example.study4test.entity.Users;
import com.example.study4test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("all")
    List<Users> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping("signin")
    public ResponseCommon login(@RequestBody UserRequestDTO userRequestDTO) {
        if (userService.signin(userRequestDTO).getEmail() == null) {
            return new ResponseCommon(ErrorCode.Code.LOGIN_INVALID, ErrorCode.Type.LOGIN_INVALID, ErrorCode.Message.LOGIN_INVALID, null);
        }
        return new ResponseCommon(ErrorCode.Code.SUCCESS, ErrorCode.Type.SUCCESS, ErrorCode.Message.SUCCESS, (userService.signin(userRequestDTO)));

    }

    @PostMapping("signup")
    public String signup(@RequestBody UserDataDTO user) {
        ModelMapper modelMapper = new ModelMapper();
        return userService.signup(modelMapper.map(user, Users.class));
    }

    @GetMapping("/refresh")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public String refresh(HttpServletRequest req) {
        return userService.refresh(req.getRemoteUser());
    }
}
