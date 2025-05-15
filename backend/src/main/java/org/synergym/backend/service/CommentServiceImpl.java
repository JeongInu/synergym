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


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    /**
     * 댓글 추가 메서드
     */
    @Transactional
    @Override
    public Integer addComment(String content, Integer postId, Integer userId) {
        // 로그인한 사용자만 댓글 작성 가능
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + postId));
        Comment comment = Comment.builder()
                .content(content)
                .post(post)
                .user(user)
                .build();
        post.addComment(comment);
        Comment savedComment = commentRepository.save(comment);
        return savedComment.getId();
    }


    /**
     * 댓글 PostId별 조회 메서드
     */
    @Override
    public List<CommentDTO> getCommentsByPostId(Integer postId) {
        List<Comment> comments = commentRepository.findByPostIdAndDeleteYnFalseOrderByCreatedAtAsc(postId);

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
    @Transactional
    @Override
    public void updateComment(Integer id, CommentDTO commentDTO, Integer userId) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + id));
        // 작성자만 수정 가능
        if (!comment.getUser().getId().equals(userId)) {
            throw new SecurityException("댓글 작성자만 수정할 수 있습니다.");
        }
        comment.changeContent(commentDTO.getContent());
        commentRepository.save(comment);
    }


    /**
     * 댓글 삭제 메서드
     * 현재는 userId를 직접 전달받지만, 추후 Spring Security 적용 시
     * SecurityContextHolder에서 인증된 사용자 정보를 가져오도록 수정 예정
     */
    @Transactional
    @Override
    public void deleteComment(Integer id, Integer userId) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + id));
        // 작성자만 삭제 가능
        if (!comment.getUser().getId().equals(userId)) {
            throw new SecurityException("댓글 작성자만 삭제할 수 있습니다.");
        }

        // 소프트 삭제 처리
        comment.softDelete();

        // 변경사항 저장
        commentRepository.save(comment);

    }


}