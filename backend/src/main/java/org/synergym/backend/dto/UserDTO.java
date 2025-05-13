package org.synergym.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.synergym.backend.entity.Role;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private Role role;
    private String email;
    private LocalDate birthDate;  // age를 birthDate로 변경
    private String gender;
    private Float weight;
    private Float height;
    private String fitnessLevel;
}