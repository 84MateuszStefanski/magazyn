package utils;

import daoservices.ProductSearchEngine;
import entities.Product;
import org.hibernate.Session;

import javax.persistence.NoResultException;

public class Test {

    public static void main(String[] args) throws NoResultException{
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

      try {
          Product product = ProductSearchEngine.getProductById(53,session);
          System.out.println(product.getProductName());
      }catch (NoResultException e){
          e.printStackTrace();
      }

        session.getTransaction().commit();
        HibernateUtil.close();

    }

}
