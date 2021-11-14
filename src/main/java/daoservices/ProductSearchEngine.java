package daoservices;

import entities.Product;
import org.hibernate.Session;
import utils.HibernateUtil;

import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.util.Optional;

public class ProductSearchEngine {


    public static BigDecimal getGrossSellingPriceByProductId(int id,Session session) throws NoResultException {
        var product = (Product) findProductById(id,session).get();
        return product.getGrossSellingPrice();
    }

    public static String getProductDescriptionByProductId(int id,Session session) throws NoResultException {
        var product = (Product) findProductById(id, session).get();
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

    public static Optional<Object> findProductById(int id,Session session) {
        session.beginTransaction();

            var query = session.createQuery("FROM Product WHERE productID =" + id);
            var product = Optional.of(query.getSingleResult());
        if (product.isEmpty()){
            return Optional.empty();
        }

        session.getTransaction().commit();

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

    public static int checkProductAvailability(String catalogNumber){
        var session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        var query = session.createQuery("quantity FROM Product WHERE catalogNumber =" + catalogNumber);
        var productQuantityOptional = Optional.of(query.getSingleResult());
        int productQuantity = (int) productQuantityOptional.get();

        if (productQuantityOptional.isEmpty()) {
            System.out.println("NO SUCH PRODUCT IN SALE");
            productQuantity = 0;
            return productQuantity;
        }

        session.getTransaction().commit();
        session.close();
        HibernateUtil.close();

        return productQuantity;
    }

    public static int getProductQuantitySearchedById(int id, Session session){
        var query = session.createQuery("SELECT quantity FROM Product WHERE productID=" + id);
        var productQuantityOptional = Optional.of(query.getSingleResult());
        int productQuantity = (int) productQuantityOptional.get();

        if (productQuantityOptional.isEmpty()) {
            System.out.println("NO SUCH PRODUCT IN SALE");
            productQuantity = 0;
            return productQuantity;
        }
        return productQuantity;
    }

}
