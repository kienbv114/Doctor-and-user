package common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import model.UserModel;

public class Algorithm {

    Validation validation = new Validation();
    AddressFile addressFile = new AddressFile();

    public void createNewAccount() {
        do {
            if (!validation.checkFileExist()) {
                return;
            }
            UserModel user = new UserModel();
            user.setUserName(validation.checkInputUsername());
            user.setPassWord(validation.checkInputPassword());
            if (!validation.checkUsernameExist(user.getUserName())) {
                System.err.println("Username exists.");
                return;
            }
            addAccountData(user);
        } while (validation.checkInputYN());
    }

    public void addAccountData(UserModel user) {
        try {
            FileWriter fileWriter = new FileWriter(addressFile.file(), true);
            fileWriter.write(user.getUserName() + ";" + user.getPassWord() + "\n");
            fileWriter.close();
            System.err.println("Create successfully.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String passwordByUsername(String username) {
        try {
            FileReader fileReader = new FileReader(addressFile.file());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] account = line.split(";");
                if (username.equalsIgnoreCase(account[0])) {
                    return account[1];
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
