package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "customers")
@Entity
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerID;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "nip")
    private String nip;

    @Column(name = "full_company)")
    private String fullCompanyName;

    @Column(name = "post_code)")
    private String postCode;

    @Column(name = "street)")
    private String street;

    @Column(name = "city)")
    private String city;

    @Column(name = "country)")
    private String country;

    @Column(name = "phone_number)")
    private String phoneNumber;

    @Column(name = "email)")
    private String email;
}
