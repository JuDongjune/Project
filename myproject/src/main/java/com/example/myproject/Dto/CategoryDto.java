package com.example.myproject.Dto;

import com.example.myproject.Entity.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter @Setter
public class CategoryDto {
    @Schema(example = "00000001" ,description = "카테고리ID")
    private String categoryId;

    @Schema(example = "운동", description = "카테고리명")
    private String categoryName;

    @Schema(example = "jueunx0x",description = "사용자ID")
    private Long userId;

    @Schema(example = "2025-04-06",description = "생성일자")
    private LocalDateTime createDt;

    @Schema(example = "2025-04-06",description = "수정일자")
    private LocalDateTime updateDt;
}
