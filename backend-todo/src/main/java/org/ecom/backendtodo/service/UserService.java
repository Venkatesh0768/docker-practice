package org.ecom.backendtodo.service;

import org.ecom.backendtodo.dto.UserRequestDto;
import org.ecom.backendtodo.dto.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponseDto createUser(UserRequestDto requestDto);
    List<UserResponseDto> getAllUser();
    UserResponseDto getUserById(UUID userId);
    UserResponseDto updateUser(UUID userId , UserRequestDto requestDto);
    void deleteUser(UUID userId);
}
