package org.synergym.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exercises")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(
            name = "exercises_seq_gen",
            sequenceName = "exercises_id_seq",
            allocationSize = 1
    )
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
    @CollectionTable(name = "exercise_muscles")
    @Builder.Default
    private List<Integer> muscles = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "exercise_muscles_secondary")
    @Builder.Default
    private List<Integer> musclesSecondary = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "exercise_equipment")
    @Builder.Default
    private List<Integer> equipment = new ArrayList<>();

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
        this.muscles = muscles != null ? new ArrayList<>(muscles) : new ArrayList<>();
    }

    public void changeEquipment(List<Integer> equipment) {
        this.equipment = equipment != null ? new ArrayList<>(equipment) : new ArrayList<>();
    }
}