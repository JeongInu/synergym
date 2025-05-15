package org.synergym.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.synergym.backend.entity.Post;
import org.springframework.data.domain.Sort;


import java.util.List;
import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    // deleteyn이 false인 게시글만 조회하여 최신글로 정렬 메서드
    List<Post> findAllByDeleteYnFalse(Sort sort);

    // deleteyn이 false인 게시글 중 제목 또는 내용에 키워드가 포함된 게시글 검색 및 최신 글로 정렬 메서드
    List<Post> findByTitleContainingOrContentContainingAndDeleteYnFalse(String title, String content, Sort sort);

    // deleteyn이 false인 게시글 중 ID로 조회하는 메서드 추가
    Optional<Post> findByIdAndDeleteYnFalse(Integer id);


}

