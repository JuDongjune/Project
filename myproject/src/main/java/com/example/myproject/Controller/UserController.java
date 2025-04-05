package com.example.myproject.Controller;

import com.example.myproject.Dto.UserDto;
import com.example.myproject.Dto.UserLoginDto;
import com.example.myproject.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "User API", description = "유저 관련 API")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/user/signup")
    @Operation(summary = "회원가입", description = "사용자가 회원가입을 합니다.")
    public ResponseEntity<String> signup(@RequestBody UserDto dto) {
        userService.signup(dto);
        return ResponseEntity.ok("회원가입 완료!");
    }

    @PostMapping("/api/user/login")
    @Operation(summary = "로그인", description = "사용자가 로그인을 합니다.")
    public ResponseEntity<String> login(@RequestBody UserLoginDto dto){
        userService.login(dto);
        return ResponseEntity.ok("로그인 성공!");
    }
}