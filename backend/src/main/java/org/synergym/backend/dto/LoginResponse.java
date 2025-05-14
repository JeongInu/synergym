package org.synergym.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.synergym.backend.entity.Role;


@Data
@AllArgsConstructor
@Builder
@Getter
public class LoginResponse {
    private int id;
    private String email;
    private String username;
    private String token;
    private Role role;
    private boolean success;
    private String message;
}