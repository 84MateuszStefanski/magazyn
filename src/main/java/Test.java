import daoservices.ProductSearchEngine;
import entities.Product;
import org.hibernate.Session;
import utils.HibernateUtil;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.NoResultException;

public class Test {

//    public static void main(String[] args) throws NoResultException{
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//
//      try {
//          System.out.println(ProductSearchEngine.getProductList(session));
//      }catch (NoResultException e){
//          e.printStackTrace();
//      }
//
//        session.getTransaction().commit();
//        HibernateUtil.close();
//
//    }

    public static void main(String[] args) {
        String hash = DigestUtils.sha256Hex("password");
        System.out.println(hash);
    }

}
