package com.example.task.user;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserDto {
    private Long id;

    @NotBlank
    private String login;
}
