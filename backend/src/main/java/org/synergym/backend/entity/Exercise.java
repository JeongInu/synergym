package org.synergym.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "exercises")
public class Exercise {
    @Id
    private Long id;

    private String name;
    private String nameOriginal;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String licenseAuthor;
    private String status;
    private String uuid;
    private LocalDateTime creationDate;

    private Integer license;
    private Integer category;
    private Integer language;

    @ElementCollection
    private List<Integer> muscles;

    @ElementCollection
    private List<Integer> musclesSecondary;

    @ElementCollection
    private List<Integer> equipment;
}

