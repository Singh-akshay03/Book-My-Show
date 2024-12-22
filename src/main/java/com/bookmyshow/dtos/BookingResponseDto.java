package com.bookmyshow.dtos;

import lombok.Data;

@Data
public class BookingResponseDto {
    private Long bookingId;
    private ResponseStatus status;
}
