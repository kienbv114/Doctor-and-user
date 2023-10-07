package controller;

import common.Algorithm;
import common.Validation;
import model.UserModel;
import view.Menu;

public class UserController {

    private final String[] MAIN_MENU_ITEMS = {
        " Create a new account",
        " Login System",
        " Exit",};

    Algorithm algorithm = new Algorithm();
    Validation validation = new Validation();

    Menu mainMenu = new Menu("====== USER MANAGEMENT SYSTEM ======", MAIN_MENU_ITEMS) {
        @Override
        public void execute(int choice) {
            switch (choice) {
                case 1:
                    algorithm.createNewAccount();
                    break;
                case 2:
                    loginSystem();
                    break;
                case 3:
                    System.exit(0);
                    break;

            }
        }

        public void loginSystem() {
            if (!validation.checkFileExist()) {
                return;
            }
            UserModel user = new UserModel();
            user.setUserName(validation.checkInputUsername());
            user.setPassWord(validation.checkInputPassword());
            if (!validation.checkUsernameExist(user.getUserName())) {
                if (!user.getPassWord().equalsIgnoreCase(algorithm.passwordByUsername(user.getUserName()))) {
                    System.err.println("Password incorrect.");
                } else {
                    System.err.println("Login success.");
                }
            } else {
                System.err.println("Username incorrect.");
            }
        }
    };

    public void run() {
        mainMenu.run();
    }
}
