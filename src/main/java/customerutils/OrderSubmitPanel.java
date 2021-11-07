
package customerutils;


import entities.*;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Scanner;


public class OrderSubmitPanel {

    private static final Scanner SCANNER = new Scanner(System.in);

    private Order order;
    private OrderDetails orderDetails;

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
//        if (!CustomerSearchEngine.theCustomerIsInDatabase(customerId)){
//            System.out.println("PLEASE REGISTER, YOU ARE NOT IN OUR DATABASE");
//        }else {
            System.out.println("WELCOME " + getCustomerByIdd(customerId,session).getCustomerName());
            placeAnOrder(customerId, session);
//        }

        session.getTransaction().commit();

    }

    protected OrderDetails placeOrderDetailsToYourOrder(Session session){

        System.out.println("WRITE PRODUCT ID OF THE PRODUCT THAT YOU WANT TO ORDER");
        int productId = SCANNER.nextInt();
        orderDetails.setProductID(productId);
        System.out.println("HOW MANY PIECES YOU WANT TO ORDER?");
        int quantity = SCANNER.nextInt();
        orderDetails.setQuantityOrdered(quantity);
        orderDetails.setGrossSellingPrice(getGrossSellingPriceByProductIdd(productId,session));
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
        OrderDescription.getOrderDescription(order.getOrderID(),session);

        session.save(order);
    }

    private boolean lowerStockQuantityOfProduct(int productId, int quantityOrdered,Session session){


        var originalQuantity = returnProductQuantitySearchedByIdd(productId, session);
        int finalQuantity = originalQuantity - quantityOrdered;

        if (quantityOrdered <= originalQuantity){
            var query = session.createQuery("UPDATE Product SET quantity =" + finalQuantity + "WHERE productID="+productId);
            query.executeUpdate();

            return true;
        }else {
            System.out.println("THERE IS NO SUCH QUANTITY FOR THIS PRODUCT." +
                    "WE HAVE ONLY " + originalQuantity + " PIECES");

            return false;
        }
    }

    private BigDecimal countTotalAmount(int productId, int quantityOrdered, Session session){

        BigDecimal price = getGrossSellingPriceByProductIdd(productId, session);
        BigDecimal totalAmount = price.multiply(BigDecimal.valueOf(quantityOrdered));


        return totalAmount;
    }

    private Customer getCustomerByIdd(int id ,Session session) {

        var query = session.createQuery("FROM Customer WHERE customerID=" + id);
        Customer customer = (Customer) Optional.of(query.getSingleResult()).get();

        session.save(customer);

        return customer;
    }

    private int returnProductQuantitySearchedByIdd(int id, Session session){


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

    private BigDecimal getGrossSellingPriceByProductIdd(int id,Session session) throws NoResultException {
        var product = (Product) findProductByIdd(id,session).get();
        return product.getGrossSellingPrice();
    }

    private Optional<Object> findProductByIdd(int id, Session session) {

        var query = session.createQuery("FROM Product WHERE productID =" + id);
        var product = Optional.of(query.getSingleResult());
        if (product.isEmpty()){
            return Optional.empty();
        }

        return product;

    }
}

