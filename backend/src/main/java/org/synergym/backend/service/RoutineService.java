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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 90fa1dc (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> d09733c (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 666814f (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 9f3ef93 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> ff4e567 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
>>>>>>> 5974d05 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
>>>>>>> 61f3472 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
>>>>>>> f52c59f (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 5974d05 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 506ec7c (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
>>>>>>> ec17896 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
>>>>>>> 988528d (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
>>>>>>> f52c59f (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
                .useYN(dto.getUseYN())
=======
                .useYn(dto.getUseYn())
                .deleteYn(dto.getDeleteYn())
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> d09733c (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
>>>>>>> 506ec7c (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
>>>>>>> ec17896 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
                .useYn(dto.getUseYn())
                .deleteYn(dto.getDeleteYn())
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 666814f (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
                .useYn(dto.getUseYn())
                .deleteYn(dto.getDeleteYn())
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 988528d (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
<<<<<<< HEAD
>>>>>>> 61f3472 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> ec17896 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
                .useYn(dto.getUseYn())
                .deleteYn(dto.getDeleteYn())
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
>>>>>>> f52c59f (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
                .useYn(dto.getUseYn())
                .deleteYn(dto.getDeleteYn())
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
                .useYn(dto.getUseYn())
                .deleteYn(dto.getDeleteYn())
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
<<<<<<< HEAD
>>>>>>> 90fa1dc (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> d09733c (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 666814f (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
<<<<<<< HEAD
                .useYn(dto.getUseYn())
                .deleteYn(dto.getDeleteYn())
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 9f3ef93 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
                .useYn(dto.getUseYn())
                .deleteYn(dto.getDeleteYn())
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
>>>>>>> ff4e567 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
>>>>>>> 5974d05 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
>>>>>>> 61f3472 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 5974d05 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 506ec7c (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
>>>>>>> ec17896 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
>>>>>>> 988528d (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
>>>>>>> f52c59f (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 90fa1dc (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> d09733c (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 666814f (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 9f3ef93 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> ff4e567 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
>>>>>>> 5974d05 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
>>>>>>> 61f3472 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
>>>>>>> f52c59f (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 5974d05 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 506ec7c (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
>>>>>>> ec17896 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
>>>>>>> 988528d (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
>>>>>>> f52c59f (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
                .useYN(routine.getUseYN())
=======
                .useYn(routine.getUseYn())
                .deleteYn(routine.getDeleteYn())
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> d09733c (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
>>>>>>> 506ec7c (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
>>>>>>> ec17896 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
                .useYn(routine.getUseYn())
                .deleteYn(routine.getDeleteYn())
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 666814f (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
                .useYn(routine.getUseYn())
                .deleteYn(routine.getDeleteYn())
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 988528d (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
<<<<<<< HEAD
>>>>>>> 61f3472 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> ec17896 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
                .useYn(routine.getUseYn())
                .deleteYn(routine.getDeleteYn())
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
>>>>>>> f52c59f (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
                .useYn(routine.getUseYn())
                .deleteYn(routine.getDeleteYn())
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
                .useYn(routine.getUseYn())
                .deleteYn(routine.getDeleteYn())
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
<<<<<<< HEAD
>>>>>>> 90fa1dc (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> d09733c (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 666814f (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
<<<<<<< HEAD
                .useYn(routine.getUseYn())
                .deleteYn(routine.getDeleteYn())
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 9f3ef93 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
                .useYn(routine.getUseYn())
                .deleteYn(routine.getDeleteYn())
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
>>>>>>> ff4e567 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
>>>>>>> 5974d05 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
>>>>>>> 61f3472 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 5974d05 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 506ec7c (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
>>>>>>> ec17896 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
>>>>>>> 988528d (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
>>>>>>> f52c59f (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
                .build();
    }
}