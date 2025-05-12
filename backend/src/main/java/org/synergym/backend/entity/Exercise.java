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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        this.equipment = equipment;
=======
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
    // DTO 정보를 Entity에 업데이트하는 메서드
    public void updateFromDTO(ExerciseDTO exerciseDTO) {
        this.name = exerciseDTO.getName();
        this.description = exerciseDTO.getDescription();
        this.category = exerciseDTO.getCategory();
        this.muscles = exerciseDTO.getMuscles();
        this.equipment = exerciseDTO.getEquipment();
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
        this.equipment = equipment != null ? new ArrayList<>(equipment) : new ArrayList<>();
>>>>>>> cc535ab (test : user, exercise, routine test코드 작성)
=======
=======
>>>>>>> 581e456 (test : user, exercise, routine test코드 작성)
        this.equipment = equipment != null ? new ArrayList<>(equipment) : new ArrayList<>();
=======
=======
>>>>>>> d78db24 (test : user, exercise, routine test코드 작성)
        this.equipment = equipment;
=======
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
    // DTO 정보를 Entity에 업데이트하는 메서드
    public void updateFromDTO(ExerciseDTO exerciseDTO) {
        this.name = exerciseDTO.getName();
        this.description = exerciseDTO.getDescription();
        this.category = exerciseDTO.getCategory();
        this.muscles = exerciseDTO.getMuscles();
        this.equipment = exerciseDTO.getEquipment();
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
>>>>>>> 8859a4d (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
>>>>>>> 90fa1dc (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
=======
        this.equipment = equipment != null ? new ArrayList<>(equipment) : new ArrayList<>();
>>>>>>> cc535ab (test : user, exercise, routine test코드 작성)
>>>>>>> d78db24 (test : user, exercise, routine test코드 작성)
>>>>>>> 581e456 (test : user, exercise, routine test코드 작성)
    }
}