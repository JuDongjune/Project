package com.example.myproject.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecommentEditDto {
    @Schema(description = "대댓글 ID", example = "1")
    private long recommentId;

    @Schema(description = "대댓글 내용", example = "저도 그렇게 생각합니다.")
    private String content;
}
