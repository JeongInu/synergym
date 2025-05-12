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

    public void changeName(String name) {
        this.name = name;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void changeCategory(Integer category) {
        this.category = category;
    }

    public void changeMuscles(List<Integer> muscles) {
        this.muscles = muscles;
    }

    public void changeEquipment(List<Integer> equipment) {
        this.equipment = equipment;
    }
}
