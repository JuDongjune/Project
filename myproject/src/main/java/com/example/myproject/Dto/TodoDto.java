package com.example.myproject.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor//생성자 자동 추가 어노테이션
@Getter @Setter
public class TodoDto {
    @Id
    @GeneratedValue
    @Schema(example = "00000001" ,description = "투두ID")
    private String todoId;

    @ManyToOne(fetch = FetchType.LAZY) //여러개 1개의 카텍고리 id에 맵핑 가능
    @JoinColumn(name = "category_id", nullable = false)  // FK로 category_id 컬럼 생성됨
    private String categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)  // FK로 user_id 컬럼 생성됨
    private String userId;

    @Schema(example = "스프링 공부", description = "투두 내용")
    private String todoContent;

    @Schema(example = "DONE", description = "투두 상태")
    private TodoStatus isDone;

    @Schema(example = "2025-04-012",description = "생성일자")
    private LocalDateTime createDt;

    @Schema(example = "2025-04-12",description = "수정일자")
    private LocalDateTime updateDt;

    @Schema(example = "2025-04-12",description = "마감일자")
    private LocalDateTime dueDate;
}
