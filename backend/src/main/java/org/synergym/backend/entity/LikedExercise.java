package org.synergym.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.synergym.backend.dto.LikedExerciseDTO;

@Entity
@Table(name = "liked_exercises")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikedExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "exerciseId")
    private Exercise exercise;

    // DTO로 변환하는 메서드
    public LikedExerciseDTO toDTO() {
        return LikedExerciseDTO.builder()
                .id(this.id)
                .userId(this.user.getId())  // User의 ID만 반환
                .exerciseId(this.exercise.getId())  // Exercise의 ID만 반환
                .build();
    }
}
