
package customerutils;

import static daoservices.ProductSearchEngine.*;
import entities.*;
import org.hibernate.Session;
import services.SendEmailService;

import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Scanner;



public class OrderSubmitPanel {

    private static final Scanner SCANNER = new Scanner(System.in);

    private final Order order;
    private final OrderDetails orderDetails;

    public static OrderSubmitPanel getOrderSubmitPanel(){
        return new OrderSubmitPanel();
    }

    private OrderSubmitPanel() {
        this.order = new Order();
        this.orderDetails = new OrderDetails();
    }

    public void submitOrder(Session session) {
        session.beginTransaction();

        System.out.println("SUBMIT YOUR ORDER ");
        System.out.println("ENTER YOUR ID NUMBER AND PRESS ENTER");
        int customerId = SCANNER.nextInt();
            System.out.println("WELCOME " + getCustomerByIdd(customerId,session).getCustomerName());
           try {
               placeAnOrder(customerId, session);
           }catch (NoResultException e){
               e.printStackTrace();
               System.out.println("PLEASE REGISTER, YOU ARE NOT IN OUR DATABASE");
           }

        session.getTransaction().commit();
    }

    private OrderDetails placeOrderDetailsToYourOrder(Session session){

        System.out.println("WRITE PRODUCT ID OF THE PRODUCT THAT YOU WANT TO ORDER");
        int productId = SCANNER.nextInt();
        orderDetails.setProductID(productId);
        orderDetails.setProductName(getProductNameById(productId,session));
        System.out.println("HOW MANY PIECES YOU WANT TO ORDER?");
        int quantity = SCANNER.nextInt();
        orderDetails.setQuantityOrdered(quantity);
        orderDetails.setGrossSellingPrice(getGrossSellingPriceById(productId,session));
        if (lowerStockQuantityOfProduct(productId, quantity, session)){
            orderDetails.setTotalAmount(countTotalAmount(productId, quantity,session));
            System.out.println("TOTAL AMOUNT IS : " + countTotalAmount(productId, quantity,session));
        }

        session.save(orderDetails);

        return orderDetails;
    }

    protected void placeAnOrder(int customerId,Session session){

        order.setCustomer(getCustomerByIdd(customerId,session));
        order.setOrderDate(LocalDateTime.now().toLocalDate());
        order.setStatus(OrderStatus.ACCEPTED_NOT_PAID);
        order.setShippedDate(LocalDateTime.now().plusDays(2L).toLocalDate());
        order.setOrderDetails(placeOrderDetailsToYourOrder(session));
        var customerEmailForOrderConfirmation = getCustomerEmail(customerId,session);
        SendEmailService emailService = new SendEmailService();
        String message = getOrderDescription();
        emailService.sendEmailWithOrderConfirmation(message,customerEmailForOrderConfirmation);

        session.save(order);
    }

    private boolean lowerStockQuantityOfProduct(int productId, int quantityOrdered,Session session){


        var originalQuantity = getProductQuantityById(productId, session);
        int finalQuantity = originalQuantity - quantityOrdered;

        if (quantityOrdered <= originalQuantity){
            var query = session.createQuery("UPDATE Product SET quantity =" + finalQuantity + " WHERE productID="+productId);
            query.executeUpdate();

            return true;
        }else {
            System.out.println("THERE IS NO SUCH QUANTITY FOR THIS PRODUCT." +
                    "WE HAVE ONLY " + originalQuantity + " PIECES");

            return false;
        }
    }

    private BigDecimal countTotalAmount(int productId, int quantityOrdered, Session session){

        BigDecimal price = getGrossSellingPriceById(productId, session);
        return price.multiply(BigDecimal.valueOf(quantityOrdered));

    }

    private Customer getCustomerByIdd(int id ,Session session) {

        var query = session.createQuery("FROM Customer WHERE customerID=" + id);
        Customer customer = (Customer) Optional.of(query.getSingleResult()).get();

        session.save(customer);

        return customer;
    }

    private String getCustomerEmail(int customerId, Session session){
        var query = session.createQuery("SELECT email FROM Customer WHERE customerID=" + customerId);
        var emailOptional= Optional.ofNullable(query.getSingleResult());
        return (String) emailOptional.get();
    }

    public String getOrderDescription(){
        return  "THANK YOU " + order.getCustomer().getCustomerName() + " FOR YOUR ORDER NR : " + order.getOrderID() + '\n' +
                    "YOU ORDERED " + orderDetails.getQuantityOrdered() + " PIECES OF  " + orderDetails.getProductName() + '\n' +
                    "FOR TOTAL COST : " + orderDetails.getTotalAmount().toString() + '\n' +
                    "YOUR ORDER WILL BE SHIPPED " + order.getShippedDate().toString();

    }

}

