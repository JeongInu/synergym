package org.synergym.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.synergym.backend.entity.Category;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseResponseDTO {
    private Integer id;
    private Category category;
    private String description;
    private Integer language;
    private String languageName;
    private String name;
}