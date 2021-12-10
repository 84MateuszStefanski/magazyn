package adminutils;

import java.util.Scanner;
import org.apache.commons.codec.digest.DigestUtils;

class AdminUtil {
    /**login = login , password = password*/
    private final String ADMIN_LOGIN = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918";
    private final String ADMIN_PASSWORD = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8";
    private final String EXIT_COMMAND = "exit";
    private static final Scanner SCANNER = new Scanner(System.in);
    private String login;
    private String password;


    protected void checkAdminLogData(){

            checkAdminLogin();
            checkAdminPassword();
    }

    private void checkAdminLogin() {

        System.out.println("TO LOG IN, WRITE LOGIN AND PASSWORD." + '\n');
        do {
            System.out.println("PLEASE WRITE LOGIN AND PRESS ENTER" + '\n');
            login = DigestUtils.sha256Hex(userChoice());
            if (!login.equals(ADMIN_LOGIN)) {
                System.out.println("ACCESS DENIED , WRONG LOGIN" + '\n');
            } else {
                System.out.println("LOGIN CORRECT");
            }
        } while (!login.equals(ADMIN_LOGIN));

    }

    private void checkAdminPassword() {

        do {
            System.out.println("PLEASE WRITE PASSWORD AND PRESS ENTER" + '\n');
            password = DigestUtils.sha256Hex(userChoice());
            if (!password.equals(ADMIN_PASSWORD)) {
                System.out.println("ACCESS DENIED , WRONG PASSWORD" + '\n');
            } else {
                System.out.println("PASSWORD CORRECT");
            }
        } while (!password.equals(ADMIN_PASSWORD));
    }

    private String userChoice(){
        return  SCANNER.nextLine();
    }


}
