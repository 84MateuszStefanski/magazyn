import org.hibernate.Session;
import utils.HibernateUtil;

public class App {


    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();




//        Customer customer = new Customer();
//        customer.setCustomerID(1);
//        customer.setCustomerName("Maxtool");
//        customer.setNip("5555555555");
//        customer.setFullCompanyName("Maxtool spółka jawna");
//        customer.setCity("Raszyn");
//        customer.setCountry("Poland");
//        customer.setEmail("maxtool@pl.pl");
//        customer.setPostCode("05-090");
//        customer.setStreet("Wschodnia 3");
//        customer.setPhoneNumber("517771863");
//
//
//        session.save(customer);
        session.getTransaction().commit();
        session.close();
        HibernateUtil.close();
    }
}