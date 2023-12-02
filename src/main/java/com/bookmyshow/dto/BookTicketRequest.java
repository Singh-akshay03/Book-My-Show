package com.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class BookTicketRequest {
    private long userId;

    private long showId;

    private List<Long> seatId;
}
