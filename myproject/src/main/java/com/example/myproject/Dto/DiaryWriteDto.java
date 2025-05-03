package com.example.myproject.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiaryWriteDto {
    @Schema(description = "글 작성자 ID", example = "euijun")
    private String userId;

    @Schema(description = "글 제목", example = "오늘은 250407")
    private String title;

    @Schema(description = "글 내용", example = "오늘은 25년 4월 7일 월요일이다. 시간은 오후 3시40분 나른하다.")
    private String content;
}
