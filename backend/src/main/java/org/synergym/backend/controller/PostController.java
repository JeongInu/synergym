package org.synergym.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.synergym.backend.dto.PostDTO;
import org.synergym.backend.repository.PostRepository;
import org.synergym.backend.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;
    /**
     * 모든 게시물 조회
     * GET /posts
     * @return 게시물 목록
     */
    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    /**
     * 특정 ID로 게시물 조회
     * GET /posts/{id}
     * @param id 게시물 ID
     * @return 조회된 게시물 정보
     */
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer id) {
        try {
            // 게시물 조회 (이 때 조회수도 자동으로 증가)
            PostDTO post = postService.getPostById(id);
            return ResponseEntity.ok(post);
        } catch (RuntimeException e) {
            // 게시물을 찾을 수 없거나 이미 삭제된 경우 404 응답
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 키워드로 게시물 검색
     * GET /posts/search?keyword={keyword}
     * @param keyword 검색 키워드
     * @return 검색된 게시물 목록
     */
    @GetMapping("/posts/search")
    public ResponseEntity<List<PostDTO>> searchPosts(@RequestParam String keyword) {
        List<PostDTO> posts = postService.searchPosts(keyword);
        return ResponseEntity.ok(posts);
    }

    /**
     * 새로운 게시물 생성
     * POST /posts
     * @param postDTO 게시물 정보
     * @param userId 사용자 ID
     * @return 생성된 게시물 ID
     */
    @PostMapping("/posts")
    public ResponseEntity<Integer> createPost(@RequestBody PostDTO postDTO, @RequestParam Integer userId) {
        try {
            Integer postId = postService.addPost(postDTO, userId);
            return ResponseEntity.ok(postId);
        } catch (RuntimeException e) {
            // 사용자를 찾을 수 없는 경우 등 예외 발생 시 400 응답
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * 게시물 정보 수정
     * PUT /posts/{id}
     * @param id 게시물 ID
     * @param postDTO 수정할 게시물 정보
     * @return 성공 시 200 응답
     */
    @PutMapping("/posts/{id}")
    public ResponseEntity<Void> updatePost(@PathVariable Integer id, @RequestBody PostDTO postDTO, @RequestParam Integer userId) {
        try {
            postService.updatePost(id, postDTO, userId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            // 게시물이나 사용자를 찾을 수 없는 경우 404 응답
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 게시물 삭제 (소프트 딜리트)
     * DELETE /posts/{id}
     * @param id 게시물 ID
     * @return 성공 시 200 응답
     */
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {
        try {
            postService.deletePost(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            // 게시물을 찾을 수 없는 경우 404 응답
            return ResponseEntity.notFound().build();
        }
    }
}
