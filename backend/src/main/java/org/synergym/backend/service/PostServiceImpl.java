package org.synergym.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.synergym.backend.dto.PostDTO;
import org.synergym.backend.entity.Post;
import org.synergym.backend.entity.User;
import org.synergym.backend.repository.PostRepository;
import org.synergym.backend.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;


    @Override
    @Transactional
    public Integer addPost(PostDTO postDTO, Integer userId) {
        // 사용자 ID로 User 객체 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다: " + userId));

        // User 객체를 dtoToEntity 메서드에 전달
        Post post = dtoToEntity(postDTO, user);
        Post savedPost = postRepository.save(post);
        return savedPost.getId();
    }



    @Override
    public List<PostDTO> getAllPosts() {
        // deleteyn이 false이면서 createdAt 필드를 기준으로 내림차순 (DESC) 정렬
        Sort sortByCreatedAtDesc = Sort.by(Sort.Direction.DESC, "createdAt");
        List<Post> posts = postRepository.findAllByDeleteYnFalse(sortByCreatedAtDesc);
        // Entity 리스트를 DTO 리스트로 변환 (PostService 인터페이스에 변환 메서드가 있다고 가정)
        return posts.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public PostDTO getPostById(Integer id) {
        Post post = postRepository.findByIdAndDeleteYnFalse(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없거나 이미 삭제된 게시글입니다."));


        // 조회수 증가
        post.increaseViewCount();
        postRepository.save(post);
        
        return entityToDto(post);
    }

    @Override
    @Transactional
    public void updatePost(Integer id, PostDTO postDTO, Integer userId) {
        //ID로 게시물 조회
        Post post = postRepository.findByIdAndDeleteYnFalse(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없거나 이미 삭제된 게시글입니다."));

        if( !post.getUser().getId().equals(userId) ){
            throw new RuntimeException("게시글 작성자만 수정할 수 있ㅅ브니다.");
        }

        //직접 엔티티의 변경 메서드 호출
        post.changeTitle(postDTO.getTitle());
        post.changeContent(postDTO.getContent());

        //변경된 엔티티 저장
        postRepository.save(post);
    }



    @Override
    @Transactional
    public void deletePost(Integer id) {
        // deleteyn이 false인 게시글 중 ID로 조회
        Post post = postRepository.findByIdAndDeleteYnFalse(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없거나 이미 삭제된 게시글입니다."));


        // 현재는 인증된 사용자 정보를 직접 가져오는 로직이 없으므로
        // 인터페이스 일치를 위해 user 파라미터 제거
        // (실제로는 Spring Security를 통해 사용자 정보를 가져와야 함)

        // 소프트 삭제 메서드명 수정
        post.softDelete();
        postRepository.save(post);

    }


    // Post 검색 기능 추가
    @Override
    public List<PostDTO> searchPosts(String keyword) {
        // deleteyn이 false인 게시글 중 제목 또는 내용에 키워드가 포함된 게시글을 최신 순으로 검색
        Sort sortByCreatedAtDesc = Sort.by(Sort.Direction.DESC, "createdAt");
        List<Post> posts = postRepository.findByTitleContainingOrContentContainingAndDeleteYnFalse(keyword, keyword, sortByCreatedAtDesc);
        return posts.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }




}