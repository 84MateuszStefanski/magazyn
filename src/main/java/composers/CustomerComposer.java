package composers;

import entities.Customer;
import entities.CustomerLevel;

public class CustomerComposer {

    public Customer createCustomer(
            String customerName,
            String nip,
            String fullCompanyName,
            String postCode,
            String street,
            String city,
            String country,
            String phoneNumber,
            String email
    ){
        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        customer.setNip(nip);
        customer.setFullCompanyName(fullCompanyName);
        customer.setCity(city);
        customer.setCountry(country);
        customer.setEmail(email);
        customer.setPostCode(postCode);
        customer.setStreet(street);
        customer.setPhoneNumber(phoneNumber);
        customer.setCustomerLevel(CustomerLevel.STANDARD_0);
        return customer;
    }
}
