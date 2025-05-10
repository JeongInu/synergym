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

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 90fa1dc (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> d09733c (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 666814f (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 9f3ef93 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> ff4e567 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
>>>>>>> 5974d05 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
>>>>>>> 61f3472 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
>>>>>>> f52c59f (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> bab334b (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 5974d05 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 506ec7c (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
>>>>>>> ec17896 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
>>>>>>> 988528d (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
>>>>>>> f52c59f (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
>>>>>>> 7bbc5c2 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
>>>>>>> bab334b (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
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
=======
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 61f3472 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> ec17896 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 90fa1dc (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> d09733c (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 666814f (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
>>>>>>> 9f3ef93 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
>>>>>>> ff4e567 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
>>>>>>> 5974d05 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
>>>>>>> 61f3472 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
>>>>>>> 506ec7c (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
>>>>>>> ec17896 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
>>>>>>> 988528d (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
>>>>>>> f52c59f (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
>>>>>>> 7bbc5c2 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
>>>>>>> bab334b (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
    public void updateFromDTO(UserDTO dto) {
        this.username = dto.getUsername();
        this.password = dto.getPassword();
        this.email = dto.getEmail();
        this.age = dto.getAge();
        this.gender = dto.getGender();
        this.weight = dto.getWeight();
        this.height = dto.getHeight();
        this.fitnessLevel = dto.getFitnessLevel();
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 666814f (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 9f3ef93 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> ff4e567 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 61f3472 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> ec17896 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
>>>>>>> bab334b (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 988528d (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
>>>>>>> f52c59f (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 7bbc5c2 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
>>>>>>> bab334b (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
<<<<<<< HEAD
>>>>>>> 90fa1dc (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> d09733c (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 5974d05 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
>>>>>>> 61f3472 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 506ec7c (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
>>>>>>> ec17896 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
    }
}
