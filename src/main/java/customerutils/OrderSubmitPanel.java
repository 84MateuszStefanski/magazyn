package customerutils;

import entities.Order;
import entities.OrderDetails;
import entities.OrderStatus;
import org.hibernate.Session;
import utils.HibernateUtil;

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
        //TODO
    }


}
