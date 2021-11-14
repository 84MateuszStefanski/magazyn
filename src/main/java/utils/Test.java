package utils;

import daoservices.ProductSearchEngine;
import org.hibernate.Session;

public class Test {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        System.out.println(ProductSearchEngine.getProductQuantitySearchedById(3, session));
        session.getTransaction().commit();
        HibernateUtil.close();
    }

}
