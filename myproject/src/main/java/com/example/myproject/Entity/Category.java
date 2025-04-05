package com.example.myproject.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "category")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @Column(name="category_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // 이 컬럼이 FK가 됨
    private User user;


    private String categoryName;

    private LocalDateTime createdDt;

    private LocalDateTime updatedDt;
}
