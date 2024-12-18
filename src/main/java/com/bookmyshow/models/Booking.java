package com.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{
    private String bookingNumber;
    @ManyToOne
    private User user;
    @ManyToOne
    private Show show;
    @ManyToMany
    private List<ShowSeat> showSeats;//in case of cancel one showSeat can belong to multiple bookings
    private double amount;
    @OneToMany
    List<Payment> payments;
    @Enumerated(EnumType.ORDINAL)
    BookingStatus bookingStatus;
}
