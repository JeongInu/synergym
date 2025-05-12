package org.synergym.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.synergym.backend.entity.Post;
import org.synergym.backend.entity.User;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByTitleContainingOrContentContaining(String title, String content);
    List<Post> findByWriter(User writer);

}
