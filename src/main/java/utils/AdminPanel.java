package utils;

import java.util.Scanner;

public class AdminPanel {

    public static void runAdminPanel() {
        ProductRegistration registration = new ProductRegistration();
        AdminUtil adminUtil = new AdminUtil();



        System.out.println("WELCOME TO ADMIN PANEL");
        System.out.println("TO EXIT WRITE -exit- AND PRESS ENTER" + '\n');

        adminUtil.checkAdminLogData();




    }
}
