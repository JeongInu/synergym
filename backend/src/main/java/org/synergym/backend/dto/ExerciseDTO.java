package org.synergym.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.synergym.backend.entity.Exercise;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExerciseDTO {
    private Integer id;
    private String name;
    private String description;
    private Integer category;
    private List<Integer> muscles;
    private List<Integer> equipment;

    public ExerciseDTO toDTO(Exercise e) {
        return new ExerciseDTO(
                e.getId(),
                e.getName(),
                Jsoup.parse(e.getDescription()).text(),
                e.getCategory(),
                e.getMuscles(),
                e.getEquipment()
        );
    }
}
