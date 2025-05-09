package org.synergym.backend.service;

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
public class LikedExerciseServiceImpl implements LikedExerciseService {

    private final LikedExerciseRepository likedExerciseRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public LikedExerciseServiceImpl(LikedExerciseRepository likedExerciseRepository,
                                    UserRepository userRepository,
                                    ExerciseRepository exerciseRepository) {
        this.likedExerciseRepository = likedExerciseRepository;
        this.userRepository = userRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @Transactional
    @Override
    public List<LikedExerciseDTO> getLikedExercisesByUserId(Long userId) {
        return likedExerciseRepository.findByUserId(userId.intValue())
                .stream()
                .map(LikedExercise::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void addLikedExercise(Long userId, Long exerciseId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));

        LikedExercise likedExercise = LikedExercise.builder()
                .user(user)
                .exercise(exercise)
                .build();

        likedExerciseRepository.save(likedExercise);
    }

    @Transactional
    @Override
    public void removeLikedExercise(Long userId, Long exerciseId) {
        LikedExercise likedExercise = likedExerciseRepository.findByUserIdAndExerciseId(userId.intValue(), exerciseId)
                .orElseThrow(() -> new RuntimeException("LikedExercise not found"));

        likedExerciseRepository.delete(likedExercise);
    }
}
