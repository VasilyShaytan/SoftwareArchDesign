package gui.controllers;

import businesslogic.ErrorCodes;
import businesslogic.Main;
import gui.facade.Facade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ErrorWindowController {
    Facade f = Main.facade;
    Stage st;
    @FXML
    private TextField errorField;

    public void init(int errorCode, Stage stage){
        st = stage;
        errorField.setText(ErrorCodes.getError(errorCode));
    }

    public void okClicked(ActionEvent actionEvent) {
        st.close();
    }
}
