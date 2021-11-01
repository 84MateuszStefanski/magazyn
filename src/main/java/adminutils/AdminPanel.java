package adminutils;



public class AdminPanel {

    ProductRegistrationInterface registration;

    public void runAdminPanel() {

        AdminUtil adminUtil = new AdminUtil();



        System.out.println("WELCOME TO ADMIN PANEL");
        System.out.println("TO EXIT WRITE -exit- AND PRESS ENTER" + '\n');

        adminUtil.checkAdminLogData();

        System.out.println("REGISTER NEW PRODUCT");
        registration.createProduct();





    }


}
