package customerutils;

import utils.HibernateUtil;

import java.util.Optional;
import java.util.Scanner;

public class OrderSubmitPanel {

    private static final Scanner SCANNER = new Scanner(System.in);

    public void submitOrder(){

        System.out.println("SUBMIT YOUR ORDER ");
        System.out.println("ENTER YOUR ID NUMBER AND PRESS ENTER");
        int id = SCANNER.nextInt();
        if (!theCustomerIsInDatabase(id)){
            System.out.println("PLEASE REGISTER, YOU ARE NOT IN OUR DATABASE");
        }else {
            //todo
        }

    }

    /**A helper method that returns a boolean and checks if client is in the database by id*/
    protected boolean theCustomerIsInDatabase(int id){
        var session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        var query = session.createQuery("FROM Customer WHERE customerID=" + id);
        var customer = Optional.of(query.getSingleResult());
        if (customer.isEmpty()){
            return false;
        }
        return true;
    }

    /**
     * this method will be use in near future
     * */
    protected boolean theCustomerIsInDatabase(String nip){
        var session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        var query = session.createQuery("FROM Customer WHERE nip=" + nip);
        var customer = Optional.of(query.getSingleResult());
        if (customer.isEmpty()){
            return false;
        }
        return true;
    }
}
