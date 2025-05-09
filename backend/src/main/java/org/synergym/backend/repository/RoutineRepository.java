package org.synergym.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.synergym.backend.entity.Routine;

import java.util.List;

public interface RoutineRepository extends JpaRepository<Routine, Long> {
    // 사용자 ID로 루틴 조회
    List<Routine> findByUserId(Long userId);
}
