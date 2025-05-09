package org.synergym.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "routineHistories")
public class RoutineHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "routine_id")
    private Routine routine;

    private String feedback;
    private Integer satisfactionScore;
}

