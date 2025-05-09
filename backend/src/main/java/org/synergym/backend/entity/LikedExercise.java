package org.synergym.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "likedExercises")
public class LikedExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "exerciseId")
    private Exercise exercise;
}

