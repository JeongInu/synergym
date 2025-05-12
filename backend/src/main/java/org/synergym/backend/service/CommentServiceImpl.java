package org.synergym.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.synergym.backend.dto.CommentDTO;
import org.synergym.backend.entity.Comment;
import org.synergym.backend.entity.Post;
import org.synergym.backend.entity.User;
import org.synergym.backend.repository.CommentRepository;
import org.synergym.backend.repository.PostRepository;
import org.synergym.backend.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CommentService 구현 클래스
 * 나중에 Spring Security 적용 시 인증된 사용자 정보를 SecurityContextHolder에서 가져오도록 수정 예정
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    /**
     * 댓글 추가 메서드
     * 현재는 userId를 직접 전달받지만, 추후 Spring Security 적용 시
     * SecurityContextHolder에서 인증된 사용자 정보를 가져오도록 수정 예정
     */
    @Override
    @Transactional
    public Integer addComment(String content, Integer postId) {
        // Spring Security 적용 시 현재 사용자 정보 가져올 예정
        // 임시로 하드코딩된 사용자 ID를 사용하거나 메서드 매개변수로 받을 수 있음
        Integer userId = getCurrentUserId(); // 현재는 구현 필요

        // 1. Post 객체 조회
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + postId));
        
        // 2. User 객체 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        
        // 3. Comment 객체 생성 및 저장
        Comment comment = Comment.builder()
                .content(content)
                .post(post)
                .user(user)
                .build();
        
        Comment savedComment = commentRepository.save(comment);
        return savedComment.getId();
    }

    // Spring Security 구현 전 임시 메서드
    private Integer getCurrentUserId() {
        // 임시 구현: 고정된 사용자 ID 반환 또는 예외 발생
        // Spring Security 구현 후 대체 예정
        throw new UnsupportedOperationException("Spring Security not yet implemented");
    }

    /**
     * 댓글 PostId별 조회 메서드
     */
    @Override
    public List<CommentDTO> getCommentsByPostId(Integer postId) {
        List<Comment> comments = commentRepository.findByPostIdAndIsDeletedFalse(postId);

        // Comment 엔티티를 DTO로 변환
        return comments.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }


    /**
     * 댓글 수정 메서드
     * 현재는 userId를 직접 전달받지만, 추후 Spring Security 적용 시
     * SecurityContextHolder에서 인증된 사용자 정보를 가져오도록 수정 예정
     */
    @Override
    @Transactional
    public void updateComment(Integer id, CommentDTO commentDTO) {
        // 임시로 고정된 사용자 ID 사용 (Spring Security 적용 전)
        Integer userId = 1; // 개발/테스트용 임시 사용자 ID


        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + id));

        // 작성자 검증 - Spring Security 적용 시 이 부분 변경 예정
        if (!comment.getUser().getId().equals(userId)) {
            throw new SecurityException("You don't have permission to update this comment");
        }

        comment.changeContent(commentDTO.getContent());
        commentRepository.save(comment);


    }


    /**
     * 댓글 삭제 메서드
     * 현재는 userId를 직접 전달받지만, 추후 Spring Security 적용 시
     * SecurityContextHolder에서 인증된 사용자 정보를 가져오도록 수정 예정
     */
    @Override
    @Transactional
    public void deleteComment(Integer id) {
        // Spring Security 적용 시 현재 사용자 정보 가져올 예정
        Integer userId = 1; // 개발/테스트용 임시 사용자 ID

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + id));

        // 작성자 검증 - Spring Security 적용 시 이 부분 변경 예정
        if (!comment.getUser().getId().equals(userId)) {
            throw new SecurityException("You don't have permission to delete this comment");
        }

        comment.softDelete();
    }


}