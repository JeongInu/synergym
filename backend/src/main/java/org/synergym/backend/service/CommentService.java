package org.synergym.backend.service;

import org.springframework.transaction.annotation.Transactional;
import org.synergym.backend.dto.CommentDTO;
import org.synergym.backend.entity.Comment;
import org.synergym.backend.entity.Post;
import org.synergym.backend.entity.User;

import java.util.List;

public interface CommentService {
    Integer addComment(String content, Integer postId, Integer userId);
    List<CommentDTO> getCommentsByPostId(Integer postId);
    void updateComment(Integer id, CommentDTO commentDTO, Integer userId);
    void deleteComment(Integer id, Integer userId);

    default Comment dtoToEntity(CommentDTO commentDTO, Post post, User user) {
        return Comment.builder()
                .id(commentDTO.getId())
                .content(commentDTO.getContent())
                .post(post)
                .user(user)
                .build();
    }


    default CommentDTO entityToDto(Comment comment) {
        return CommentDTO.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .postId(comment.getPost().getId())
                .userId(comment.getUser().getId())
                .username(comment.getUser().getUsername())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }


}
