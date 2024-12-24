package com.bookmyshow.strategies;

public interface PricingStrategy {
    public double calculatePrice(double basePrice,int numberOfSeats);
}
