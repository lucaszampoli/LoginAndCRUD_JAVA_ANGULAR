package com.zampo.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
        Long id,
        @NotBlank @NotNull @Length(min = 5, max = 200) String name,
        @NotBlank @NotNull @Email String email,
        @NotNull @Length(max = 10) String profile,
        @NotBlank @NotNull String status,
        @NotBlank @NotNull @Length(min = 5, max = 200) String password) {
}
