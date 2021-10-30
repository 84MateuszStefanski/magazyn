package utils;

import java.util.Scanner;


class AdminUtil {

    private final String ADMIN_LOGIN = "admin";
    private final String ADMIN_PASSWORD = "password";
    private final String EXIT_COMMAND = "exit";
    private static final Scanner SCANNER = new Scanner(System.in);
    private String login;
    private String password;

    protected void checkAdminLogData(){
            checkAdminLogin();
            checkAdminPassword();
    }

    private void checkAdminLogin() {

        System.out.println("TO LOG IN TO ADMIN PANEL, WRITE LOGIN AND PASSWORD." + '\n');
        do {
            System.out.println("PLEASE WRITE LOGIN AND PRESS ENTER" + '\n');
            login = SCANNER.nextLine();
            if (!login.equals(ADMIN_LOGIN)) {
                System.out.println("ACCESS DENIED , WRONG LOGIN" + '\n');
            } else {
                System.out.println("LOGIN CORRECT");
            }
        } while (!login.equals(ADMIN_LOGIN) && !login.equals(EXIT_COMMAND));

    }

    private void checkAdminPassword() {

        do {
            System.out.println("PLEASE WRITE PASSWORD AND PRESS ENTER" + '\n');
            password = SCANNER.nextLine();
            if (!password.equals(ADMIN_PASSWORD)) {
                System.out.println("ACCESS DENIED , WRONG PASSWORD" + '\n');
            } else {
                System.out.println("PASSWORD CORRECT");
            }
        } while (!password.equals(ADMIN_PASSWORD) && !password.equals(EXIT_COMMAND));
    }

    protected boolean exitAdminPanel(String exit) {
        return exit.equals(EXIT_COMMAND);
    }


}
//todo sprawić żeby wychodziło z programu na exit