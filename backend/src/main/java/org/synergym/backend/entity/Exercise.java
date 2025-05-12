package org.synergym.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.synergym.backend.dto.ExerciseDTO;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "exercises")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exercise {
    @Id
    private Integer id;

    private String name;
    private String nameOriginal;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String licenseAuthor;
    private String status;
    private String uuid;
    private LocalDateTime creationDate;

    private Integer license;
    private Integer category;
    private Integer language;

    @ElementCollection
    private List<Integer> muscles;

    @ElementCollection
    private List<Integer> musclesSecondary;

    @ElementCollection
    private List<Integer> equipment;

    // DTO 정보를 Entity에 업데이트하는 메서드
    public void updateFromDTO(ExerciseDTO exerciseDTO) {
        this.name = exerciseDTO.getName();
        this.description = exerciseDTO.getDescription();
        this.category = exerciseDTO.getCategory();
        this.muscles = exerciseDTO.getMuscles();
        this.equipment = exerciseDTO.getEquipment();
    }
}
