package org.synergym.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "categories")
public class Category {
    @Id
    private Integer id;

    private String name;
}
