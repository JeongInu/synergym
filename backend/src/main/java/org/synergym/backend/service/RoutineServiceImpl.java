package org.synergym.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.synergym.backend.dto.RoutineDTO;
import org.synergym.backend.entity.Routine;
import org.synergym.backend.entity.User;
import org.synergym.backend.repository.RoutineRepository;
import org.synergym.backend.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoutineServiceImpl implements RoutineService {

    private final RoutineRepository routineRepository;
    private final UserRepository userRepository;

    @Autowired
    public RoutineServiceImpl(RoutineRepository routineRepository, UserRepository userRepository) {
        this.routineRepository = routineRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public RoutineDTO getRoutineById(Integer id) {
        Routine routine = routineRepository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Routine not found"));
        return routine.toDTO();
    }

    @Transactional
    @Override
    public RoutineDTO addRoutine(RoutineDTO routineDTO) {
        User user = userRepository.findById(routineDTO.getUserId().longValue())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Routine routine = Routine.builder()
                .name(routineDTO.getName())
                .routineGoal(routineDTO.getRoutineGoal())
                .user(user)
                .createdAt(routineDTO.getCreatedAt())
                .useYn(routineDTO.getUseYn())
                .deleteYn(routineDTO.getDeleteYn())
                .build();

        return routineRepository.save(routine).toDTO();
    }

    @Transactional
    @Override
    public RoutineDTO updateRoutine(Integer id, RoutineDTO routineDTO) {
        Routine routine = routineRepository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Routine not found"));

        User user = userRepository.findById(routineDTO.getUserId().longValue())
                .orElseThrow(() -> new RuntimeException("User not found"));

        routine.updateFromDTO(routineDTO, user);
        return routineRepository.save(routine).toDTO();
    }

    @Transactional
    @Override
    public void deleteRoutine(Integer id) {
        if (!routineRepository.existsById(id.longValue())) {
            throw new RuntimeException("Routine not found");
        }
        routineRepository.deleteById(id.longValue());
    }

    @Transactional(readOnly = true)
    @Override
    public List<RoutineDTO> getAllRoutines() {
        return routineRepository.findAll()
                .stream()
                .map(Routine::toDTO)
                .collect(Collectors.toList());
    }
}
