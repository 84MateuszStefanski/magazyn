package daoservices;

import entities.Order;
import entities.OrderDetails;
import entities.Product;
import org.hibernate.Session;

import java.util.Optional;

public class OrderDescription {

    private final int orderId;
    private final String customerName;
    private final String quantity;
    private final int productId;
    private final String productName;
    private final String totalPrice;
    private final String shippedDate;

    public static OrderDescription getOrderDescription(int orderId, Session session){
        return new OrderDescription(orderId,session);
    }

    private OrderDescription(int orderId, Session session) {
        this.orderId = orderId;
        this.customerName = getOrder(orderId, session).getCustomer().getCustomerName();
        this.quantity = String.valueOf(getOrderDetails(orderId, session).getQuantityOrdered());
        this.productId = setProductId(orderId,session);
        this.productName = setProductName(session);
        this.totalPrice = String.valueOf(getOrderDetails(orderId, session).getTotalAmount());
        this.shippedDate = getOrder(orderId, session).getShippedDate().toString();
    }

    private Order getOrder(int orderId, Session session){
        session.beginTransaction();

        var query = session.createQuery("FROM Order WHERE orderID=" + orderId);
        Optional<Object> orderOptional = Optional.ofNullable(query.getSingleResult());
        Order order = (Order) orderOptional.get();
            session.getTransaction().commit();
            return order;
        }

    private OrderDetails getOrderDetails(int orderId, Session session){
        session.beginTransaction();

        var query = session.createQuery("FROM OrderDetails WHERE orderID=" + orderId);
        Optional<Object> orderDetailsOptional = Optional.ofNullable(query.getSingleResult());
        OrderDetails orderDetails = (OrderDetails) orderDetailsOptional.get();
            session.getTransaction().commit();
            return orderDetails;
    }

    private String setProductName(Session session){
        session.beginTransaction();

        var query = session.createQuery("FROM Product WHERE productID=" + this.productId);
        Optional<Object> productOptional = Optional.ofNullable(query.getSingleResult());
        Product product = (Product)productOptional.get();
            var productNameV = product.getProductName();
             session.getTransaction().commit();
             return productNameV;
    }

    private int setProductId(int orderId, Session session){
        session.beginTransaction();
        var query1 = session.createQuery("productID FROM OrderDetails WHERE orderID=" + orderId);
        Optional<Object> productIdOptional = Optional.ofNullable(query1.getSingleResult());
            var productIdV = (int) productIdOptional.get();
        session.getTransaction().commit();
        return productIdV;
    }

    @Override
    public String toString() {
        return "THANK YOU " + customerName + " FOR YOUR ORDER NR : " + orderId + '\n' +
                "YOU ORDERED " + quantity + " PIECES OF + " + productName + '\n' +
                "FOR TOTAL COST : " + totalPrice + '\n' +
                "YOUR ORDER WILL BE SHIPPED " + shippedDate;
    }
}
