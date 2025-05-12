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

    public void changeUsername(String username) {
        this.username = username;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeEmail(String email) {
        this.email = email;
    }

    public void changeAge(Integer age) {
        this.age = age;
    }

    public void changeGender(String gender) {
        this.gender = gender;
    }

    public void changeWeight(Float weight) {
        this.weight = weight;
    }

    public void changeHeight(Float height) {
        this.height = height;
    }

    public void changeFitnessLevel(String fitnessLevel) {
        this.fitnessLevel = fitnessLevel;
    }
}
