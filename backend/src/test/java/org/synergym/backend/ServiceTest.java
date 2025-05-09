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

import java.time.LocalDateTime;
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
        testUser = User.builder()
                .username("testUser")
                .password("password123")
                .email("test@test.com")
                .build();
        testUser = userRepository.save(testUser);

        testRoutineDTO = RoutineDTO.builder()
                .name("Test Routine")
                .routineGoal("Strength Improvement")
                .userId(testUser.getId())
                .useYn(true)
                .deleteYn(false)
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Test
    void testCreateAndGetRoutine() {
        // when
        RoutineDTO savedRoutine = routineService.addRoutine(testRoutineDTO);

        // then
        assertThat(savedRoutine).isNotNull();
        assertThat(savedRoutine.getId()).isNotNull();
        assertThat(savedRoutine.getName()).isEqualTo("Test Routine");
        assertThat(savedRoutine.getRoutineGoal()).isEqualTo("Strength Improvement");
        assertThat(savedRoutine.getUserId()).isEqualTo(testUser.getId());

        RoutineDTO foundRoutine = routineService.getRoutineById(savedRoutine.getId());
        assertThat(foundRoutine).isNotNull()
                .extracting("name", "routineGoal", "userId")
                .containsExactly("Test Routine", "Strength Improvement", testUser.getId());
    }

    @Test
    void testUpdateRoutine() {
        // given
        RoutineDTO savedRoutine = routineService.addRoutine(testRoutineDTO);

        RoutineDTO updateDTO = RoutineDTO.builder()
                .id(savedRoutine.getId())
                .name("Updated Routine")
                .routineGoal("Weight Loss")
                .userId(testUser.getId())
                .useYn(true)
                .deleteYn(false)
                .build();

        // when
        RoutineDTO updatedRoutine = routineService.updateRoutine(savedRoutine.getId(), updateDTO);

        // then
        assertThat(updatedRoutine).isNotNull()
                .extracting("name", "routineGoal")
                .containsExactly("Updated Routine", "Weight Loss");
    }

    @Test
    void testGetNonExistingRoutine() {
        assertThrows(RuntimeException.class, () -> routineService.getRoutineById(999));
    }

    @Test
    void testDeleteRoutine() {
        // given
        RoutineDTO savedRoutine = routineService.addRoutine(testRoutineDTO);

        // when
        routineService.deleteRoutine(savedRoutine.getId());

        // then
        assertThrows(RuntimeException.class, () -> routineService.getRoutineById(savedRoutine.getId()));
    }

    @Test
    void testGetAllRoutines() {
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
        assertThat(routines)
                .isNotNull()
                .hasSize(2)
                .extracting("name")
                .containsExactlyInAnyOrder("Test Routine", "Second Routine");
    }

    @Test
    void testCreateRoutineWithInvalidUserId() {
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
