package gui.controllers;

import businesslogic.Main;
import gui.facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class StartWindowController {

    Facade f = Main.facade;

    @FXML TextField loginField;
    @FXML TextField passwordField;
    @FXML private Label errorLabel;

    @FXML private void loginClicked() throws Exception {

        if (f.authenticate(loginField.getText(), passwordField.getText())){
            int roleId = f.getRoleId(loginField.getText());
            if (roleId == 1) {
                Main.showClientWindow(loginField.getText());
            }
            if (roleId == 2) {
                Main.showManagerWindow(loginField.getText());
            }
            if (roleId == 3) {
                Main.showProviderWindow(loginField.getText());
            }

        } else {
            errorLabel.setText("Incorrect user or password");
        }

    }

    @FXML private void registrationClicked() throws IOException {
        Main.showRegistrationWindow();
    }

}
