package front;

import adminutils.AdminPanel;
import customerutils.CustomerRegistration;
import customerutils.CustomerRegistrationInterface;
import customerutils.OrderSubmitPanel;

import java.util.Scanner;

public class FirstView implements Runnable {

    @Override
    public void run(){

        final Scanner SCANNER = new Scanner(System.in);

        System.out.println();
        System.out.println("#####################################################################");
        System.out.println("#                    WELCOME, THIS IS ORDERING PROGRAM              #");
        System.out.println("#####################################################################");
        System.out.println();

        String userChoice;
        do {
            System.out.println();
            System.out.println("Choose what you want to do, then press enter:");
            System.out.println("[1] Add new customer");
            System.out.println("[2] Submit your order");
            System.out.println("[3] Enter admin panel");
            System.out.println("[x] End");
            userChoice = SCANNER.nextLine();
            if (userChoice.trim().equals("1")) {
                System.out.println("Register new customer " + '\n');
                CustomerRegistrationInterface registrationInterface = new CustomerRegistration();
                registrationInterface.registerCustomer();
            } else if (userChoice.trim().equals("2")) {
                System.out.println("Submit your order " + '\n');
                OrderSubmitPanel submitPanel = new OrderSubmitPanel();
                submitPanel.submitOrder();
            } else if (userChoice.trim().equals("3")) {
                System.out.println("Enter admin panel ");
                AdminPanel adminPanel = new AdminPanel();
                adminPanel.runAdminPanel();
            } else if (userChoice.trim().equalsIgnoreCase("x")) {
                System.out.println("###############################################################################");
                System.out.println("#                    Thank You for using my program :)                        #");
                System.out.println("###############################################################################");
            } else {
                System.out.println("Make your choice again");
            }
        } while (!userChoice.trim().equalsIgnoreCase("x"));

    }
}
