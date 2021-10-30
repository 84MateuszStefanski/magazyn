package utils;

import entities.Product;

import java.math.BigDecimal;

@FunctionalInterface
public interface ProductRegistrationInterface {

    Product createProduct(String catalogNumber,
                          String product_name,
                          Integer quantity,
                          BigDecimal netPurchasePrice);
}
