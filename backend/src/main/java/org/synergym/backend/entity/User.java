package org.synergym.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.synergym.backend.dto.UserDTO;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private String role;
    private String email;
    private Integer age;
    private String gender;
    private Float weight;
    private Float height;
    private String fitnessLevel;

    @CreationTimestamp
    private LocalDateTime createdAt;

    // DTO로 변환하는 메서드
    public UserDTO toDTO() {
        return UserDTO.builder()
                .id(this.id)
                .username(this.username)
                .password(this.password)
                .role(this.role)
                .email(this.email)
                .age(this.age)
                .gender(this.gender)
                .weight(this.weight)
                .height(this.height)
                .fitnessLevel(this.fitnessLevel)
                .createdAt(this.createdAt)
                .build();
    }

    // DTO 정보를 Entity에 업데이트하는 메서드
    public void updateFromDTO(UserDTO userDTO) {
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
        this.role = userDTO.getRole();
        this.email = userDTO.getEmail();
        this.age = userDTO.getAge();
        this.gender = userDTO.getGender();
        this.weight = userDTO.getWeight();
        this.height = userDTO.getHeight();
        this.fitnessLevel = userDTO.getFitnessLevel();
        this.createdAt = userDTO.getCreatedAt();
    }
}
