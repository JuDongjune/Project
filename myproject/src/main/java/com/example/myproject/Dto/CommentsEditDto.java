package com.example.myproject.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentsEditDto {
    @Schema(description = "댓글 ID", example = "1")
    private long commentId;

    @Schema(description = "댓글 내용", example = "댓글 남겨요.")
    private String content;
}
