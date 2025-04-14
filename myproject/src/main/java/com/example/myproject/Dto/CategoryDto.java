package com.example.myproject.Dto;
import com.example.myproject.Entity.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Getter @Setter
public class CategoryDto {
    @Schema(example = "00000001" ,description = "카테고리ID")
    private String categoryId;

    @Schema(example = "운동", description = "카테고리명")
    private String categoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)  // FK로 user_id 컬럼 생성됨
    private User user;

    @Schema(example = "2025-04-06",description = "생성일자")
    private LocalDateTime createDt;

    @Schema(example = "2025-04-06",description = "수정일자")
    private LocalDateTime updateDt;
}
