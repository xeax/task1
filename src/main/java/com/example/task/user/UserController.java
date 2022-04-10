package com.example.task.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Api(tags = "User controller")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ApiOperation("Register user")
    public ResponseEntity<UserDto> create(UriComponentsBuilder b, @Valid @RequestBody UserDto userDto) {
        UserDto userDtoCreated = userService.register(userDto);
        return ResponseEntity.ok(userDtoCreated);
    }
}