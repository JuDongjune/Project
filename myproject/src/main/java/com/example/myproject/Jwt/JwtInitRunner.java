package com.example.myproject.Jwt;

import com.example.myproject.Entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JwtInitRunner implements CommandLineRunner {

    private final JwtUtil jwtUtil;

    public JwtInitRunner(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void run(String... args) {
        // 임시 username 으로 토큰 생성

        User user = new User();
        user.setUserId("testuser");
        user.setSocialLogin(false);
        user.setEmail("");
        String token = jwtUtil.createToken(user);

        System.out.println("개발용 임시 JWT 토큰 생성 완료:");
        System.out.println("Bearer " + token);
    }
}