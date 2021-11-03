package customerutils;

import daoservices.ProductSearchEngine;
import entities.Order;
import entities.OrderDetails;
import entities.OrderStatus;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Scanner;

public class OrderSubmitPanel {

    private static final Scanner SCANNER = new Scanner(System.in);
    private Order order;

    public void submitOrder(){

        System.out.println("SUBMIT YOUR ORDER ");
        System.out.println("ENTER YOUR ID NUMBER AND PRESS ENTER");
        int customerId = SCANNER.nextInt();
        if (!CustomerSearchEngine.theCustomerIsInDatabase(customerId)){
            System.out.println("PLEASE REGISTER, YOU ARE NOT IN OUR DATABASE");
        }else {
            System.out.println("WELCOME " + CustomerSearchEngine.getCustomerById(customerId).getCustomerName());
            placeAnOrder(customerId);
        }

    }

    protected void placeAnOrder(int customerId){


        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        this.order = new Order();
        order.setCustomer(CustomerSearchEngine.getCustomerById(customerId));
        order.setOrderDate(LocalDateTime.now().toLocalDate());
        order.setStatus(OrderStatus.ACCEPTED_NOT_PAID);
        order.setShippedDate(LocalDateTime.now().plusDays(2L).toLocalDate());
        order.setOrderDetails(placeOrderDetailsToYourOrder());



        session.save(order);
        session.getTransaction().commit();
        session.close();
        HibernateUtil.close();

    }

    protected OrderDetails placeOrderDetailsToYourOrder(){
        OrderDetails orderDetails = new OrderDetails();
        System.out.println("WRITE PRODUCT ID OF THE PRODUCT THAT YOU WANT TO ORDER");
        int productId = SCANNER.nextInt();
        orderDetails.setProductID(productId);
        System.out.println("HOW MANY PIECES YOU WANT TO ORDER?");
        int quantity = SCANNER.nextInt();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        orderDetails.setQuantityOrdered(quantity);
        if (lowerStockQuantityOfProduct(productId, quantity)){
            orderDetails.setTotalAmount(countTotalAmount(productId, quantity));
            System.out.println("TOTAL AMOUNT IS : " + countTotalAmount(productId, quantity).toString());

        }
        //todo dopisac elsa

        return orderDetails;
    }

    private boolean lowerStockQuantityOfProduct(int productId, int quantityOrdered){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        var originalQuantity = ProductSearchEngine.returnProductQuantitySearchedById(productId);
        int finalQuantity = originalQuantity - quantityOrdered;

        if (quantityOrdered <= originalQuantity){
            var query = session.createQuery("UPDATE products SET quantity =" + finalQuantity + "WHERE productID="+productId);
            session.save(query);
            session.getTransaction().commit();
            session.close();
            HibernateUtil.close();
            return true;
        }else {
            System.out.println("THERE IS NO SUCH QUANTITY FOR THIS PRODUCT." +
                    "WE HAVE ONLY " + originalQuantity + " PIECES");
            return false;
        }
    }

    private BigDecimal countTotalAmount(int productId, int quantityOrdered){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        BigDecimal price =
        ProductSearchEngine.getGrossSellingPriceByProductId(productId);
        BigDecimal totalAmount = price.multiply(BigDecimal.valueOf(quantityOrdered));

        session.getTransaction().commit();
        session.close();
        HibernateUtil.close();
        return totalAmount;
    }
}
