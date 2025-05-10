package org.synergym.backend.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.synergym.backend.dto.RoutineDTO;
import org.synergym.backend.entity.Routine;
import org.synergym.backend.entity.User;
import org.synergym.backend.repository.RoutineRepository;
import org.synergym.backend.repository.UserRepository;

import java.util.List;
import java.util.Optional;
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
        Routine routine = routineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Routine not found"));
        return entityToDto(routine);
    }

    @Transactional
    @Override
    public Integer addRoutine(RoutineDTO routineDTO) {
        User user = userRepository.findById(routineDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Routine routine = dtoToEntity(routineDTO, user);
        routine = routineRepository.save(routine);
        return routine.getId();
    }

    @Transactional
    @Override
    public void updateRoutine(Integer id, RoutineDTO routineDTO) {
        Routine routine = routineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Routine not found with id: " + id));
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD

        User user = userRepository.findById(routineDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + routineDTO.getUserId()));

        routine.changeName(routineDTO.getName());
        routine.changeRoutineGoal(routineDTO.getRoutineGoal());
        routine.changeUseYN(routineDTO.getUseYN());

=======
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)

        User user = userRepository.findById(routineDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + routineDTO.getUserId()));


        routine.updateFromDTO(routineDTO, user);
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
        routineRepository.save(routine);
    }

    @Transactional
    @Override
    public void deleteRoutine(Integer id) {
        if (!routineRepository.existsById(id)) {
            throw new RuntimeException("Routine not found");
        }
        routineRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<RoutineDTO> getAllRoutines() {
        return routineRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
}
