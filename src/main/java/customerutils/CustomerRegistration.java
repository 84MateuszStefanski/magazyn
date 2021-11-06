package customerutils;

import entities.Customer;
import entities.CustomerLevel;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CustomerRegistration implements CustomerRegistrationInterface {

    private static final Scanner SCANNER = new Scanner(System.in);


    @Override
    public void registerCustomer(Session session) {

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

        String email;
        do {
            System.out.println("WRITE YOUR EMAIL CORRECTLY AND PRESS ENTER");
            email = SCANNER.nextLine();
        }while (!checkEmailCorrectness(email));

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
        customer.setCustomerLevel(CustomerLevel.STANDARD_0);

        if (!theCustomerIsInDatabaseByNip(nip, session)){
            session.save(customer);
            session.getTransaction().commit();

            System.out.println("YOU HAVE BEEN REGISTERED, WE INVITE YOU TO PLACE AN ORDER" + '\n');
            System.out.println("AS NEW CUSTOMER YOUR CUSTOMER LEVEL WILL BE SET AS STANDARD" + '\n' +
                    ", BUY MORE TO GET HIGHER LEVEL" + '\n');
        }else {
            System.out.println("YOU ARE ALREADY OUR CUSTOMER, WE INVITE YOU TO PLACE AN ORDER"+ '\n');
        }
    }

    private boolean theCustomerIsInDatabaseByNip(String nip,Session session) throws NoResultException {
        Object customer = null;

        try {
            var query = session.createQuery("FROM Customer WHERE nip=" + nip);
            customer = query.getSingleResult();
        }catch (NoResultException e){
            e.printStackTrace();
        }


        return customer != null;
    }

    private boolean checkEmailCorrectness(String email){
        Pattern checker = Pattern.compile("[A-Z0-9a-z._%+-/]+@[A-Za-z0-9.-]+.[A-Za-z]{2,64}");
        return checker.matcher(email).matches();
    }
}
