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
<<<<<<< HEAD
    private Character useYN;
=======
    private Boolean useYn;
    private Boolean deleteYn;
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
}
