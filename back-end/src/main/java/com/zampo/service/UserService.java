package com.zampo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import com.zampo.dto.UserDTO;
import com.zampo.dto.mapper.UserMapper;
import com.zampo.repository.UserRepository;
import com.zampo.exception.RecordNotFoundException;
import com.zampo.model.User;

@Validated
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDTO> list() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO findById(@NotNull @Positive Long id) {
        return userRepository.findById(id).map(userMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public UserDTO create(@Valid @NotNull UserDTO user) {
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(user)));
    }

    public UserDTO update(@NotNull @Positive Long id, @Valid @NotNull UserDTO userDTO) {
        return userRepository.findById(id)
                .map(recordFound -> {
                    User user = userMapper.toEntity(userDTO);
                    recordFound.setName(userDTO.name());
                    recordFound.setEmail(userDTO.email());
                    recordFound.setProfile(userDTO.profile());
                    recordFound.setStatus(userDTO.status());
                    return userMapper.toDTO(userRepository.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        userRepository.delete(userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
