package composers;

import entities.Product;

import java.math.BigDecimal;

public class ProductComposer {

    public void createProduct(
            String catalogNumber,
            String product_name,
            Integer quantity,
            BigDecimal netPurchasePrice) {

        Product product = new Product();
        product.setCatalogNumber(catalogNumber);
        product.setProduct_name(product_name);
        product.setNetPurchasePrice(netPurchasePrice);
        product.setQuantity(quantity);

        product.setGrossPurchasePrice(grossPurchasePriceCalculate(netPurchasePrice));
        product.setNetSellingPrice(netSellPriceCalculate(netPurchasePrice));
        product.setGrossSellingPrice(product.getNetSellingPrice());
    }


    private BigDecimal grossPurchasePriceCalculate(BigDecimal netPrice) {
        if (isNotNullOrZero(netPrice)) {
            BigDecimal grossPrice =
                    netPrice.add(netPrice.multiply(Tax.TAX_23.getTaxRate()));
            return grossPrice;
        } else {
            return BigDecimal.ZERO;
        }

    }

    private BigDecimal netSellPriceCalculate(BigDecimal netPurchacePrice) {
        if (isNotNullOrZero(netPurchacePrice)) {
            BigDecimal nettSellingPrice =
                    netPurchacePrice.add(netPurchacePrice.multiply(BigDecimal.valueOf(0.6)));
            return nettSellingPrice;
        } else {
            return BigDecimal.ZERO;
        }
    }

    private BigDecimal grossSellPriceCalculate(BigDecimal netSellingPrice){
        if (isNotNullOrZero(netSellingPrice)) {
            BigDecimal grossSellingPrice =
                    netSellingPrice.add(netSellingPrice.multiply(Tax.TAX_23.getTaxRate()));
            return grossSellingPrice;
        }else {
            return BigDecimal.ZERO;
        }

    }

    private boolean isNotNullOrZero(BigDecimal price){
        return price != null && price.compareTo(BigDecimal.ZERO) > 0;
    }

}
