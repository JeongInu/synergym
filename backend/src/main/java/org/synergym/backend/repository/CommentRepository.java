package org.synergym.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.synergym.backend.entity.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    // deleteYn이 false인 댓글만 조회(소프트 삭제되지 않은 것만)
    List<Comment> findByPostIdAndDeleteYnFalse(Integer postId);


}
