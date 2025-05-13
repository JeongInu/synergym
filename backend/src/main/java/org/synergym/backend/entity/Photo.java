package org.synergym.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "photos")
public class Photo extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // 사용자와 다대일 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 이미지 경로만 저장
    private String imagePath;

    // FRONT or SIDE
    @Enumerated(EnumType.STRING)
    private ViewType viewType;

    // 업로드 시점
    private LocalDateTime uploadedAt;

    private boolean deletedYn;

    // 1:1 관계의 역방향 설정 (optional=true: 분석이 안 된 상태 가능)
    @OneToOne(mappedBy = "photo", cascade = CascadeType.ALL, orphanRemoval = true, optional = true)
    private PoseAnalysis poseAnalysis;

    @PrePersist
    public void prePersist() {
        this.uploadedAt = LocalDateTime.now();
        this.deletedYn = false;
    }

    public enum ViewType {
        FRONT, SIDE
    }
}