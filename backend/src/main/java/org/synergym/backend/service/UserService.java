package org.synergym.backend.service;

import org.synergym.backend.dto.RoutineDTO;
import org.synergym.backend.dto.UserDTO;
import org.synergym.backend.entity.Routine;
import org.synergym.backend.entity.User;

import java.util.List;

public interface UserService {
    UserDTO getUserByUsername(String username);
    Integer addUser(UserDTO userDTO);
    void updateUser(Integer id, UserDTO userDTO);
    void deleteUser(Integer id);
    List<UserDTO> getAllUsers();

    default User dtoToEntity(UserDTO dto){
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .role(dto.getRole())
                .email(dto.getEmail())
                .birthDate(dto.getBirthDate())
                .gender(dto.getGender())
                .weight(dto.getWeight())
                .height(dto.getHeight())
                .fitnessLevel(dto.getFitnessLevel())
                .build();
    }

    default UserDTO entityToDto(User user){
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .email(user.getEmail())
                .birthDate(user.getBirthDate())
                .gender(user.getGender())
                .weight(user.getWeight())
                .height(user.getHeight())
                .fitnessLevel(user.getFitnessLevel())
                .build();
    }
}
