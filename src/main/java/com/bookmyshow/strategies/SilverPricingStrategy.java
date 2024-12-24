package com.bookmyshow.strategies;

public class SilverPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice, int numberOfSeats) {
        return basePrice * numberOfSeats * 0.8;
    }
}
