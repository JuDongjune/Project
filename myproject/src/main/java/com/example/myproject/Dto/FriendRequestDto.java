package com.example.myproject.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendRequestDto {
    @Schema(description = "사용자 유저 ID(요청 발신자)", example = "euijun")
    private String senderId;

    @Schema(description = "친구 유저 ID(요청 수신자)", example = "june")
    private String receiverId;
}
