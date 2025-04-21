package com.example.myproject.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "user_id", length = 20)
    private String userId;

    @Column(name = "user_pw", length = 20, nullable = true) //소셜로그인 유저에겐 null임
    private String userPw;

    @Column(name = "user_name", length = 20, nullable = false)
    private String userName;

    @Column(name = "phone", length = 45)
    private String phone;

    @Column(name = "email") //소셜로그인 유저만 있음
    private String email;

    @Column(name = "isSocialLogin", columnDefinition = "boolean default false")
    private boolean isSocialLogin;

    @CreationTimestamp //자동생성
    @Column(name = "created_dt")
    private LocalDateTime createdDt;

    @UpdateTimestamp //업데이트 자동생성
    @Column(name = "updated_dt")
    private LocalDateTime updatedDt;

    @Column(name = "nickname", length = 45, nullable = false, unique = true)
    private String nickname;

    public static User of(String email){
        User user = new User();
        user.setUserId(email + "_kakao");
        user.email = email;
        user.setSocialLogin(true);
        return user;
    }
}
