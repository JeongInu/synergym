package org.synergym.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoutineDetailDTO {
    private Long id;
    private Long routineId; // routine_id
    private Long exerciseId; // exercise_id
    private Long sequenceOrder;
}
