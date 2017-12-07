package gui.controllers;

import businesslogic.Main;
import gui.facade.Facade;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientWindowController {
    private Facade f = Main.facade;
    private String log;

    @FXML private Text welcomeText;
    @FXML private ListView orderView;
    @FXML private Text orderDesc;
    @FXML private TextField balanceField;
    @FXML private TextField newBalanceField;

    @FXML private Button payButton;


    public void init(String login) throws Exception {
        log = login;
        welcomeText.setText("Welcome, " + log);
        long uid = f.getIdByLogin(log);
        int money = f.getClientMoney(uid);
        balanceField.setText(String.valueOf(money));
        ArrayList<String> olv = f.getClientOrdersList(login);
        ObservableList<String> items = FXCollections.observableArrayList(olv);
        if(items.size() != 0){
            orderView.setItems(items);
            orderView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    ArrayList<String> orderDescriptions = null;
                    try {
                        orderDescriptions = f.getOrderDescription(newValue);
                        int statusMOrder = f.getStatusMOrder(Integer.parseInt(newValue));
                        int statusFullPayOrder = f.getStatusFullPayOrder(Integer.parseInt(newValue));
                        if (statusMOrder == 1 && statusFullPayOrder == 0) {
                            payButton.setDisable(false);
                        }
                        else {
                            payButton.setDisable(true);
                        }
                        orderDesc.setText("");
                        orderDesc.setWrappingWidth(250);
                        orderDesc.setText(orderDesc.getText() + orderDescriptions.get(0) + "\n");
                        for (int i = 1; i < orderDescriptions.size(); i++){
                            if(i > 5)
                                orderDesc.setText(orderDesc.getText() + (i - 5) + ". " +
                                        orderDescriptions.get(i) + "\n\n");
                            else
                                orderDesc.setText(orderDesc.getText() + orderDescriptions.get(i) + "\n");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }


    @FXML private void createOrderClicked() throws Exception {
        Main.showCreateClientOrderWindow(log);
    }

    @FXML private void topUpClicked() throws Exception {
        long uid = f.getIdByLogin(log);
        f.topUpClientMoney(uid, Integer.parseInt(newBalanceField.getText()));
        Main.showClientWindow(log);
    }

    @FXML private void backClicked() throws IOException {
        Main.showStart();
    }

    @FXML private void payClicked() throws Exception {
        long uid = f.getIdByLogin(log);
        long coid = Long.parseLong(orderView.getSelectionModel().getSelectedItems().get(0).toString());
        int amount = f.getAmountOrder(coid);
        f.changeClientBalance(uid, amount);
        f.setStatusFullPayOrder(coid);
        Main.showClientWindow(log);
    }


}
