package com.example.myproject.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentsWriteDto {
    @Schema(description = "게시물 ID", example = "1")
    private long DiaryId;

    @Schema(description = "댓글 내용", example = "댓글 남겨요.")
    private String content;

    @Schema(description = "댓글 작성자", example = "euijun")
    private String userId;
}
