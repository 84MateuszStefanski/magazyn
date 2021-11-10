package customerutils;

import entities.Customer;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.Optional;

public class CustomerSearchEngine {

    /**
     * A helper method that returns a boolean and checks if client is in the database by id
     */
    protected static boolean theCustomerIsInDatabase(int id, Session session) {

        var query = session.createQuery("FROM Customer WHERE customerID=" + id);
        Object customer = Optional.of(query.getSingleResult()).get();
        if (customer instanceof Customer && id == ((Customer) customer).getCustomerID()) {
            return true;
        }
        return false;
    }

    /**
     * A helper method that returns a boolean and checks if client is in the database by nip
     */

    //TODO sprawdzić dlaczego metoda nie działa
    protected static boolean theCustomerIsInDatabaseByNip(String nip) {
        var session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        var query = session.createQuery("FROM Customer WHERE nip=" + nip);
        Object customer = Optional.of(query.getSingleResult()).get();
        if (customer instanceof Customer) {
            return true;
        }
        session.getTransaction().commit();
        session.close();
        HibernateUtil.close();
        return false;
    }

    /**
     * A helper method that returns a Customer who places the order
     */
    protected static Customer getCustomerById(int id , Session session) {

        session.beginTransaction();

        var query = session.createQuery("FROM Customer WHERE customerID=" + id);
        Customer customer = (Customer) Optional.of(query.getSingleResult()).get();

        session.save(customer);
        session.getTransaction().commit();


        return customer;
    }
}
