package org.synergym.backend.dto.wrapper;

import lombok.Data;
import org.synergym.backend.dto.ExerciseDTO;

import java.util.List;

@Data
public class WgerExerciseWrapper {
    private List<ExerciseDTO> results;
}