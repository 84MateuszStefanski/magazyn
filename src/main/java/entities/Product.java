package entities;

import lombok.Getter;
import lombok.Setter;

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
    private String product_name;

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




}
