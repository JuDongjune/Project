package com.example.myproject.Entity;

import jakarta.persistence.*;
import lombok.*;
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

    @Column(name = "user_id",length = 20,nullable = false)
    private Long userId;

    @Column(name = "created_dt")
    private LocalDateTime createdDt;

    @Column(name = "updated_dt")
    private LocalDateTime updatedDt;

}
