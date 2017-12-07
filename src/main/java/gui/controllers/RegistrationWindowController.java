package gui.controllers;

import businesslogic.Main;
import gui.facade.Facade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.Date;

public class RegistrationWindowController {
    Facade f = Main.facade;
    private ObservableList<String> choiceBoxRegList = FXCollections.observableArrayList("Client", "Manager", "Provider");

    @FXML private TextField loginField;
    @FXML private TextField passwordField;
    @FXML private TextField nameField;
    @FXML private TextField surnameField;
    @FXML private ChoiceBox<String> roleChoice;

    @FXML private Text error;

    public void init(){
        //this.login = login;
        roleChoice.setItems(choiceBoxRegList);
        roleChoice.setValue("Client");
    }

    public void createClicked() throws Exception {
        String login = loginField.getText();
        String password = passwordField.getText();
        String name = nameField.getText();
        String surname = surnameField.getText();
        String role = roleChoice.getValue();
        int roleInt = roleStringToInt(role);
        int money = 0;
        long id = f.createUserId();

        int st = f.addNewClient(id, login, password, name, surname, roleInt, money);

        if (st != 0)
            Main.showErrorWindow(st);
        else {
            int roleId = f.getRoleId(login);
            if (roleId == 1) {
                Main.showClientWindow(login);
            }
            if (roleId == 2) {
                Main.showManagerWindow(login);
            }
            if (roleId == 3) {
                Main.showProviderWindow(login);
            }
        }


    }

    private int roleStringToInt(String role){
        //role = String.valueOf(role);
        switch (role){
            case "Client":
                return 1;
            case "Manager":
                return 2;
            case "Provider":
                return 3;
            default:
                return 9;
        }

    }
    @FXML private void backClicked() throws Exception {
        Main.showStart();
    }
}
