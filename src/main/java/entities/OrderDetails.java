package entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Entity
@Table(name="orderdetails")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderID;

    @Column
    @JoinTable(name = "productID")
    private Integer productID;

    @Column
    @JoinTable(name = "productName")
    private String productName;

    @Column
    private int quantityOrdered;

    @Column
    @JoinTable(name = "gross_selling_price")
    private BigDecimal grossSellingPrice;

    @Column
    private BigDecimal totalAmount;
}
