package org.synergym.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.synergym.backend.dto.PostDTO;
import org.synergym.backend.entity.User;
import org.synergym.backend.repository.UserRepository;
import org.synergym.backend.service.PostService;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class PostServiceTest {

    // 테스트 대상 서비스 주입
    @Autowired
    private PostService postService;

    @Autowired
    private UserRepository userRepository;

    // 테스트에 사용할 객체들
    private User testUser;
    private PostDTO testPostDTO;
    private Integer createdPostId;

    /**
     * 각 테스트 실행 전 필요한 테스트 데이터 설정
     */
    @BeforeEach
    void setUp() {

        // 테스트용 유저 생성
        testUser = User.builder()
                .username("testuser")
                .email("test@example.com")
                .build();
        testUser = userRepository.save(testUser);


        // 테스트용 DTO 생성
        testPostDTO = PostDTO.builder()
                .title("테스트 제목")
                .content("테스트 내용")
                .userId(testUser.getId())
                .viewCount(0)
                .build();

    }

    /**
     * 게시물 추가 기능 테스트
     * 새 게시물 DTO와 사용자 ID를 전달하면 게시물이 생성되고 ID가 반환되는지 확인
     */
    @Test
    @DisplayName("게시물 추가 테스트")
    void CreatPost() {
        // 테스트용 게시물 DTO 생성
        PostDTO newPost = PostDTO.builder()
                .title("새 게시물")
                .content("테스트 내용입니다")
                .userId(testUser.getId())
                .build();

        // 게시물 생성
        Integer postId = postService.addPost(newPost, testUser.getId());

        // 게시물 ID 출력
        System.out.println("생성된 게시글: " + postId);

    }

    @Test
    @DisplayName("게시물 전체 조회 테스트")
    void getAllPosts() {
        List<PostDTO> posts = postService.getAllPosts();

        // 콘솔 출력 (테스트 확인용)
        System.out.println("전체 게시물 목록:");
        posts.forEach(System.out::println);

    }

    @Test
    @DisplayName("ID로 게시물 조회 테스트")
    void getPostById() {
        // 게시물 등록 및 ID 획득
        createdPostId = postService.addPost(testPostDTO, testUser.getId());

        // 해당 ID로 게시글 조회
        PostDTO foundPost = postService.getPostById(createdPostId);

        // 콘솔 출력
        System.out.println("조회된 게시물: "+foundPost);

    }

    @Test
    @DisplayName("게시물 업데이트")
    void updatePost() {
        // 테스트 유저/게시글 생성 및 등록
        Integer postId = postService.addPost(testPostDTO, testUser.getId());

        // 게시물 수정용 DTO 생성
        PostDTO updateDTO = PostDTO.builder()
                .title("수정된 제목")
                .content("수정된 내용")
                .userId(testUser.getId())
                .viewCount(0)
                .build();

        // 게시물 수정
        postService.updatePost(postId, updateDTO);

        // 수정된 게시물 조회
        PostDTO updatedPost = postService.getPostById(postId);

        // 콘솔 출력
        System.out.println("수정된 게시물: " + updatedPost);
    }


    @Test
    @DisplayName("게시물 삭제")
    void deletePost() {
        // 1. 테스트 유저/게시글 생성 및 등록
        Integer postId = postService.addPost(testPostDTO, testUser.getId());

        // 2. 게시물 삭제
        postService.deletePost(postId);

        // 3. 삭제 후 게시글 조회 시 예외 발생(or null 반환) 검증
        try {
            PostDTO deletedPost = postService.getPostById(postId);
            // 만약 예외가 발생하지 않고 값이 나온다면 실패
            System.out.println("게시물 삭제 후 조회 시 예외가 발생해야 합니다. 조회결과: " + deletedPost);
        } catch (RuntimeException e) {
            // 예외 발생 시 테스트 통과
            System.out.println("게시물 삭제 확인됨: " + e.getMessage());
        }
    }



}