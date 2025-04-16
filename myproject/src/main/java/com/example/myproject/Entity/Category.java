package com.example.myproject.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "category")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @Column(name = "category_id",length = 20,nullable = false)
    private String  categoryId;

    @Column(name = "category_name",length = 45)
    private String  categoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp //자동생성
    @Column(name = "created_dt")
    private LocalDateTime createdDt;

    @UpdateTimestamp //업데이트 자동생성
    @Column(name = "updated_dt")
    private LocalDateTime updatedDt;

}
