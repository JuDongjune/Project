package com.example.myproject.Dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
@Getter @Setter
public class CategoryUpdateDto {
    private String categoryName;
    private LocalDateTime updateDt;
}
