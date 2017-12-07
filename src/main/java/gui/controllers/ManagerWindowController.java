package gui.controllers;

import businesslogic.Main;
import businesslogic.orderfactory.ClientOrder;
import gui.facade.Facade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManagerWindowController {
    private Facade f = Main.facade;
    private String log;

    @FXML TableView<ClientOrder> managerTableView;
    @FXML TableColumn <ClientOrder, Long> clientOrderIdColumn;
    @FXML TableColumn <ClientOrder, Long> userIdColumn;
    @FXML TableColumn <ClientOrder, Integer> amountColumn;
    @FXML TableColumn <ClientOrder, Byte> statusMColumn;
    @FXML TableColumn <ClientOrder, Byte> statusPPayColumn;
    @FXML TableColumn <ClientOrder, Byte> statusFullPayColumn;

    @FXML TextField clientOrderIdField;
    @FXML TextField userIdField;
    @FXML TextField amountField;
    @FXML TextField statusMField;
    @FXML TextField statusPPayField;
    @FXML TextField statusFullPayField;

    @FXML Button confirmationOrderButton;
    @FXML Text welcomeText;




    public void init(String login) throws Exception {
        log = login;
        welcomeText.setText("Welcome, " + log);
        initTable(log);
        managerTableView.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 0) {
                getSelectLine();
            }
        });
    }
    private void initTable(String login) throws SQLException {
        log = login;
        ArrayList<ClientOrder> clientOrderList = f.getTableClientOrder();
        ObservableList<ClientOrder> data = FXCollections.observableArrayList(clientOrderList);
        clientOrderIdColumn.setCellValueFactory( new PropertyValueFactory<ClientOrder,Long>("ClientOrderId") );
        userIdColumn.setCellValueFactory( new PropertyValueFactory<ClientOrder,Long>("UserId") );
        amountColumn.setCellValueFactory( new PropertyValueFactory<ClientOrder,Integer>("Amount") );
        statusMColumn.setCellValueFactory( new PropertyValueFactory<ClientOrder,Byte>("StatusMOrder") );
        statusPPayColumn.setCellValueFactory( new PropertyValueFactory<ClientOrder,Byte>("StatusPPayOrder") );
        statusFullPayColumn.setCellValueFactory( new PropertyValueFactory<ClientOrder,Byte>("StatusFullPayOrder") );
        managerTableView.setItems(data);

    }

    private void getSelectLine() {
        if (managerTableView.getSelectionModel().getSelectedItem() != null) {
            ClientOrder selectedItem = managerTableView.getSelectionModel().getSelectedItem();

            clientOrderIdField.setText(String.valueOf(selectedItem.getClientOrderId()));
            userIdField.setText(String.valueOf(selectedItem.getUserId()));
            amountField.setText(String.valueOf(selectedItem.getAmount()));
            statusMField.setText(String.valueOf(selectedItem.getStatusMOrder()));
            statusPPayField.setText(String.valueOf(selectedItem.getStatusPPayOrder()));
            statusFullPayField.setText(String.valueOf(selectedItem.getStatusFullPayOrder()));
        }
    }

    @FXML private void confirmationOrderClicked() throws Exception {
        byte st = f.confirmationOrder(Long.parseLong(clientOrderIdField.getText()));
        Main.showManagerWindow(log);
    }

    @FXML private void managerOrderClicked() throws Exception {
        Main.showCreateManagerOrderWindow(log);
    }
    @FXML private void backClicked() throws Exception {
        Main.showStart();
    }



}
