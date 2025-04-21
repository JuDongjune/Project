package com.example.myproject.Jwt;

import com.example.myproject.Entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 임시 키

    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 2; // 2시간

    // 토큰 생성
    public String createToken(User user) {
        JwtBuilder builder = Jwts.builder()
                .setSubject(user.getUserId())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key);

        //소셜 로그인 사용자만 email 추가
        if(user.isSocialLogin() && user.getEmail() != null){
            builder.claim("email", user.getEmail());
        }

        return builder.compact();
    }

    // 토큰에서 유저명 추출
    public String getUserIdFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // 토큰에서 email 추출
    public String getEmailFromToken(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Object emailObj = claims.get("email");
        return emailObj != null ? emailObj.toString() : null;
    }


    // 토큰 유효성 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}