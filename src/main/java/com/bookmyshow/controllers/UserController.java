package com.bookmyshow.controllers;

import com.bookmyshow.dtos.CreateUserResponseDto;
import com.bookmyshow.models.User;
import com.bookmyshow.services.IUserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private IUserService userService;
    public CreateUserResponseDto createUser(User user){
        User createdUser=userService.registerUser(user.getUsername(), user.getEmail(), user.getPassword());
        return mapUserToCreateUserResponseDto(createdUser);
    }

    private CreateUserResponseDto mapUserToCreateUserResponseDto(User user){
        CreateUserResponseDto createUserResponseDto = new CreateUserResponseDto();
        createUserResponseDto.setUserId(user.getId());
        createUserResponseDto.setEmail(user.getEmail());
        return createUserResponseDto;
    }
}
