package org.synergym.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoutineHistoryDTO {
    private Long id;
    private Long userId; // user_id
    private Long routineId; // routine_id
    private String feedback;
    private Long satisfactionScore;
}
