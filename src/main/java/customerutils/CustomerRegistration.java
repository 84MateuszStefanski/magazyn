package customerutils;

import entities.Customer;
import entities.CustomerLevel;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.Scanner;

public class CustomerRegistration implements CustomerRegistrationInterface {

    private static final Scanner SCANNER = new Scanner(System.in);


    @Override
    public void registerCustomer() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();


        Customer customer = new Customer();

        System.out.println("WELCOME TO REGISTRATION PANEL" + '\n');

        System.out.println("WRITE YOUR COMPANY SHORT NAME AND PRESS ENTER");
        String customerName = SCANNER.nextLine();
        customer.setCustomerName(customerName);

        System.out.println("WRITE YOUR TAX IDENTIFICATION NUMBER AND PRESS ENTER");
        String nip = SCANNER.nextLine();
        customer.setNip(nip);

        System.out.println("WRITE YOUR FULL COMPANY NAME AND PRESS ENTER");
        String fullCompanyName = SCANNER.nextLine();
        customer.setFullCompanyName(fullCompanyName);

        System.out.println("WRITE YOUR CITY AND PRESS ENTER");
        String city = SCANNER.nextLine();
        customer.setCity(city);

        System.out.println("WRITE YOUR COUNTRY AND PRESS ENTER");
        String country = SCANNER.nextLine();
        customer.setCountry(country);

        System.out.println("WRITE YOUR EMAIL CITY AND PRESS ENTER");
        String email = SCANNER.nextLine();
        customer.setEmail(email);

        System.out.println("WRITE YOUR POST CODE AND PRESS ENTER");
        String postCode = SCANNER.nextLine();
        customer.setPostCode(postCode);

        System.out.println("WRITE YOUR STREET AND PRESS ENTER");
        String street = SCANNER.nextLine();
        customer.setStreet(street);

        System.out.println("WRITE YOUR PHONE NUMBER AND PRESS ENTER");
        String phoneNumber = SCANNER.nextLine();
        customer.setPhoneNumber(phoneNumber);
        System.out.println("AS NEW CUSTOMER YOUR CUSTOMER LEVEL WILL BE SET AS STANDARD" + '\n' +
                ", BUY MORE TO GET HIGHER LEVEL");
        customer.setCustomerLevel(CustomerLevel.STANDARD_0);

        session.save(customer);
        session.getTransaction().commit();
        session.close();
        HibernateUtil.close();
    }
}
