package adminutils;

import entities.Product;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.math.BigDecimal;
import java.util.Scanner;

public class ProductRegistration implements ProductRegistrationInterface{

    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public void createProduct(Session session) {

        session.beginTransaction();

        Product product = new Product();
        System.out.println("WELCOME TO PRODUCT REGISTRATION PANEL" + '\n');

        System.out.println("WRITE PRODUCT CATALOG NUMBER AND PRESS ENTER");
        String catalogNumber = SCANNER.nextLine();
        product.setCatalogNumber(catalogNumber);

        System.out.println("WRITE PRODUCT NAME AND PRESS ENTER");
        String productName = SCANNER.nextLine();
        product.setProductName(productName);

        System.out.println("WRITE PRODUCT PURCHASE PRICE NET AND PRESS ENTER");
        BigDecimal netPurchasePrice = new BigDecimal(SCANNER.nextLine());
        product.setNetPurchasePrice(netPurchasePrice);

        System.out.println("WRITE PRODUCT QUANTITY AND PRESS ENTER");
        Integer quantity = SCANNER.nextInt();
        product.setQuantity(quantity);

        product.setGrossPurchasePrice(grossPurchasePriceCalculate(netPurchasePrice));
        product.setNetSellingPrice(netSellPriceCalculate(netPurchasePrice));
        product.setGrossSellingPrice(grossSellPriceCalculate(product.getNetSellingPrice()));

        session.save(product);
        session.getTransaction().commit();
    }

    protected BigDecimal grossPurchasePriceCalculate(BigDecimal netPrice) {
        if (isNotNullOrZero(netPrice)) {
            BigDecimal grossPrice =
                    netPrice.add(netPrice.multiply(Tax.TAX_23.getTaxRate()));
            return grossPrice;
        } else {
            return BigDecimal.ZERO;
        }

    }

    protected BigDecimal netSellPriceCalculate(BigDecimal netPurchasePrice) {
        if (isNotNullOrZero(netPurchasePrice)) {
            BigDecimal nettSellingPrice =
                    netPurchasePrice.add(netPurchasePrice.multiply(BigDecimal.valueOf(0.6)));
            return nettSellingPrice;
        } else {
            return BigDecimal.ZERO;
        }
    }

    protected BigDecimal grossSellPriceCalculate(BigDecimal netSellingPrice) {
        if (isNotNullOrZero(netSellingPrice)) {
            BigDecimal grossSellingPrice =
                    netSellingPrice.add(netSellingPrice.multiply(Tax.TAX_23.getTaxRate()));
            return grossSellingPrice;
        } else {
            return BigDecimal.ZERO;
        }

    }

    protected boolean isNotNullOrZero(BigDecimal price) {
        return price != null && price.compareTo(BigDecimal.ZERO) > 0;
    }


}
