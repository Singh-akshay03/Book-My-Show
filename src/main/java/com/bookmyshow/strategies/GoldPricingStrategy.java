package com.bookmyshow.strategies;

public class GoldPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice, int numberOfSeats) {
        return basePrice * numberOfSeats * 1.2;
    }
}
