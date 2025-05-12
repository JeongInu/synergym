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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    private Character useYN;

    public void changeName(String name) {
        this.name = name;
    }

    public void changeRoutineGoal(String routineGoal) {
        this.routineGoal = routineGoal;
    }

    public void changeUseYN(Character useYN) {
        this.useYN = useYN;
    }
}
