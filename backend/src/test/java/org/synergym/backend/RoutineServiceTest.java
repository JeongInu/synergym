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

@SpringBootTest
@Transactional
class RoutineServiceTest {

    @Autowired
    private RoutineService routineService;

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
                .useYN('Y')
                .build();
    }

    @Test
    void createRoutine() {
        Integer routineId = routineService.addRoutine(testRoutineDTO);
        RoutineDTO savedRoutine = routineService.getRoutineById(routineId);

        System.out.println("생성된 루틴: " + savedRoutine);
    }

    @Test
    void updateRoutine() {
        Integer routineId = routineService.addRoutine(testRoutineDTO);

        RoutineDTO updateDTO = RoutineDTO.builder()
                .name("Updated Routine")
                .routineGoal("Weight Loss")
                .userId(testUser.getId())
                .useYN('Y')
                .build();

        routineService.updateRoutine(routineId, updateDTO);

        RoutineDTO updatedRoutine = routineService.getRoutineById(routineId);
        System.out.println("수정된 루틴: " + updatedRoutine);
    }

    @Test
    void getRoutine_NotFound() {
        try {
            RoutineDTO routine = routineService.getRoutineById(999);
            System.out.println("조회된 루틴: " + routine);
        } catch (RuntimeException e) {
            System.out.println("루틴을 찾을 수 없습니다: " + e.getMessage());
        }
    }

    @Test
    void deleteRoutine() {
        Integer routineId = 18;
        routineService.deleteRoutine(routineId);

        try {
            RoutineDTO deleted = routineService.getRoutineById(routineId);
            System.out.println("삭제된 후에도 조회된 루틴: " + deleted);
        } catch (RuntimeException e) {
            System.out.println("루틴 삭제 확인됨: " + e.getMessage());
        }
    }

    @Test
    void getAllRoutines() {
        routineService.addRoutine(testRoutineDTO);

        RoutineDTO secondRoutine = RoutineDTO.builder()
                .name("Second Routine")
                .routineGoal("Flexibility")
                .userId(testUser.getId())
                .useYN('Y')
                .build();
        routineService.addRoutine(secondRoutine);

        List<RoutineDTO> routines = routineService.getAllRoutines();
        System.out.println("전체 루틴 목록:");
        routines.forEach(System.out::println);
    }

    @Test
    void createRoutine_WithInvalidUserId_ThrowsException() {
        RoutineDTO invalidRoutineDTO = RoutineDTO.builder()
                .name("Invalid Routine")
                .routineGoal("Strength")
                .userId(999)
                .useYN('Y')
                .build();

        try {
            routineService.addRoutine(invalidRoutineDTO);
        } catch (RuntimeException e) {
            System.out.println("예외 발생 (유효하지 않은 사용자): " + e.getMessage());
        }
    }
}
