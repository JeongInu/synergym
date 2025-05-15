package org.synergym.backend.service;

import org.synergym.backend.dto.PostDTO;
import org.synergym.backend.entity.Post;
import org.synergym.backend.entity.User;

import java.util.List;

public interface PostService {
    Integer addPost(PostDTO postDTO, Integer userId);
    List<PostDTO> getAllPosts();
    PostDTO getPostById(Integer id);
    void updatePost(Integer id, PostDTO postDTO, Integer userId);
    void deletePost(Integer id);
    List<PostDTO> searchPosts(String keyword);

    // 변환 메서드
    default Post dtoToEntity(PostDTO dto, User user) {
        return Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .user(user)
                .viewCount(dto.getViewCount())
                .build();
    }

    default PostDTO entityToDto(Post post) {
        return PostDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .viewCount(post.getViewCount())
                .userId(post.getUser().getId())
                .username(post.getUser().getUsername()) // User 엔티티에서 username 가져와 설정
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

}
