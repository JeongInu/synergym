package org.synergym.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LikedExerciseDTO {
    private Integer id;
    private Integer userId; // user_id
    private Integer exerciseId; // exercise_id
}
