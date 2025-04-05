package com.example.myproject.Entity;

import jakarta.persistence.*;
import lombok.*;
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

    @Column(name = "user_pw", length = 20, nullable = false)
    private String userPw;

    @Column(name = "user_name", length = 20, nullable = false)
    private String userName;

    @Column(name = "phone", length = 45)
    private String phone;

    @Column(name = "created_dt")
    private LocalDateTime createdDt;

    @Column(name = "updated_dt")
    private LocalDateTime updatedDt;

    @Column(name = "nickname", length = 45)
    private String nickname;
}
