package com.bookmyshow.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CreateBookingRequestDto {
    private Long userId;
    private Long showId;
    List<Long> showSeatIds;
}
