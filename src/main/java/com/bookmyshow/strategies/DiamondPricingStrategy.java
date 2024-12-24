package com.bookmyshow.strategies;

public class DiamondPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice, int numberOfSeats) {
        return basePrice * numberOfSeats * 2;
    }
}
