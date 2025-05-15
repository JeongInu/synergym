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
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExerciseController {
    private final ExerciseImportService exerciseImportService;
    private final ExerciseService exerciseService;

    @PostMapping("/import")
    public ResponseEntity<Void> importExercises() {
        exerciseImportService.importExercises();
        return ResponseEntity.ok().build();
    }

    // 전체 운동 조회
    @GetMapping("/exercises")
    public ResponseEntity<List<ExerciseResponseDTO>> getAllExercises() {
        List<ExerciseResponseDTO> exercises = exerciseService.getAllExercises();
        System.out.println("===== getAllExercises() 결과 =====");
        for (ExerciseResponseDTO dto : exercises) {
            System.out.println("ID: " + dto.getId() +
                    ", Name: " + dto.getName() +
                    ", Description: " + dto.getDescription() +
                    ", Category: " + dto.getCategoryName());
        }
        return ResponseEntity.ok(exercises);
    }

    // 특정 카테고리와 언어로 운동 조회 (languageName 사용)
    @GetMapping("/exercises/filter")
    public ResponseEntity<List<ExerciseResponseDTO>> getExercisesByCategoryAndLanguage(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String languageName
    ) {
        List<ExerciseResponseDTO> exercises = exerciseService.getExercisesByCategoryAndLanguage(category, languageName);
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/exercises/search")
    public ResponseEntity<Page<ExerciseResponseDTO>> searchExercises(
            @RequestParam(required = false) String keyword,
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<ExerciseResponseDTO> result = exerciseService.searchExercises(
                keyword, pageable
        );

         System.out.println("===== searchExercises() 결과 =====");
//         if (result != null && result.hasContent()) {
//             for (ExerciseResponseDTO dto : result.getContent()) {
//                 System.out.println("ID: " + dto.getId() +
//                         ", Name: " + dto.getName() +
//                         ", Description: " + dto.getDescription() +
//                         ", Category: " + dto.getCategoryName());
//             }
//             System.out.println("Total Elements: " + result.getTotalElements() + ", Total Pages: " + result.getTotalPages());
//         } else {
//              System.out.println("검색 결과가 없습니다.");
//         }
        return ResponseEntity.ok(result);
    }

}