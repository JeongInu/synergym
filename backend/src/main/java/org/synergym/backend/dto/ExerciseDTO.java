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
import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;

@Getter
@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
@Slf4j
public class ExerciseDTO {
    private Integer id;
    private String name;
    private String description;
    private String uuid;

    @JsonProperty("creation_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private LocalDateTime creationDate;

    private Integer license;

    @JsonProperty("license_author")
    private String licenseAuthor;

    private Integer category;
    private Integer language;

    @JsonProperty("muscles")
    @Builder.Default
    private List<Integer> muscles = new ArrayList<>();

    @JsonProperty("muscles_secondary")
    @Builder.Default
    private List<Integer> musclesSecondary = new ArrayList<>();

    @JsonProperty("equipment")
    @Builder.Default
    private List<Integer> equipment = new ArrayList<>();

    // translation 데이터를 저장하기 위한 map
    @Builder.Default
    private Map<String, String> translations = new HashMap<>();

    @JsonAnySetter
    public void handleUnknownField(String key, Object value) {
        log.debug("Unknown field: {} = {}", key, value);
    }
}