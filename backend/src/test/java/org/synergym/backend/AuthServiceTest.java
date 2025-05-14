package org.synergym.backend;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.synergym.backend.config.SecurityConfig;
import org.synergym.backend.dto.LoginRequest;
import org.synergym.backend.dto.LoginResponse;
import org.synergym.backend.dto.SignupRequest;
import org.synergym.backend.entity.UserGoal;
import org.synergym.backend.repository.UserRepository;
import org.synergym.backend.service.AuthService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Import(SecurityConfig.class)
class AuthServiceTest {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private SignupRequest signupRequest;
    private LoginRequest loginRequest;

    @BeforeEach
    void setUp() {
        // 테스트에 사용할 데이터 초기화
        UserGoal userGoal = UserGoal.builder()
                .goal("체중 감량")
                .build();

        signupRequest = SignupRequest.builder()
                .email("kimchijjim@naver.com")
                .password("masitda")
                .name("김치찜")
                .birthDate(LocalDate.of(2000, 10, 11))
                .gender("F")
                .weight(75.0f)
                .height(180.0f)
                .fitnessLevel("BEGINNER")
                .userGoal(userGoal.getGoal())  // UserGoal 객체로 설정
                .build();

        loginRequest = new LoginRequest();
        loginRequest.setEmail("test3@example.com");
        loginRequest.setPassword("password123!");

    }

    @Test
    @DisplayName("회원가입 테스트")
    void testSignup() {
        // given
        log.info("회원가입 테스트 시작: {}", signupRequest.getEmail());
        
        // when
        authService.signup(signupRequest);
        
        // then
        var user = userRepository.findByEmail(signupRequest.getEmail());
        assertTrue(user.isPresent(), "가입된 사용자가 데이터베이스에 존재해야 합니다");
    }

    @Test
    @DisplayName("이메일 중복 체크 테스트")
    void testEmailDuplicateCheck() {
        // given
        authService.signup(signupRequest);
        
        // when
        boolean emailExists = authService.checkEmailDuplicate("test@example.com");
        
        // then
        log.info("이메일 중복 체크 결과: {}", emailExists);
        assertTrue(emailExists, "가입된 이메일이 존재해야 합니다");
    }

    @Test
    @DisplayName("로그인 테스트")
    void testLogin() {
        // given
        authService.login(loginRequest);
        
        // when
        log.info("로그인 테스트 시작: {}", loginRequest.getEmail());
        LoginResponse loginResponse = authService.login(loginRequest);
        
        // then
        assertAll(
            () -> assertNotNull(loginResponse, "로그인 응답이 null이 아니어야 합니다"),
            () -> assertNotNull(loginResponse.getToken(), "JWT 토큰이 생성되어야 합니다"),
            () -> assertEquals(loginRequest.getEmail(), loginResponse.getEmail(), "로그인된 이메일이 일치해야 합니다")
        );
        log.info("로그인 성공: token={}", loginResponse.getToken());
    }
}