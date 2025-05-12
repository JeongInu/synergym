package org.synergym.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "poseAnalysises")
public class PoseAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 분석 대상 사진
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photoId", unique = true)
    private Photo photo;

    private Double neckAngleDiff;
    private Double shoulderAngleDiff;
    private Double spineAngleDiff;

    private String feedback;

    private LocalDateTime analyzedAt;

    @PrePersist
    public void prePersist() {
        this.analyzedAt = LocalDateTime.now();
    }


}
