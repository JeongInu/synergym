package org.synergym.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.synergym.backend.entity.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    // deleteYn이 false인 댓글만 생성된 날짜 기준 조회
    List<Comment> findByPostIdAndDeleteYnFalseOrderByCreatedAtAsc(Integer postId);


}
