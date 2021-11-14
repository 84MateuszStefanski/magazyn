package adminutils;


import org.hibernate.Session;

import java.util.Scanner;

public class AdminPanel {

    private static final Scanner SCANNER = new Scanner(System.in);
    private ProductRegistrationInterface registration;
    private ProductQuantityMagnifier quantityMagnifier;

    public void runAdminPanel(Session session) {

        AdminUtil adminUtil = new AdminUtil();
        String userChoice;

        do {
            adminUtil.checkAdminLogData();
            System.out.println("WELCOME TO ADMIN PANEL" + '\n');
            System.out.println("IF YOU WANT TO REGISTER PRODUCT WRITE 1 AND PRESS ENTER" + '\n');
            System.out.println("IF YOU WANT TO INCREASE PRODUCT QUANTITY WRITE 2 AND PRESS ENTER" + '\n');
            System.out.println("IF YOU WANT TO EXIT WRITE -exit- AND PRESS ENTER" + '\n');

           userChoice = SCANNER.nextLine();

            if (userChoice.trim().equals("1")) {
                registration = new ProductRegistration();
                registration.createProduct(session);
            }
            if (userChoice.trim().equals("2")){
                quantityMagnifier = new ProductQuantityMagnifier();
                quantityMagnifier.increaseProductQuantity(session);
            }
            if (!userChoice.trim().equals("1") && !userChoice.trim().equalsIgnoreCase("exit")){
                System.out.println("CHOOSE ONE OF THE OPTIONS" + '\n');
            }
            if (userChoice.trim().equalsIgnoreCase("exit")){
                System.out.println("BYE BYE ADMIN , SEE YOU NEXT TIME");
            }
        }while (!userChoice.trim().equals("1") && !userChoice.trim().equalsIgnoreCase("exit"));


    }



}
