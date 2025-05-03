package com.example.myproject.Controller;

import com.example.myproject.Dto.RecommentEditDto;
import com.example.myproject.Dto.RecommentWriteDto;
import com.example.myproject.Entity.Recomment;
import com.example.myproject.Jwt.JwtUtil;
import com.example.myproject.Service.RecommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diary/recomment")
@Tag(name = "Recomment API", description = "대댓글 관련 API")
public class RecommentController {
    private final RecommentService recommentService;
    private final JwtUtil jwtUtil;

    public RecommentController(RecommentService recommentService, JwtUtil jwtUtil){
        this.recommentService = recommentService;
        this.jwtUtil = jwtUtil;
    }

    @Operation(summary = "대댓글 작성", description = "사용자가 대댓글을 작성합니다.")
    @PostMapping("/write")
    public ResponseEntity<String> writeRecomment(@RequestBody RecommentWriteDto dto, HttpServletRequest request){
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String userId = jwtUtil.getUserIdFromToken(token);
        dto.setUserId(userId);
        recommentService.postRecomment(dto);
        return ResponseEntity.ok("댓글이 작성되었습니다.");
    }

    @Operation(summary = "대댓글 수정", description = "사용자가 작성했던 대댓글을 수정합니다.")
    @PutMapping("/edit")
    public ResponseEntity<String> editRecomment(@RequestBody RecommentEditDto dto, HttpServletRequest request){
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String userId = jwtUtil.getUserIdFromToken(token);
        recommentService.putRecomment(dto, userId);
        return ResponseEntity.ok("댓글이 수정되었습니다.");
    }

    @Operation(summary = "대댓글 삭제", description = "사용자가 작성했던 대댓글을 삭제합니다.")
    @DeleteMapping("/delete/{recommentId}")
    public ResponseEntity<String> removeRecomment(@PathVariable long recommentId, HttpServletRequest request){
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String userId = jwtUtil.getUserIdFromToken(token);
        recommentService.deleteRecomment(recommentId, userId);
        return ResponseEntity.ok("댓글이 삭제되었습니다.");
    }

    @Operation(summary = "대댓글 조회", description = "대댓글이 생성일자 순으로 10개 한 페이지 꼴로 보여집니다.")
    @GetMapping("/view/{commentId}")
    public Page<Recomment> viewRecomments(@PathVariable long commentId, @RequestParam(defaultValue = "0") int page){
        return recommentService.getRecommentByPaged(commentId, page);
    }
}
