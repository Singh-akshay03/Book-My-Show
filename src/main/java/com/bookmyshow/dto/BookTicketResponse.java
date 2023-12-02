package com.bookmyshow.dto;

import com.bookmyshow.Models.Ticket;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookTicketResponse {
    private Ticket ticket;
    private ResponseStatus responseStatus;
}
