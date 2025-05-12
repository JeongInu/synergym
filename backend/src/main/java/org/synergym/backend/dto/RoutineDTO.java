package org.synergym.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoutineDTO {
    private Integer id;
    private String name;
    private String routineGoal;
    private Integer userId; // user_id
    private Character useYN;
}
