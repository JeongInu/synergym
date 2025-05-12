package org.synergym.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.synergym.backend.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
