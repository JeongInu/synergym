package org.synergym.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseResponseDTO {
    private Integer id;
    private Integer category;
    private String description;
    private Integer language;
    private String name;

    public static ExerciseResponseDTO from(ExerciseDTO exerciseDTO) {
        return ExerciseResponseDTO.builder()
                .id(exerciseDTO.getId())
                .category(exerciseDTO.getCategory())
                .description(exerciseDTO.getDescription())
                .language(exerciseDTO.getLanguage())
                .name(exerciseDTO.getName())
                .build();
    }
}