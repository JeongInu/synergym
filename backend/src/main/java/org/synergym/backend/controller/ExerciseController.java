package org.synergym.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.synergym.backend.dto.ExerciseResponseDTO;
import org.synergym.backend.service.ExerciseImportService;
import org.synergym.backend.service.ExerciseService;

import java.util.List;

@RestController
@RequestMapping("/exercises")
@RequiredArgsConstructor
public class ExerciseController {
    private final ExerciseImportService exerciseImportService;
    private final ExerciseService exerciseService;

    @PostMapping("/exercises/import")
    public ResponseEntity<Void> importExercises() {
        exerciseImportService.importExercises();
        return ResponseEntity.ok().build();
    }

    // 전체 운동 조회
    @GetMapping
    public ResponseEntity<List<ExerciseResponseDTO>> getAllExercises() {
        List<ExerciseResponseDTO> exercises = exerciseService.getAllExercises();
        return ResponseEntity.ok(exercises);
    }
    // 특정 ID 운동 조회
    @GetMapping("/{id}")
    public ResponseEntity<ExerciseResponseDTO> getExerciseById(@PathVariable Integer id) {
        ExerciseResponseDTO exercise = exerciseService.getExerciseById(id);
        return ResponseEntity.ok(exercise);
    }
    // 특정 카테고리와 언어로 운동 조회 (languageName 사용)
    @GetMapping("/filter")
    public ResponseEntity<List<ExerciseResponseDTO>> getExercisesByCategoryAndLanguage(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String languageName  // 언어 이름으로 수정
    ) {
        List<ExerciseResponseDTO> exercises = exerciseService.getExercisesByCategoryAndLanguage(category, languageName);
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ExerciseResponseDTO>> searchExercises(
            @RequestParam(required = false) String keyword,
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<ExerciseResponseDTO> result = exerciseService.searchExercises(
                keyword, pageable
        );
        return ResponseEntity.ok(result);
    }
}