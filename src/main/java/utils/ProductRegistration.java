package utils;

import entities.Product;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Scanner;

public class ProductRegistration implements ProductRegistrationInterface{

    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public void createProduct() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Product product = new Product();

        System.out.println("WRITE PROCUCT CATALOG NUMBER AND PRESS ENTER");
        String catalogNumber = SCANNER.nextLine();
        product.setCatalogNumber(catalogNumber);

        System.out.println("WRITE PROCUCT NAME AND PRESS ENTER");
        String productName = SCANNER.nextLine();
        product.setProductName(productName);

        System.out.println("WRITE PROCUCT PURCHASE PRICE NET AND PRESS ENTER");
        BigDecimal netPurchasePrice = new BigDecimal(SCANNER.nextLine());
        product.setNetPurchasePrice(netPurchasePrice);

        System.out.println("WRITE PROCUCT QUANTITY AND PRESS ENTER");
        Integer quantity = SCANNER.nextInt();
        product.setQuantity(quantity);

        product.setGrossPurchasePrice(grossPurchasePriceCalculate(netPurchasePrice));
        product.setNetSellingPrice(netSellPriceCalculate(netPurchasePrice));
        product.setGrossSellingPrice(grossSellPriceCalculate(product.getNetSellingPrice()));

        session.save(product);
        session.getTransaction().commit();
        session.close();
        HibernateUtil.close();
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

    private BigDecimal grossSellPriceCalculate(BigDecimal netSellingPrice) {
        if (isNotNullOrZero(netSellingPrice)) {
            BigDecimal grossSellingPrice =
                    netSellingPrice.add(netSellingPrice.multiply(Tax.TAX_23.getTaxRate()));
            return grossSellingPrice;
        } else {
            return BigDecimal.ZERO;
        }

    }

    private boolean isNotNullOrZero(BigDecimal price) {
        return price != null && price.compareTo(BigDecimal.ZERO) > 0;
    }


}
