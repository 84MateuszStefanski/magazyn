import composers.CustomerComposer;
import composers.ProductComposer;
import daoservices.ProductSearchEngine;
import entities.Product;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.math.BigDecimal;

public class App {


    public static void main(String[] args) {

//        CustomerComposer composer = new CustomerComposer();
//        ProductComposer productComposer = new ProductComposer();
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();

        System.out.println(ProductSearchEngine.getGrossSellingPriceByProductId(1).toString());

//        Product product = productComposer.createProduct(
//                "yt-3884",
//                "Tool set",
//                5,
//                BigDecimal.valueOf(330.00)
//        );

//        session.save(product);

//        Customer customer = composer.createCustomer(
//                "Maxtool",
//                "5555555555",
//                "Maxtool spółka jawna",
//                "05-090",
//                "Wschodnia 3",
//                "Raszyn",
//                "Poland",
//                "517771863",
//                "maxtool@pl.pl");

//        session.save(customer);
//        session.getTransaction().commit();
//        session.close();
//        HibernateUtil.close();
    }
}
