package org.synergym.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.synergym.backend.dto.RoutineDTO;

@Entity
@Table(name = "routines")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String routineGoal;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private Boolean useYn;
    private Boolean deleteYn;

    public void updateFromDTO(RoutineDTO dto, User user) {
        this.name = dto.getName();
        this.routineGoal = dto.getRoutineGoal();
        this.user = user;
        this.useYn = dto.getUseYn();
        this.deleteYn = dto.getDeleteYn();
    }

}
