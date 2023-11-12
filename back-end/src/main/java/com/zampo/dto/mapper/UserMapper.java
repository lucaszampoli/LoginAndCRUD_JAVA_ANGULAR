package com.zampo.dto.mapper;

import org.springframework.stereotype.Component;

import com.zampo.dto.UserDTO;
import com.zampo.model.User;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getProfile(), user.getStatus(), user.getPassword());
    }

    public User toEntity(UserDTO userDTO) {

        if (userDTO == null) {
            return null;
        }

        User user = new User();
        if (userDTO.id() != null) {
            user.setId(userDTO.id());
        }
        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setProfile(userDTO.profile());
        user.setStatus(userDTO.status());
        user.setPassword(userDTO.password());

        return user;
    }

}
