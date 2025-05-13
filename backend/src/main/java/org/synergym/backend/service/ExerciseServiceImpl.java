package org.synergym.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.synergym.backend.api.WgerApiClient;
import org.synergym.backend.dto.ExerciseDTO;
import org.synergym.backend.dto.ExerciseResponseDTO;
import org.synergym.backend.entity.Exercise;
import org.synergym.backend.entity.ExerciseTranslation;
import org.synergym.backend.repository.ExerciseRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final WgerApiClient wgerApiClient;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, WgerApiClient wgerApiClient) {
        this.exerciseRepository = exerciseRepository;
        this.wgerApiClient = wgerApiClient;
    }

    @Override
    public Exercise dtoToEntity(ExerciseDTO dto) {
        if (dto == null) {
            return null;
        }

        List<Integer> muscles = dto.getMuscles() != null ?
                dto.getMuscles().stream().filter(Objects::nonNull).collect(Collectors.toList()) :
                Collections.emptyList();

        List<Integer> musclesSecondary = dto.getMusclesSecondary() != null ?
                dto.getMusclesSecondary().stream().filter(Objects::nonNull).collect(Collectors.toList()) :
                Collections.emptyList();

        List<Integer> equipment = dto.getEquipment() != null ?
                dto.getEquipment().stream().filter(Objects::nonNull).collect(Collectors.toList()) :
                Collections.emptyList();

        List<ExerciseTranslation> translations = dto.getTranslations() != null ?
                dto.getTranslations().stream()
                        .filter(t -> t.getLanguageCode() != null &&
                                t.getTranslationsKey() != null &&
                                t.getTranslationText() != null)  // 필수 필드만 필터링
                        .map(t -> ExerciseTranslation.builder()
                                .languageCode(t.getLanguageCode())
                                .translationsKey(t.getTranslationsKey())
                                .translationText(t.getTranslationText())
                                .translatedName(t.getTranslatedName())
                                .translations(t.getTranslations())
                                .build())
                        .collect(Collectors.toList()) :
                Collections.emptyList();

        return Exercise.builder()
                // .id(dto.getId()) // ExerciseImportServiceImpl에서 dto.setId(null)로 처리하므로 주석 처리
                .name(dto.getName())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .language(dto.getLanguage())
                .muscles(muscles)
                .musclesSecondary(musclesSecondary)
                .equipment(equipment)
                .translations(translations)
                // Exercise 엔티티의 다른 필드들도 DTO로부터 값을 가져와 빌더에 추가
                .build();
    }

    @Override
    public ExerciseResponseDTO entityToResponseDto(Exercise exercise) {
        if (exercise == null) {
            return null;
        }
        // Exercise 엔티티를 ExerciseResponseDTO로 변환하는 로직
        // 빌더 패턴을 ExerciseResponseDTO에도 적용할 수 있다면 유사하게 사용 가능
        ExerciseResponseDTO.ExerciseResponseDTOBuilder builder = ExerciseResponseDTO.builder()
                .id(exercise.getId())
                .category(exercise.getCategory())
                .description(exercise.getDescription())
                .language(exercise.getLanguage())
                .name(exercise.getName());
        return builder.build();
    }


    @Transactional(readOnly = true)
    @Override
    public ExerciseResponseDTO getExerciseById(Integer id) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found with id: " + id));
        return entityToResponseDto(exercise);
    }

    @Transactional
    @Override
    public Integer addExercise(ExerciseDTO exerciseDTO) {
        Exercise exercise = dtoToEntity(exerciseDTO);
        // Exercise 엔티티의 creationDate 필드가 Not Null이고, DTO에 없다면 여기서 설정 가능
        // if (exercise.getCreationDate() == null) {
        //     exercise.setCreationDate(java.time.LocalDateTime.now()); // 또는 빌더에서 설정
        // }
        exercise = exerciseRepository.save(exercise);
        return exercise.getId();
    }

    @Transactional
    @Override
    public void deleteExercise(Integer id) {
        if (!exerciseRepository.existsById(id)) {
            throw new RuntimeException("Exercise not found with id: " + id);
        }
        exerciseRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ExerciseResponseDTO> getAllExercises() {
        return exerciseRepository.findAll()
                .stream()
                .map(this::entityToResponseDto)
                .collect(Collectors.toList());
    }
}
