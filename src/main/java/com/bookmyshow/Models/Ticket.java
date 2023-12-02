package com.bookmyshow.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Ticket extends BaseModel {
    @ManyToOne
    private User bookedBy;
    @ManyToOne
    private Show show;
    @OneToMany
    private List<ShowSeat> showSeats;
    private double totalAmount;
    @Enumerated(EnumType.ORDINAL)
    private TicketStatus ticketStatus;
    @OneToMany
    private List<Payment> payments;
}
