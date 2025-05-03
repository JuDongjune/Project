package com.example.myproject.Controller;

import com.example.myproject.Dto.FriendRequestDto;
import com.example.myproject.Entity.Friend;
import com.example.myproject.Jwt.JwtUtil;
import com.example.myproject.Service.FriendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/friendship")
@Tag(name = "Friend API", description = "친구 관련 API")
public class FriendController {
    private final FriendService friendService;
    private final JwtUtil jwtUtil;

    public FriendController(FriendService friendService, JwtUtil jwtUtil){
        this.friendService = friendService;
        this.jwtUtil = jwtUtil;
    }

    @Operation(summary = "팔로우 신청", description = "사용자가 다른 유저에게 팔로우 요청을 보냅니다.")
    @PostMapping("/request")
    public ResponseEntity<String> requestFollow(@RequestBody FriendRequestDto dto, HttpServletRequest request){
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String userId = jwtUtil.getUserIdFromToken(token);
        dto.setSenderId(userId);
        friendService.requestFollowing(dto);
        return ResponseEntity.ok("팔로우를 요청했습니다.");
    }

    @Operation(summary = "팔로우 요청 수락", description = "사용자가 받은 팔로우 요청을 수락합니다.")
    @PutMapping("/accept/{friendId}")
    public ResponseEntity<String> acceptFollow(@PathVariable Long friendId, HttpServletRequest request){
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String userId = jwtUtil.getUserIdFromToken(token);
        friendService.acceptFollowing(friendId, userId);
        return ResponseEntity.ok("팔로우 요청을 수락했습니다.");
    }

    @Operation(summary = "팔로우 목록 조회", description = "사용자의 팔로우 목록을 조회합니다.")
    @GetMapping("/view/{userId}")
    public Page<Friend> viewFollowList(@PathVariable String userId, @RequestParam(defaultValue = "0") int page){
        return friendService.getFriendList(userId, page);
    }

    @Operation(summary = "팔로우 취소", description = "사용자가 팔로우를 취소합니다.")
    @DeleteMapping("/delete/{friendId}")
    public ResponseEntity<String> deleteFollow(@PathVariable long friendId, HttpServletRequest request){
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String userId = jwtUtil.getUserIdFromToken(token);
        friendService.deleteFollowing(friendId, userId);
        return ResponseEntity.ok("팔로우를 취소(거절)했습니다.");
    }


}
