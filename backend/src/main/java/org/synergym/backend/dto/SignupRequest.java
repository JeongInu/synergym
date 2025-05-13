package org.synergym.backend.dto;

import lombok.*;
import org.synergym.backend.entity.UserGoal;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SignupRequest {
    private String email;
    private String password;
    private String name;
    private LocalDate birthDate;
    private String gender;
    private Float weight;
    private Float height;
    private String fitnessLevel;
    private String userGoal;
}

