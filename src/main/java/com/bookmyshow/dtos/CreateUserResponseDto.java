package com.bookmyshow.dtos;

import lombok.Data;

@Data
public class CreateUserResponseDto {
    private Long userId;
    private String email;
}
