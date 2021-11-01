package daoservices;

import entities.Product;
import utils.HibernateUtil;

import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.util.Optional;

public class ProductSearchEngine {


    public static BigDecimal getGrossSellingPriceByProductId(int id) throws NoResultException {
        var product = (Product) findProductById(id).get();
        return product.getGrossSellingPrice();
    }

    public static String getProductDescriptionByProductId(int id) throws NoResultException {
        var product = (Product) findProductById(id).get();
        return product.toString();
    }

    public static BigDecimal getGrossSellingPriceByCatalogNumber(String catalogNumber) throws NoResultException {
        var product = (Product) findProductByCatalogNumber(catalogNumber).get();
        return product.getGrossSellingPrice();
    }

    public static String getProductDescriptionByCatalogNumber(String catalogNumber) throws NoResultException {
        var product = (Product) findProductByCatalogNumber(catalogNumber).get();
        return product.toString();
    }

    public static Optional<Object> findProductById(int id) {

        var session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

            var query = session.createQuery("FROM Product WHERE productID =" + id);
            var product = Optional.of(query.getSingleResult());
        if (product.isEmpty()){
            return Optional.empty();
        }

        session.getTransaction().commit();
        session.close();
        HibernateUtil.close();

        return product;

    }

    public static Optional<Object> findProductByCatalogNumber(String number) {

        var session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        var query = session.createQuery("FROM Product WHERE catalogNumber =" + number);
        var product = Optional.of(query.getSingleResult());
        if (product.isEmpty()){
            return Optional.empty();
        }

        session.getTransaction().commit();
        session.close();
        HibernateUtil.close();

        return product;

    }

    public static int checkProductAvailability(int id){
        var session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();




        session.getTransaction().commit();
        session.close();
        HibernateUtil.close();
    }

}
