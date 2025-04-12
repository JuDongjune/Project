package com.example.myproject.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class TodoUpdateDto {
    private String todoContent;
    private LocalDateTime upadteDt;
}
