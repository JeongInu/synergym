package org.synergym.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.synergym.backend.dto.RoutineDTO;

import java.time.LocalDateTime;

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

    @CreationTimestamp
    private LocalDateTime createdAt;

    private Boolean useYn;
    private Boolean deleteYn;

    public RoutineDTO toDTO() {
        return RoutineDTO.builder()
                .id(id)
                .name(name)
                .routineGoal(routineGoal)
                .userId(user != null ? user.getId() : null)
                .createdAt(createdAt)
                .useYn(useYn)
                .deleteYn(deleteYn)
                .build();
    }

    public void updateFromDTO(RoutineDTO dto, User user) {
        this.name = dto.getName();
        this.routineGoal = dto.getRoutineGoal();
        this.user = user;
        this.createdAt = dto.getCreatedAt();
        this.useYn = dto.getUseYn();
        this.deleteYn = dto.getDeleteYn();
    }

}
