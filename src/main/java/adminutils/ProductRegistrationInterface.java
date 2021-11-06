package adminutils;

import org.hibernate.Session;

@FunctionalInterface
public interface ProductRegistrationInterface {

    void createProduct(Session session);
}
