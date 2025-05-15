package org.synergym.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.synergym.backend.dto.CommentDTO;
import org.synergym.backend.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * 댓글 추가
     * POST /api/comments
     * 요청 본문: { "content": "댓글 내용", "postId": 1, "userId": 100 }
     * 로그인한 사용자만 가능 (userId를 요청 본문 또는 파라미터로 전달)
     */
    @PostMapping
    public ResponseEntity<Integer> addComment(@RequestBody CommentDTO commentDTO) {
        try {
            // CommentDTO에 content, postId, userId 필드가 있다고 가정
            Integer commentId = commentService.addComment(
                    commentDTO.getContent(),
                    commentDTO.getPostId(),
                    commentDTO.getUserId() // 요청 본문에서 userId를 가져옴 (임시 방편)
            );
            return new ResponseEntity<>(commentId, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            // 사용자 또는 게시물을 찾을 수 없는 경우 등 예외 발생 시 400 응답
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * 특정 게시물의 댓글 조회
     * GET /api/comments/post/{postId}
     */
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByPostId(@PathVariable Integer postId) {
        List<CommentDTO> comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    /**
     * 댓글 수정
     * PUT /api/comments/{id}
     * 요청 본문: { "content": "수정된 댓글 내용", "userId": 100 }
     * 인증된 사용자 중 댓글 작성자만 가능 (userId를 요청 본문 또는 파라미터로 전달)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateComment(
            @PathVariable Integer id,
            @RequestBody CommentDTO commentDTO
    ) {
        try {
            // CommentDTO에 content, userId 필드가 있다고 가정
            commentService.updateComment(
                    id,
                    commentDTO,
                    commentDTO.getUserId() // 요청 본문에서 userId를 가져옴 (임시 방편)
            );
            return ResponseEntity.ok().build();
        } catch (SecurityException e) {
            // 작성자가 아닌 경우 403 Forbidden 응답
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (RuntimeException e) {
            // 댓글을 찾을 수 없는 경우 404 응답
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 댓글 삭제 (소프트 딜리트)
     * DELETE /api/comments/{id}
     * 요청 파라미터: ?userId=100
     * 인증된 사용자 중 댓글 작성자만 가능 (userId를 요청 파라미터로 전달)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Integer id,
            @RequestParam Integer userId // 요청 파라미터에서 userId를 가져옴 (임시 방편)
    ) {
        try {
            commentService.deleteComment(id, userId);
            return ResponseEntity.ok().build();
        } catch (SecurityException e) {
            // 작성자가 아닌 경우 403 Forbidden 응답
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (RuntimeException e) {
            // 댓글을 찾을 수 없는 경우 404 응답
            return ResponseEntity.notFound().build();
        }
    }
}