package com.example.ProjectA.Mapper;

import com.example.ProjectA.dto.User.UserDto;
import com.example.ProjectA.entity.User;

public class UserMapper {

    public static UserDto UserMapperToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setAddress(user.getAddress());
        userDto.setPhone(user.getPhone());
        userDto.setImage(user.getImage());
        userDto.setRole(user.getRole().getName());
        return userDto;
    }
}
