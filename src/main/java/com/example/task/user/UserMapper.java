package com.example.task.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * The User mapper.
 */
@Mapper
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User map(UserDto userDto);

    UserDto map(User user);
}
