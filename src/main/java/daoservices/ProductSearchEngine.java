package daoservices;

import entities.Product;
import org.hibernate.Session;
import utils.HibernateUtil;

import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.util.Optional;

public class ProductSearchEngine {

    public static String getProductNameById(int id, Session session) throws NoResultException {
        return getProductById(id,session).getProductName();
    }

    public static BigDecimal getGrossSellingPriceByProductId(int id,Session session) throws NoResultException {
        var product = (Product) getProductOptionalById(id,session).get();
        return product.getGrossSellingPrice();
    }

    public static String getProductDescriptionByProductId(int id,Session session) throws NoResultException {
        var product = (Product) getProductOptionalById(id, session).get();
        return product.toString();
    }

    public static BigDecimal getGrossSellingPriceByCatalogNumber(String catalogNumber) throws NoResultException {
        var product = getProductByCatalogNumber(catalogNumber).get();
        return product.getGrossSellingPrice();
    }

    public static String getProductDescriptionByCatalogNumber(String catalogNumber) throws NoResultException {
        var product = (Product) findProductByCatalogNumber(catalogNumber).get();
        return product.toString();
    }

    /**This method unpacks optional from the methods below and returns a Product class object*/
    public static Product getProductById(int id, Session session) throws NoResultException{
        return (Product)getProductOptionalById(id,session).get();

    }
    /**This method returns an object of class Product wrapped in optional*/
    private static Optional<Object> getProductOptionalById(int id, Session session) throws NoResultException{

        var query = session.createQuery("FROM Product WHERE productID =" + id);
        var product = Optional.ofNullable(query.getSingleResult());
        if (product.isEmpty()){
            return Optional.empty();
        }
        return product;

    }

    public static Optional<Object> getProductByCatalogNumber(String catalogNumber, Session session) {

        var query = session.createQuery("FROM Product WHERE catalogNumber =" + catalogNumber);
        var product = Optional.of(query.getSingleResult());
        if (product.isEmpty()){
            return Optional.empty();
        }
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
