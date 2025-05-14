package org.synergym.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.synergym.backend.dto.LoginRequest;
import org.synergym.backend.dto.LoginResponse;
import org.synergym.backend.dto.SignupRequest;
import org.synergym.backend.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;  // AuthenticationServiceException을 AuthService로 변경

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request) {
        log.info("회원가입 요청: {}", request.getEmail());
        authService.signup(request);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        log.info("로그인 요청: Email={}", request.getEmail());
        LoginResponse loginResponse = authService.login(request);
        log.info("로그인 결과: success={}, message={}, email={}, token={}",
                loginResponse.isSuccess(),
                loginResponse.getMessage(),
                loginResponse.getEmail(),
                loginResponse.getToken());

        return ResponseEntity.ok(loginResponse);
    }
}

