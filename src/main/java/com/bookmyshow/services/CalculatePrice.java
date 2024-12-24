package com.bookmyshow.services;

import com.bookmyshow.models.SeatType;
import com.bookmyshow.strategies.PricingStrategy;
import org.springframework.stereotype.Service;

@Service
public class CalculatePrice {
    public double calculatePrice(double basePrice, SeatType seatType){
        PricingStrategy pricingStrategy = seatType.getPricingStrategy();
        // Calculate and return the price using the selected strategy
        return pricingStrategy.calculatePrice(basePrice,1);
    }
}
