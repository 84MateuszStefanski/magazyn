package entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Table(name = "products")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productID;

    @Column(name = "catalog_number")
    private String catalogNumber;


    @Column(name = "product_name")
    private String productName;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "net_purchase_price")
    private BigDecimal netPurchasePrice;

    @Column(name = "gross_purchase_price")
    private BigDecimal grossPurchasePrice;

    @Column(name = "net_selling_price")
    private BigDecimal netSellingPrice;

    @Column(name = "gross_selling_price")
    private BigDecimal grossSellingPrice;


    @Override
    public String toString() {
        return "Product description " + '\n' +
                " productID = " + productID + '\n' +
                " catalogNumber = " + catalogNumber + '\n' +
                " productName = " + productName + '\n' +
                " quantity = " + quantity + '\n' +
                " netPurchasePrice = " + netPurchasePrice + '\n' +
                " grossPurchasePrice = " + grossPurchasePrice + '\n' +
                " netSellingPrice = " + netSellingPrice + '\n' +
                " grossSellingPrice = " + grossSellingPrice;
    }
}
