package org.synergym.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.synergym.backend.dto.RoutineDTO;
import org.synergym.backend.entity.User;
import org.synergym.backend.repository.RoutineRepository;
import org.synergym.backend.repository.UserRepository;
import org.synergym.backend.service.RoutineService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class ServiceTest {
    @Autowired
    private RoutineService routineService;

    @Autowired
    private RoutineRepository routineRepository;

    @Autowired
    private UserRepository userRepository;

    private User testUser;
    private RoutineDTO testRoutineDTO;

    @BeforeEach
    void setUp() {
        // 테스트 유저 생성
        testUser = User.builder()
                .username("testUser")
                .password("password123")
                .email("test@test.com")
                .build();
        testUser = userRepository.save(testUser);

        // 테스트용 루틴 DTO 생성
        testRoutineDTO = RoutineDTO.builder()
                .name("Test Routine")
                .routineGoal("Strength Improvement")
                .userId(testUser.getId())
                .useYn(true)
                .deleteYn(false)
                .build();
    }

    @Test
    void createRoutine_Success() {
        // when
        Integer routineId = routineService.addRoutine(testRoutineDTO);

        // then
        RoutineDTO savedRoutine = routineService.getRoutineById(routineId);
        assertThat(savedRoutine).isNotNull();
        assertThat(savedRoutine.getName()).isEqualTo(testRoutineDTO.getName());
        assertThat(savedRoutine.getRoutineGoal()).isEqualTo(testRoutineDTO.getRoutineGoal());
        assertThat(savedRoutine.getUserId()).isEqualTo(testUser.getId());
    }

    @Test
    void updateRoutine_Success() {
        // given
        Integer routineId = routineService.addRoutine(testRoutineDTO);
        RoutineDTO updateDTO = RoutineDTO.builder()
                .name("Updated Routine")
                .routineGoal("Weight Loss")
                .userId(testUser.getId())
                .useYn(true)
                .deleteYn(false)
                .build();

        // when
        routineService.updateRoutine(routineId, updateDTO);

        // then
        RoutineDTO updatedRoutine = routineService.getRoutineById(routineId);
        assertThat(updatedRoutine).isNotNull();
        assertThat(updatedRoutine.getName()).isEqualTo("Updated Routine");
        assertThat(updatedRoutine.getRoutineGoal()).isEqualTo("Weight Loss");
    }

    @Test
    void getRoutine_NotFound() {
        // when & then
        assertThrows(RuntimeException.class, () -> routineService.getRoutineById(999));
    }

    @Test
    void deleteRoutine_Success() {
        // given
        Integer routineId = routineService.addRoutine(testRoutineDTO);

        // when
        routineService.deleteRoutine(routineId);

        // then
        assertThrows(RuntimeException.class, () -> routineService.getRoutineById(routineId));
    }

    @Test
    void getAllRoutines_Success() {
        // given
        routineService.addRoutine(testRoutineDTO);

        RoutineDTO secondRoutine = RoutineDTO.builder()
                .name("Second Routine")
                .routineGoal("Flexibility")
                .userId(testUser.getId())
                .useYn(true)
                .deleteYn(false)
                .build();
        routineService.addRoutine(secondRoutine);

        // when
        List<RoutineDTO> routines = routineService.getAllRoutines();

        // then
        assertThat(routines).isNotNull()
                .hasSize(2)
                .extracting("name")
                .containsExactlyInAnyOrder("Test Routine", "Second Routine");
    }

    @Test
    void createRoutine_WithInvalidUserId_ThrowsException() {
        // given
        RoutineDTO invalidRoutineDTO = RoutineDTO.builder()
                .name("Test Routine")
                .routineGoal("Strength Improvement")
                .userId(999)
                .useYn(true)
                .deleteYn(false)
                .build();

        // when & then
        assertThrows(RuntimeException.class, () -> routineService.addRoutine(invalidRoutineDTO));
    }
}