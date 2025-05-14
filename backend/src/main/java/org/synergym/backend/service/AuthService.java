package org.synergym.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.synergym.backend.dto.LoginRequest;
import org.synergym.backend.dto.LoginResponse;
import org.synergym.backend.dto.SignupRequest;
import org.synergym.backend.entity.Role;
import org.synergym.backend.entity.User;
import org.synergym.backend.entity.UserGoal;
import org.synergym.backend.repository.UserGoalRepository;
import org.synergym.backend.repository.UserRepository;
import org.synergym.backend.security.JwtTokenProvider;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserGoalRepository userGoalRepository;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtTokenProvider jwtTokenProvider, UserGoalRepository userGoalRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider; // jwtTokenProvider 초기화
        this.userGoalRepository = userGoalRepository;
    }


    @Transactional
    public void signup(SignupRequest request) {
        // 이메일 중복 체크
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }
        if (userRepository.existsByUsername(request.getName())) {
            throw new RuntimeException("이미 존재하는 닉네임입니다.");
        }

        // User 엔티티 생성
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .username(request.getName())
                .birthDate(request.getBirthDate())
                .gender(request.getGender())
                .weight(request.getWeight())
                .height(request.getHeight())
                .fitnessLevel(request.getFitnessLevel())
                .role(Role.MEMBER)
                .build();

        userRepository.save(user);

        if (request.getUserGoal() != null && !request.getUserGoal().isEmpty()) {
            UserGoal userGoal = UserGoal.builder()
                    .user(user) // 생성된 User 엔티티 연결
                    .goal(request.getUserGoal())
                    .build();
            userGoalRepository.save(userGoal); // UserGoal 저장 (UserGoalRepository 필요)
        }

    }

//    @Transactional
//    public LoginResponse login(LoginRequest request) {
//        User user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new RuntimeException("가입되지 않은 이메일입니다."));
//
//        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//            throw new RuntimeException("잘못된 비밀번호입니다.");
//        }
//
//        try {
//            String email = user.getEmail();
//            Role role = user.getRole();
//
//            if (email == null || role == null) {
//                throw new RuntimeException("사용자 정보가 올바르지 않습니다.");
//            }
//
//            List<String> roles = Collections.singletonList(role.name());
//            // jwtTokenProvider가 null이 아니므로 NullPointerException 발생하지 않음
//            String token = jwtTokenProvider.createToken(email, roles);
//
//            return LoginResponse.builder()
//                    .id(user.getId())
//                    .email(email)
//                    .username(user.getUsername())
//                    .token(token)
//                    .role(role)
//                    .build();
//        } catch (Exception e) {
//            // 토큰 생성 외 다른 예외가 발생할 경우를 대비하여 로그를 남기거나 좀 더 구체적인 예외 처리를 고려할 수 있습니다.
//            throw new RuntimeException("토큰 생성 중 오류가 발생했습니다.", e);
//        }
//    }

    @Transactional
    public LoginResponse login(LoginRequest request) {
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());

        if (optionalUser.isEmpty()) {
            return LoginResponse.builder()
                    .success(false)
                    .message("Email not Registered")
                    .build();
        }

        User user = optionalUser.get();
        log.info("사용자 찾음: Email={}, Username={}", user.getEmail(), user.getUsername());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return LoginResponse.builder()
                    .success(false)
                    .message("Incorrect Password")
                    .build();
        }

        try {
            String email = user.getEmail();
            Role role = user.getRole();

            if (email == null || role == null) {
                return LoginResponse.builder()
                        .success(false)
                        .message("User information Invalid")
                        .build();
            }

            List<String> roles = Collections.singletonList(role.name());
            String token = jwtTokenProvider.createToken(email, roles);

            return LoginResponse.builder()
                    .email(email)
                    .username(user.getUsername())
                    .token(token)
                    .role(role)
                    .success(true)
                    .message("Login Success")
                    .build();
        } catch (Exception e) {
            return LoginResponse.builder()
                    .success(false)
                    .message("Error Creating Token.")
                    .build();
        }
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("loadUserByUser 1 name 호출: Email={}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.warn("loadUserByUsername - 사용자를 찾을 수 없습니다: Email={}", email);
                    return new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
                });
        log.info("loadUserByUsername - 사용자 찾음: Email={}, Username={}", user.getEmail(), user.getUsername());
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }

    // 현재 로그인한 사용자 정보 가져오기
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    }

    // 비밀번호 변경
    @Transactional
    public void changePassword(String email, String oldPassword, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("현재 비밀번호가 일치하지 않습니다.");
        }

        user.changePassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    // 이메일 중복 체크
    public boolean checkEmailDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }
}