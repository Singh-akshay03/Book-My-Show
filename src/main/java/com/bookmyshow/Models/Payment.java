package com.bookmyshow.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Payment extends BaseModel {
    private PaymentMethod paymentMethod;
    private Date timeOfPayment;
    private String referenceId;

    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus;
    @ManyToOne
    private Ticket ticket;
}
