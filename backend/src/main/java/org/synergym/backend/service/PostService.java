package org.synergym.backend.service;

import org.synergym.backend.dto.PostDTO;
import org.synergym.backend.entity.Post;
import org.synergym.backend.entity.User;

import java.util.List;

public interface PostService {
    Integer addPost(PostDTO postDTO, Integer userId);
    List<PostDTO> getAllPosts();
    PostDTO getPostById(Integer id);
    void updatePost(Integer id, PostDTO postDTO);
    void deletePost(Integer id);
    List<PostDTO> searchPosts(String keyword);
    List<PostDTO> getPostsByUser(Integer userId);

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
                .build();
    }

}
