package com.example.myproject.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public enum TodoStatus {
    WAIT("대기"),
    DONE("완료");
    private final String description;
}
