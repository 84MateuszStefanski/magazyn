package daoservices;

import entities.Customer;
import org.hibernate.Session;
import utils.HibernateUtil;

import javax.persistence.NoResultException;
import java.util.Optional;

public class CustomerSearchEngine {

    /**
     * A helper method that returns a boolean and checks if client is in the database by id
     */
    protected static boolean theCustomerIsInDatabase(int id, Session session) {
        return false;
    }



    /**This method unpacks optional from the methods below and returns a Product class object*/
    private static Customer getCustomerById(int id , Session session) throws NoResultException{
        return (Customer) getCustomerOptionalById(id,session).get();
    }

    /**This method returns an object of class Customer wrapped in optional*/
    private static Optional<Object> getCustomerOptionalById(int id , Session session) throws NoResultException {

        var query = session.createQuery("FROM Customer WHERE customerID=" + id);
        var customer = Optional.of(query.getSingleResult());
        if (customer.isEmpty()){
            return Optional.empty();
        }else {
            return customer;
        }
    }
}
