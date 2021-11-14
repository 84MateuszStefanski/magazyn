package adminutils;

import daoservices.ProductSearchEngine;
import org.hibernate.Session;

import java.util.Scanner;

public class ProductQuantityMagnifier {

    private static final Scanner SCANNER = new Scanner(System.in);


    void increaseProductQuantity(Session session){
        session.beginTransaction();
        System.out.println("PLEASE WRITE PRODUCT ID WITCH QUANTITY YOU WANT TO INCREASE" + '\n');
        int productId = SCANNER.nextInt();
        int productQuantity = ProductSearchEngine.getProductQuantitySearchedById(productId,session);
        System.out.println("ACTUAL QUANTITY IS " + productQuantity + '\n');
        System.out.println("PLEASE WRITE QUANTITY YOU WANT TO INCREASE" + '\n');
        int productQuantityToIncrease = SCANNER.nextInt();
        int productQuantityAfterIncrese = productQuantity + productQuantityToIncrease;
        var query = session.createQuery(
                      "UPDATE Product " +
                        "SET quantity = " + productQuantityAfterIncrese +
                        " WHERE productID=" + productId);

        query.executeUpdate();
        session.getTransaction().commit();
        System.out.println("NOW THERE IS " + productQuantityAfterIncrese + " PIECES");
    }

}
