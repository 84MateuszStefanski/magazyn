package adminutils;

import java.math.BigDecimal;

enum Tax {
    TAX_8(BigDecimal.valueOf(0.08)),
    TAX_23(BigDecimal.valueOf(0.23));

    private final BigDecimal taxRate;

    Tax(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }
}
