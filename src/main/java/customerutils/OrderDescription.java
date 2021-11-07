package customerutils;

import entities.Customer;
import entities.Order;
import entities.OrderDetails;
import entities.Product;
import org.hibernate.Session;

import java.util.Optional;

class OrderDescription {

    protected static String getOrderDescription(int orderId, Session session){

        OrderDetails orderDetails = (OrderDetails) getOrderDetails(orderId, session).get();
        Customer customer = getCustomerFromOrder(orderId,session);
        Product product = (Product) getProductFromOrder(orderId,session).get();

        String orderDescription = "THANK YOU " + customer.getCustomerName() + " FOR ORDERING " +
                orderDetails.getQuantityOrdered() + " OF " + product.getProductName() +
                " FOR TOTAL AMOUNT " + orderDetails.getTotalAmount() ;


        return orderDescription;
    }

    private static Optional<Object> getOrder(int orderId, Session session){
        session.beginTransaction();

        var query = session.createQuery("FROM Order WHERE orderID=" + orderId);
        var order = Optional.of(query.getSingleResult());
        if (order.isEmpty()){
            return Optional.empty();
        }else {
            session.getTransaction().commit();
            return order;
        }
    }

    private static Optional<Object> getOrderDetails(int orderId, Session session){
        session.beginTransaction();

        var query = session.createQuery("FROM OrderDetails WHERE orderID=" + orderId);
        var orderDetails = Optional.of(query.getSingleResult());
        if (orderDetails.isEmpty()){
            return Optional.empty();
        }else {
            session.getTransaction().commit();
            return orderDetails;
        }
    }

    private static Customer getCustomerFromOrder(int orderId, Session session){
        Order order = (Order) getOrder(orderId, session).get();
        return order.getCustomer();
    }

    private static Optional<Object> getProductFromOrder(int orderId, Session session){
        session.beginTransaction();
        var query1 = session.createQuery("productID FROM OrderDetails WHERE orderID=" + orderId);
        int productId =(int)query1.getSingleResult();
        var query2 = session.createQuery("FROM Product WHERE productID=" + productId);
        var product = Optional.of(query2.getSingleResult());
        if (product.isEmpty()){
            return Optional.empty();
        }else {
            session.getTransaction().commit();
            return product;
        }
    }
//TODO Exception in thread "main" java.lang.NullPointerException
//	at customerutils.OrderSubmitPanel.placeAnOrder(OrderSubmitPanel.java:74)
//	at customerutils.OrderSubmitPanel.submitOrder(OrderSubmitPanel.java:41)
}
