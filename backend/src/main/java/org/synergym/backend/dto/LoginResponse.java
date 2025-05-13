package org.synergym.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.synergym.backend.entity.Role;


@Data
@Builder
@AllArgsConstructor
public class LoginResponse {
    private Integer id;
    private String email;
    private String username;
    private String token;
    private Role role;

}

