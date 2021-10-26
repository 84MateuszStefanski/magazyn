package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Table(name = "orders")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderID;

    @ManyToOne
    @JoinColumn(name = "customerID")
    private Customer customer;

    @Column
    private LocalDate orderDate;

    @Column
    private LocalDate shippedDate;

    @Column
    private OrderStatus status;

    @OneToOne
    private OrderDetails orderDetails;





}
