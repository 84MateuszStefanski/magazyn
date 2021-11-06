package customerutils;

import org.hibernate.Session;

@FunctionalInterface
public interface CustomerRegistrationInterface {

    void registerCustomer(Session session);
}
