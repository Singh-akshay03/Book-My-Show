package com.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Payment extends BaseModel {
    private String referenceNumber;
    private double amount;
    private String currency;
    @Enumerated(EnumType.ORDINAL)
    private PaymentGateway paymentGateway;
    @Enumerated(EnumType.ORDINAL)
    private PaymentMode paymentMode;
}
