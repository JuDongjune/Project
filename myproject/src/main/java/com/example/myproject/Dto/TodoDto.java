package com.example.myproject.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class TodoDto {
    @Schema(example = "00000001" ,description = "투두ID")
    private String todoId;

    @Schema(example = "00000001", description = "카테고리ID")
    private String categoryId;

    @Schema(example = "jueunx0x",description = "사용자ID")
    private Long userId;

    @Schema(example = "스프링 공부", description = "투두 내용")
    private String todoContent;

    @Schema(example = "DONE", description = "투두 상태")
    private String isDone;

    @Schema(example = "2025-04-012",description = "생성일자")
    private LocalDateTime createDt;

    @Schema(example = "2025-04-12",description = "수정일자")
    private LocalDateTime updateDt;

    @Schema(example = "2025-04-12",description = "마감일자")
    private LocalDateTime dueDate;
}
