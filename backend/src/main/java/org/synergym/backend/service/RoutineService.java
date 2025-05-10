package org.synergym.backend.service;

import org.synergym.backend.dto.LikedExerciseDTO;
import org.synergym.backend.dto.RoutineDTO;
import org.synergym.backend.entity.Exercise;
import org.synergym.backend.entity.LikedExercise;
import org.synergym.backend.entity.Routine;
import org.synergym.backend.entity.User;

import java.util.List;

public interface RoutineService {
    RoutineDTO getRoutineById(Integer id);
    Integer addRoutine(RoutineDTO routineDTO);
    void updateRoutine(Integer id, RoutineDTO routineDTO);
    void deleteRoutine(Integer id);
    List<RoutineDTO> getAllRoutines();

    default Routine dtoToEntity(RoutineDTO dto, User user) {
        return Routine.builder()
                .id(dto.getId())
                .name(dto.getName())
                .routineGoal(dto.getRoutineGoal())
                .user(user)
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
                .useYN(dto.getUseYN())
=======
                .useYn(dto.getUseYn())
                .deleteYn(dto.getDeleteYn())
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
                .useYn(dto.getUseYn())
                .deleteYn(dto.getDeleteYn())
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
                .useYn(dto.getUseYn())
                .deleteYn(dto.getDeleteYn())
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
                .build();
    }

    default RoutineDTO entityToDto(Routine routine) {
        return RoutineDTO.builder()
                .id(routine.getId())
                .name(routine.getName())
                .routineGoal(routine.getRoutineGoal())
                .userId(routine.getUser() != null ? routine.getUser().getId() : null)
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
                .useYN(routine.getUseYN())
=======
                .useYn(routine.getUseYn())
                .deleteYn(routine.getDeleteYn())
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
                .useYn(routine.getUseYn())
                .deleteYn(routine.getDeleteYn())
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
                .useYn(routine.getUseYn())
                .deleteYn(routine.getDeleteYn())
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
                .build();
    }
}