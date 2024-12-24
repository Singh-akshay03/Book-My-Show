package com.bookmyshow.models;

import com.bookmyshow.strategies.*;
import lombok.Getter;

@Getter
public enum SeatType {
    SILVER(new SilverPricingStrategy()),
    GOLD(new GoldPricingStrategy()),
    PLATINUM(new PlatinumPricingStrategy()),
    DIAMOND(new DiamondPricingStrategy()),
    RECLINER(new ReclinerPricingStrategy());

    private final PricingStrategy pricingStrategy;

    SeatType(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

}
