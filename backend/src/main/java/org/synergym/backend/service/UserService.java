package org.synergym.backend.service;

import org.synergym.backend.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO getUserByUsername(String username);
    UserDTO addUser(UserDTO userDTO);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    List<UserDTO> getAllUsers();
}
