package org.synergym.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.synergym.backend.service.ExerciseImportService;

@RestController
@RequiredArgsConstructor
public class ExerciseController {
    private final ExerciseImportService exerciseImportService;

    @PostMapping("/exercises/import")
    public ResponseEntity<Void> importExercises() {
        exerciseImportService.importExercises();
        return ResponseEntity.ok().build();
    }
}