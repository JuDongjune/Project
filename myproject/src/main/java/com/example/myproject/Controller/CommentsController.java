package com.example.myproject.Controller;

import com.example.myproject.Dto.CommentsEditDto;
import com.example.myproject.Dto.CommentsWriteDto;
import com.example.myproject.Entity.Comments;
import com.example.myproject.Jwt.JwtUtil;
import com.example.myproject.Service.CommentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diary/comments")
@Tag(name = "Comments API", description = "댓글 관련 API")
public class CommentsController {
    private final CommentsService commentsService;
    private final JwtUtil jwtUtil;

    public CommentsController(CommentsService commentsService, JwtUtil jwtUtil){
        this.commentsService = commentsService;
        this.jwtUtil = jwtUtil;
    }

    @Operation(summary = "댓글 작성 기능", description = "사용자가 게시글에 댓글을 작성합니다.")
    @PostMapping("/write")
    public ResponseEntity<String> writeComment(@RequestBody CommentsWriteDto dto, HttpServletRequest request){
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String userId = jwtUtil.getUserIdFromToken(token);
        dto.setUserId(userId);
        commentsService.writeReply(dto);
        return ResponseEntity.ok("댓글이 작성되었습니다.");
    }

    @Operation(summary = "댓글 수정 기능", description = "사용자가 작성한 댓글을 수정합니다.")
    @PutMapping("/edit")
    public ResponseEntity<String> editComment(@RequestBody CommentsEditDto dto, HttpServletRequest request){
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String userId = jwtUtil.getUserIdFromToken(token);
        commentsService.editReply(dto, userId);
        return ResponseEntity.ok("댓글이 수정되었습니다.");
    }

    @Operation(summary = "댓글 삭제 기능", description = "사용자가 작성한 댓글을 삭제합니다.")
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable long commentId, HttpServletRequest request){
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String userId = jwtUtil.getUserIdFromToken(token);
        commentsService.deleteReply(commentId, userId);
        return ResponseEntity.ok("댓글이 삭제되었습니다.");
    }

    @Operation(summary = "댓글 조회 기능", description = "사용자가 댓글을 생성일자 순으로 한 페이지당 10개씩 볼 수 있습니다.")
    @GetMapping("/view/{diaryId}")
    public Page<Comments> viewComments(@PathVariable long diaryId, @RequestParam(defaultValue = "0") int page){
        return commentsService.getReplyByPaged(diaryId, page);
    }
}
