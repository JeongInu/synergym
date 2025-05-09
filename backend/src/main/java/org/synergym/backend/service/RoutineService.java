package org.synergym.backend.service;

import org.synergym.backend.dto.RoutineDTO;

import java.util.List;

public interface RoutineService {
    RoutineDTO getRoutineById(Integer id);
    RoutineDTO addRoutine(RoutineDTO routineDTO);
    RoutineDTO updateRoutine(Integer id, RoutineDTO routineDTO);
    void deleteRoutine(Integer id);
    List<RoutineDTO> getAllRoutines();
}