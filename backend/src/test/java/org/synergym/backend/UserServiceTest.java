//package org.synergym.backend;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//import org.synergym.backend.dto.UserDTO;
//import org.synergym.backend.repository.UserRepository;
//import org.synergym.backend.service.UserService;
//
//import java.util.List;
//
//@SpringBootTest
//@Transactional
//public class UserServiceTest {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    private UserDTO testUserDTO;
//
//    @BeforeEach
//    void setUp() {
//        testUserDTO = UserDTO.builder()
//                .username("qwe123")
//                .password("password123")
//                .email("test@test.com")
//                .age(25)
//                .gender("M")
//                .weight(70.0f)
//                .height(175.0f)
//                .fitnessLevel("중급")
//                .build();
//    }
//
//    @Test
//    void createUser() {
//        Integer userId = userService.addUser(testUserDTO);
//        UserDTO savedUser = userService.getUserByUsername(testUserDTO.getUsername());
//        System.out.println("생성된 사용자: " + savedUser);
//    }
//
//    @Test
//    void updateUser() {
//        Integer userId = userService.addUser(testUserDTO);
//
//        UserDTO updateDTO = UserDTO.builder()
//                .username("updatedUser")
//                .password("newPassword")
//                .email("updated@test.com")
//                .age(30)
//                .gender("F")
//                .weight(65.0f)
//                .height(165.0f)
//                .fitnessLevel("고급")
//                .build();
//
//        userService.updateUser(userId, updateDTO);
//        UserDTO updatedUser = userService.getUserByUsername("updatedUser");
//        System.out.println("수정된 사용자: " + updatedUser);
//    }
//
//    @Test
//    void getAllUsers() {
//        userService.addUser(testUserDTO);
//
//        UserDTO secondUser = UserDTO.builder()
//                .username("secondUser")
//                .password("password456")
//                .email("second@test.com")
//                .build();
//        userService.addUser(secondUser);
//
//        List<UserDTO> users = userService.getAllUsers();
//        System.out.println("전체 사용자 목록:");
//        users.forEach(System.out::println);
//    }
//
//    @Test
//    void deleteUser() {
//        Integer userId = userService.addUser(testUserDTO);
//        userService.deleteUser(userId);
//
//        try {
//            UserDTO deletedUser = userService.getUserByUsername(testUserDTO.getUsername());
//            System.out.println("삭제된 사용자가 여전히 존재함: " + deletedUser);
//        } catch (RuntimeException e) {
//            System.out.println("사용자가 성공적으로 삭제됨: " + e.getMessage());
//        }
//    }
//}
