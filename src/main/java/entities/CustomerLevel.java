package entities;

import lombok.Getter;

@Getter
public enum CustomerLevel {

    STANDARD_0(1.0),
    SILVER_10(0.9),
    GOLD_20(0.8),
    PLATINUM_30(0.7);

    private final Double discount;

    CustomerLevel(Double discount) {
        this.discount = discount;
    }
}
