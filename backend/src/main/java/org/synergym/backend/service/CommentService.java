package org.synergym.backend.service;

import org.synergym.backend.dto.CommentDTO;
import org.synergym.backend.entity.User;

import java.util.List;

public interface CommentService {
    Integer addComment(String content, Integer postId);
    List<CommentDTO> getCommentsByPostId(Integer postId);
    CommentDTO getCommentById(Integer id);
    void updateComment(Integer id, String content);
    void deleteComment(Integer id);

}
