package org.ecom.backendtodo.service.impl;

import lombok.RequiredArgsConstructor;
import org.ecom.backendtodo.dto.UserRequestDto;
import org.ecom.backendtodo.dto.UserResponseDto;
import org.ecom.backendtodo.exceptions.UserNotFoundException;
import org.ecom.backendtodo.model.User;
import org.ecom.backendtodo.repository.UserRepository;
import org.ecom.backendtodo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto createUser(UserRequestDto requestDto) {
        Optional<User> existUser = userRepository.findByUsername(requestDto.getUsername());

        if (existUser.isPresent()) {
            throw new RuntimeException("User Already Exits");
        }

        User user = new User();
        user.setUsername(requestDto.getUsername());
        User savedUser = userRepository.save(user);

        return toDtos(savedUser);
    }

    @Override
    public List<UserResponseDto> getAllUser() {
        return List.of();
    }

    @Override
    public UserResponseDto getUserById(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not by this id" + userId));

        return toDtos(user);
    }

    @Override
    public UserResponseDto updateUser(UUID userId, UserRequestDto requestDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not by this id" + userId));


        user.setUsername(requestDto.getUsername());
        User savedUser = userRepository.save(user);

        return toDtos(savedUser);
    }

    @Override
    public void deleteUser(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not by this id" + userId));
        userRepository.deleteById(userId);
    }


    private UserResponseDto toDtos(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
}
