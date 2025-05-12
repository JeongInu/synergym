package org.synergym.backend.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//기본생성자 생성, 자식 클래스나 같은 패키지 내의 클래스에서만 생성자에 접근
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    private LocalDateTime updatedAt;
    
    private boolean deleteYn;

    @PrePersist
    //엔티티가 데이터베이스에 저장되기 전에 `deleteYn` 필드를 자동으로 `false`로 설정
    public void onCreate() {
        this.deleteYn = false;
    }

    public void softDelete() {
        this.deleteYn = true;
    }
}