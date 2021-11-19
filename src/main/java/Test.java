import daoservices.ProductSearchEngine;
import entities.Product;
import org.hibernate.Session;
import utils.HibernateUtil;

import javax.persistence.NoResultException;

public class Test {

    public static void main(String[] args) throws NoResultException{
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

      try {
          System.out.println(ProductSearchEngine.getProductList(session));
      }catch (NoResultException e){
          e.printStackTrace();
      }

        session.getTransaction().commit();
        HibernateUtil.close();

    }

}
