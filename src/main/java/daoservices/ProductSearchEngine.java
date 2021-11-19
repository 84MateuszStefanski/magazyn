package daoservices;

import entities.Product;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductSearchEngine {

    public static String getCatalogNumberById(int id, Session session) throws NoResultException {
        return getProductById(id,session).getCatalogNumber();
    }

    public static String getProductNameById(int id, Session session) throws NoResultException {
        return getProductById(id,session).getProductName();
    }

    public static BigDecimal getGrossSellingPriceById(int id,Session session) throws NoResultException {
        return getProductById(id, session).getGrossSellingPrice();
    }

    public static int getProductQuantityById(int id, Session session){
        return getProductById(id, session).getQuantity();
    }

    public static String getProductDescriptionById(int id,Session session) throws NoResultException {
        return getProductById(id, session).toString();
    }

    /**This method unpacks optional from the methods below and returns a Product class object*/
    private static Product getProductById(int id, Session session) throws NoResultException{
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

    public static List<Product> getProductList(Session session) {
        var query = session.createQuery("FROM Product");
        List<Product> products = new ArrayList<>();
        products = query.getResultList();
        return  products;
    }
}
