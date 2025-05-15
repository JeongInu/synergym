package org.synergym.backend.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.synergym.backend.entity.Category;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Slf4j
public class ExerciseDTO {
    private Integer id;
    private String name;
    private String description;
    private Category category;
    private Integer language;

    @JsonProperty("muscles")
    private List<Integer> muscles = new ArrayList<>();

    @JsonProperty("muscles_secondary")
    private List<Integer> musclesSecondary = new ArrayList<>();

    @JsonProperty("equipment")
    private List<Integer> equipment = new ArrayList<>();

    // translation 데이터를 저장하기 위한 map
    private List<ExerciseTranslationDTO> translations = new ArrayList<>();

    @JsonAnySetter
    public void handleUnknownField(String key, Object value) {
        log.debug("Unknown field: {} = {}", key, value);
    }
}