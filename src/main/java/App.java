import daoservices.ProductSearchEngine;
import utils.AdminPanel;

import javax.persistence.NoResultException;

public class App {


    public static void main(String[] args) {

        AdminPanel.runAdminPanel();

//        CustomerComposer composer = new CustomerComposer();
//        ProductComposer productComposer = new ProductComposer();
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();

//        try {
//            System.out.println(ProductSearchEngine.getGrossSellingPriceByCatalogNumber("'yt-3884'"));
//        }catch (NoResultException e){
//            e.printStackTrace();
//            System.out.println("No such product");
//        }
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
