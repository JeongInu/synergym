package org.synergym.backend.service;

import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.synergym.backend.api.WgerApiClient;
import org.synergym.backend.dto.ExerciseDTO;
import org.synergym.backend.dto.ExerciseResponseDTO;
import org.synergym.backend.entity.Exercise;
import org.synergym.backend.entity.ExerciseTranslation;
import org.synergym.backend.entity.LanguageType;
import org.synergym.backend.repository.ExerciseRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final WgerApiClient wgerApiClient;

    private static final Map<String, Integer> languageNameToIdMap = Map.ofEntries(
            Map.entry("AZ", 18),
            Map.entry("ID", 23),
            Map.entry("DE", 1),
            Map.entry("EN", 2),
            Map.entry("ES", 4),
            Map.entry("EO", 19),
            Map.entry("FR", 12),
            Map.entry("HR", 22),
            Map.entry("IT", 13),
            Map.entry("NL", 6),
            Map.entry("NO", 11),
            Map.entry("PL", 14),
            Map.entry("PT", 7),
            Map.entry("SV", 10),
            Map.entry("TR", 16),
            Map.entry("CS", 9),
            Map.entry("EL", 8),
            Map.entry("BG", 3),
            Map.entry("RU", 5),
            Map.entry("UK", 15)
    );

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, WgerApiClient wgerApiClient) {
        this.exerciseRepository = exerciseRepository;
        this.wgerApiClient = wgerApiClient;
    }

    @Override
    public List<ExerciseResponseDTO> getExercisesByCategoryAndLanguage(String categoryName, String languageName) {
        boolean filterByCategory = categoryName != null && !categoryName.trim().isEmpty();

        Integer languageId = null;
        boolean filterByLanguage = false;
        if (languageName != null && !languageName.trim().isEmpty()) {
            languageId = languageNameToIdMap.get(languageName.trim());
            filterByLanguage = languageId != null;
        }

        List<Exercise> exercises;

        if (filterByCategory && filterByLanguage) {
            exercises = exerciseRepository.findByCategory_NameAndLanguage(categoryName.trim(), languageId);
        } else if (filterByCategory) {
            exercises = exerciseRepository.findByCategory_Name(categoryName.trim());
        } else if (filterByLanguage) {
            exercises = exerciseRepository.findByLanguage(languageId);
        } else {
            exercises = exerciseRepository.findAll();
        }
        return exercises.stream()
                .map(e -> ExerciseResponseDTO.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .description(e.getDescription())
                        .categoryId(e.getCategory() != null ? e.getCategory().getId() : null)
                        .categoryName(e.getCategory() != null ? e.getCategory().getName() : null)
                        .language(e.getLanguage() != null ? e.getLanguage() : null)
                        .languageName(LanguageType.getDisplayNameById(e.getLanguage()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Page<ExerciseResponseDTO> searchExercises(String keyword,
                                                     Pageable pageable) {

        Specification<Exercise> spec = Specification.where(null);

        if (keyword != null && !keyword.trim().isEmpty()) {
            String lowerKeyword = "%" + keyword.trim().toLowerCase() + "%";

            // 여러 필드에 대한 OR 검색 조건을 만들기 위한 Specification 정의
            Specification<Exercise> keywordSpec = (root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();

                // 제목, 내용 검색
                predicates.add(cb.like(cb.lower(root.get("name")), lowerKeyword));
                predicates.add(cb.like(cb.lower(root.get("description")), lowerKeyword));

                return cb.or(predicates.toArray(new Predicate[0]));
            };

            spec = spec.and(keywordSpec);
        }

        Page<Exercise> page = exerciseRepository.findAll(spec, pageable);
        return page.map(this::entityToResponseDto);
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

        Integer language = exercise.getLanguage();  // Integer (nullable)

        String languageName = null;
        if (language != null) {
            languageName = LanguageType.getDisplayNameById(language);
        } else {
            // 언어가 없을 경우 기본값 설정 (예: "Unknown" 또는 빈 문자열)
            languageName = "Unknown";
        }

        // Exercise 엔티티를 ExerciseResponseDTO로 변환하는 로직
        // 빌더 패턴을 ExerciseResponseDTO에도 적용할 수 있다면 유사하게 사용 가능
        ExerciseResponseDTO.ExerciseResponseDTOBuilder builder = ExerciseResponseDTO.builder()
                .id(exercise.getId())
                .categoryId(exercise.getCategory().getId())
                .categoryName(exercise.getCategory().getName())
                .description(exercise.getDescription())
                .language(exercise.getLanguage())
                .languageName(languageName)
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

    @Transactional(readOnly = true)
    @Override
    public List<ExerciseResponseDTO> getAllExercises() {
        return exerciseRepository.findAll()
                .stream()
                .map(this::entityToResponseDto)
                .collect(Collectors.toList());
    }
}
