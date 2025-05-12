package org.synergym.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Column(columnDefinition = "TEXT")
    private String description;

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

    @ElementCollection
    @CollectionTable(name = "exercise_translations")
    @Builder.Default
    private Map<String, String> translations = new HashMap<>();  // language code를 key로 사용

    public void addTranslation(String languageCode, String translatedName) {
        this.translations.put(languageCode, translatedName);
    }

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