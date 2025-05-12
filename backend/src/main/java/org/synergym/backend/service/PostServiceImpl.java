package org.synergym.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.synergym.backend.dto.PostDTO;
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
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;


    @Override
    @Transactional
    public Integer addPost(PostDTO postDTO, Integer userId) {
        // 사용자 ID로 User 객체 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        // User 객체를 dtoToEntity 메서드에 전달
        Post post = dtoToEntity(postDTO, user);
        Post savedPost = postRepository.save(post);
        return savedPost.getId();
    }



    @Override
    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostById(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + id));
        
        // 조회수 증가
        post.increaseViewCount();
        postRepository.save(post);
        
        return entityToDto(post);
    }

    @Override
    @Transactional
    public void updatePost(Integer id, PostDTO postDTO) {
        // 1. ID로 게시물 조회
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + id));

        // 2. 사용자 ID로 사용자 조회 (DTO에서 가져옴)
        User user = userRepository.findById(postDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + postDTO.getUserId()));

        // 3. 직접 엔티티의 변경 메서드 호출
        post.changeTitle(postDTO.getTitle());
        post.changeContent(postDTO.getContent());

        // 4. 변경된 엔티티 저장
        postRepository.save(post);
    }



    @Override
    @Transactional
    public void deletePost(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + id));

        // 현재는 인증된 사용자 정보를 직접 가져오는 로직이 없으므로
        // 인터페이스 일치를 위해 user 파라미터 제거
        // (실제로는 Spring Security를 통해 사용자 정보를 가져와야 함)

        // 소프트 삭제 메서드명 수정
        post.softDelete();
    }


    // Post 검색 기능 추가
    @Override
    public List<PostDTO> searchPosts(String keyword) {
        List<Post> posts = postRepository.findByTitleContainingOrContentContaining(keyword, keyword);
        return posts.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
    
    // 특정 사용자의 게시물 조회
    @Override
    public List<PostDTO> getPostsByUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        
        List<Post> posts = postRepository.findByUser(user);
        return posts.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }


}