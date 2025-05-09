package org.synergym.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.synergym.backend.dto.UserDTO;
import org.synergym.backend.entity.User;
import org.synergym.backend.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user.toDTO();  // toDTO 메서드를 사용
    }

    @Transactional
    @Override
    public UserDTO addUser(UserDTO userDTO) {
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .role(userDTO.getRole())
                .email(userDTO.getEmail())
                .age(userDTO.getAge())
                .gender(userDTO.getGender())
                .weight(userDTO.getWeight())
                .height(userDTO.getHeight())
                .fitnessLevel(userDTO.getFitnessLevel())
                .createdAt(userDTO.getCreatedAt())
                .build();
        user = userRepository.save(user);
        return user.toDTO();  // 저장 후 DTO로 반환
    }

    @Transactional
    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // DTO로 받은 정보를 Entity에 반영
        user.updateFromDTO(userDTO);  // Entity에서 DTO의 정보를 업데이트하는 메서드 호출
        user = userRepository.save(user); // 업데이트된 Entity 저장

        return user.toDTO();  // DTO 반환
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(User::toDTO)  // Entity를 DTO로 변환
                .collect(Collectors.toList());
    }
}
