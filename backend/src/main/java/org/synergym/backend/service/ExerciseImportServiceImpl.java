package org.synergym.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.synergym.backend.api.WgerApiClient;
import org.synergym.backend.dto.ExerciseDTO;
import org.synergym.backend.entity.Exercise;
import org.synergym.backend.repository.ExerciseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExerciseImportServiceImpl implements ExerciseImportService {

    private final ExerciseRepository exerciseRepository;
    private final WgerApiClient wgerApiClient;
    private final ExerciseService exerciseService;

    @Transactional
    @Override
    public void importExercises() {
        try {
            log.info("API에서 운동 데이터 가져오기 시작...");
            List<ExerciseDTO> dtos = wgerApiClient.fetchExerciseDTOs();
            log.info("API에서 {} 개의 운동 데이터를 성공적으로 가져왔습니다", dtos.size());

            if (dtos.isEmpty()) {
                log.warn("API에서 가져온 운동 데이터가 없습니다");
                return;
            }

            log.info("기존 운동 데이터 삭제 시작...");
            exerciseRepository.deleteAllInBatch();
            log.info("기존 운동 데이터 삭제 완료");

            log.info("새로운 운동 데이터 저장 시작...");
            List<Exercise> exercises = dtos.stream()
                    .map(dto -> {
                        dto.setId(null);
                        return exerciseService.dtoToEntity(dto);
                    })
                    .collect(Collectors.toList());

            exerciseRepository.saveAll(exercises);
            log.info("{}개의 운동 데이터를 성공적으로 저장했습니다", exercises.size());

        } catch (Exception e) {
            log.error("운동 데이터 가져오기 중 오류 발생: ", e);
            throw new RuntimeException("API로부터 운동 데이터 가져오기 실패: " + e.getMessage(), e);
        }
    }
}