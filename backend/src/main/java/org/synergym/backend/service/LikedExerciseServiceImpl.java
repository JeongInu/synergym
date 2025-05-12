package org.synergym.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.synergym.backend.dto.LikedExerciseDTO;
import org.synergym.backend.entity.Exercise;
import org.synergym.backend.entity.LikedExercise;
import org.synergym.backend.entity.User;
import org.synergym.backend.repository.LikedExerciseRepository;
import org.synergym.backend.repository.UserRepository;
import org.synergym.backend.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikedExerciseServiceImpl implements LikedExerciseService {

    private final LikedExerciseRepository likedExerciseRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;

    @Override
    public List<LikedExerciseDTO> getLikedExercisesByUserId(Integer userId) {
        return likedExerciseRepository.findByUserId(userId).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Integer addLikedExercise(Integer userId, Integer exerciseId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new IllegalArgumentException("Exercise not found"));

        LikedExercise likedExercise = dtoToEntity(user, exercise);
        likedExercise = likedExerciseRepository.save(likedExercise);
        return likedExercise.getId();
    }


    @Override
    public void removeLikedExercise(Integer userId, Integer exerciseId) {
        LikedExercise likedExercise = likedExerciseRepository.findByUserIdAndExerciseId(userId, exerciseId)
                .orElseThrow(() -> new IllegalArgumentException("LikedExercise not found"));
        likedExerciseRepository.delete(likedExercise);
    }
}