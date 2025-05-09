package org.synergym.backend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
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
}

