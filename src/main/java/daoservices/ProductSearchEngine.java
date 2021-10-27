package daoservices;

import entities.Product;
import org.hibernate.Session;
import utils.HibernateUtil;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.Optional;

public class ProductSearchEngine {

    public static BigDecimal getGrossSellingPriceByProductId(int id){
        return findProductById(id).getGrossSellingPrice();
    }

    public static Product findProductById(int id) {

        Product searchedProduct = new Product();

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();


        Query query = session.createQuery("FROM Product WHERE productID =" + id);
        try {
             searchedProduct = (Product) query.getSingleResult();
        }catch (NoResultException e){
            e.printStackTrace();
        }
        //todo napisac nullchecka


//        Optional<Product> searchedProductOptional = Optional.of((Product) query.getSingleResult());
//        if (searchedProductOptional.isPresent()) {
//            searchedProduct = searchedProductOptional.get();
//        }else {
//            System.out.println("No such product");
//        }
        session.getTransaction().commit();
        session.close();
        HibernateUtil.close();

        return searchedProduct;
    }



}
