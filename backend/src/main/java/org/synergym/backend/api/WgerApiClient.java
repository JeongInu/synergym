package org.synergym.backend.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.synergym.backend.dto.ExerciseDTO;
import org.synergym.backend.dto.TranslationDTO;
import org.synergym.backend.dto.wrapper.WgerExerciseWrapper;
import org.synergym.backend.dto.wrapper.WgerTranslationWrapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
@Slf4j
@RequiredArgsConstructor
public class WgerApiClient {
    private final RestTemplate restTemplate;
    private final Executor executor;
    private static final String BASE_URL = "https://wger.de/api/v2";

    public WgerApiClient() {
        this.restTemplate = new RestTemplate();
        this.executor = Executors.newFixedThreadPool(20);

        // SimpleClientHttpRequestFactory 사용
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        restTemplate.setRequestFactory(factory);
    }


    public List<ExerciseDTO> fetchExerciseDTOs() {
        try {
            // 1. 기본 운동 정보 가져오기
            String exerciseUrl = BASE_URL + "/exercise/" +
                    "?language=2" +
                    "&limit=1000" +
                    "&status=2";

            ResponseEntity<WgerExerciseWrapper> exerciseResponse = restTemplate.exchange(
                    exerciseUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {}
            );

            List<ExerciseDTO> exercises = exerciseResponse.getBody().getResults();
            log.info("Fetched {} basic exercises", exercises.size());

            // 2. 병렬로 번역 정보 가져오기
            List<CompletableFuture<Void>> futures = exercises.stream()
                    .filter(exercise -> exercise.getId() != null)
                    .map(exercise -> CompletableFuture.runAsync(() -> {
                        try {
                            List<TranslationDTO> translations = fetchTranslations(exercise.getId());
                            updateExerciseWithTranslations(exercise, translations);
                            log.debug("Processed translations for exercise ID: {}", exercise.getId());
                        } catch (Exception e) {
                            log.error("Error processing translations for exercise {}: {}", 
                                    exercise.getId(), e.getMessage());
                        }
                    }, executor))
                    .collect(Collectors.toList());

            // 3. 모든 비동기 작업 완료 대기
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

            log.info("Successfully fetched and processed all translations");
            return exercises;

        } catch (Exception e) {
            log.error("Failed to fetch exercises: {}", e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public List<TranslationDTO> fetchTranslations(Integer exerciseId) {
        String url = BASE_URL + "/exercise-translation/" +
                "?exercise=" + exerciseId;

        try {
            ResponseEntity<WgerTranslationWrapper> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<WgerTranslationWrapper>() {}
            );

            return response.getBody().getResults();
        } catch (Exception e) {
            log.error("Error fetching translations for exercise {}: {}", exerciseId, e.getMessage());
            return new ArrayList<>();
        }
    }

    private void updateExerciseWithTranslations(ExerciseDTO exercise, List<TranslationDTO> translations) {
        for (TranslationDTO translation : translations) {
            if (translation.getName() != null) {
                exercise.setName(translation.getName());
            }
            if (translation.getDescription() != null) {
                exercise.setDescription(translation.getDescription());
            }
            if (translation.getCreationDate() != null) {
                exercise.setCreationDate(translation.getCreationDate());
            }
            if (translation.getLicense() != null) {
                exercise.setLicense(translation.getLicense());
            }
            if (translation.getLanguage() != null) {
                exercise.setLanguage(translation.getLanguage());
            }

            // 번역 맵에 저장
            if (translation.getLanguage() != null && translation.getName() != null) {
                exercise.getTranslations().put(
                        translation.getLanguage().toString(),
                        translation.getName()
                );
            }
        }
    }
}