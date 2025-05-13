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

    private Integer category;
    private Integer language;

    @ElementCollection(fetch = FetchType.LAZY) // 지연 로딩은 성능에 도움이 될 수 있습니다.
    @CollectionTable(name = "exercise_muscles",
            joinColumns = @JoinColumn(name = "exercise_id")) // exercise 테이블과의 FK
    @Column(name = "muscle_id") // 요소가 저장될 컬럼 (DB 스키마의 컬럼명과 일치해야 함)
    @Builder.Default
    private List<Integer> muscles = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "exercise_muscles_secondary",
            joinColumns = @JoinColumn(name = "exercise_id")) // exercise 테이블과의 FK
    @Column(name = "muscle_id") // 요소가 저장될 컬럼 (오류 메시지에서 언급된 컬럼)
    @Builder.Default
    private List<Integer> musclesSecondary = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "exercise_equipment",
            joinColumns = @JoinColumn(name = "exercise_id")) // exercise 테이블과의 FK
    @Column(name = "equipment_id") // 요소가 저장될 컬럼 (DB 스키마의 컬럼명과 일치해야 함)
    @Builder.Default
    private List<Integer> equipment = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "exercise_translations",
            joinColumns = @JoinColumn(name = "exercise_id"))
    @Builder.Default
    private List<ExerciseTranslation> translations = new ArrayList<>();


}