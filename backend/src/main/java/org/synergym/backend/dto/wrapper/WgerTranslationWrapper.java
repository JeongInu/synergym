package org.synergym.backend.dto.wrapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.synergym.backend.dto.ExerciseTranslationDTO;

import java.util.List;

@Getter
@NoArgsConstructor
public class WgerTranslationWrapper {
    private List<ExerciseTranslationDTO> results;
}

