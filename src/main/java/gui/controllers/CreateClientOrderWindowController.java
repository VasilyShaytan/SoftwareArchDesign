package gui.controllers;

import businesslogic.Main;
import businesslogic.orderfactory.ProductInStock;
import gui.facade.Facade;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class CreateClientOrderWindowController {
    private Facade f = Main.facade;
    private String log;

    @FXML private TableView<ProductInStock> orderTableView;
    @FXML private TableColumn<ProductInStock, Integer> productIdColumn;
    @FXML private TableColumn<ProductInStock, String> productNameColumn;
    @FXML private TableColumn<ProductInStock, Integer> productCountColumn;
    @FXML private TableColumn<ProductInStock, Integer> productPriceColumn;

    @FXML private TextField productIdField;
    @FXML private TextField productNameField;
    @FXML private TextField productCountField;
    @FXML private TextField productPriceField;
    @FXML private TextField productClientCountField;

    public void init(String login) throws SQLException {
        log = login;
        initTable(log);
        orderTableView.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 0) {
                getSelectLine();
            }
        });

    }

    private void initTable(String login) throws SQLException {
        log = login;
        ArrayList<ProductInStock> productInStockList = f.getTableProductInStock();
        ObservableList<ProductInStock> data = FXCollections.observableArrayList(productInStockList);
        productIdColumn.setCellValueFactory( new PropertyValueFactory<ProductInStock,Integer>("productId") );
        productNameColumn.setCellValueFactory( new PropertyValueFactory<ProductInStock,String>("productName") );
        productCountColumn.setCellValueFactory( new PropertyValueFactory<ProductInStock,Integer>("productCount") );
        productPriceColumn.setCellValueFactory( new PropertyValueFactory<ProductInStock,Integer>("productPrice") );
        orderTableView.setItems(data);

    }

    private void getSelectLine() {
        if (orderTableView.getSelectionModel().getSelectedItem() != null) {
            ProductInStock selectedItem = orderTableView.getSelectionModel().getSelectedItem();
            productIdField.setText(String.valueOf(selectedItem.getProductId()));
            productNameField.setText(selectedItem.getProductName());
            productCountField.setText(String.valueOf(selectedItem.getProductCount()));
            productPriceField.setText(String.valueOf(selectedItem.getProductPrice()));
        }
    }

    @FXML private void createOrderClicked() throws Exception {
        long coid = f.createClientOrderId();
        long oid = f.createOrderId();
        long uid = f.getClient(log).getUserId();
        int amount = Integer.parseInt(productClientCountField.getText()) * Integer.parseInt(productPriceField.getText());
        Byte smo = 0;
        Byte sppo = 0;
        Byte sfpo = 0;
        long pid = Long.parseLong(productIdField.getText());
        int pc = Integer.parseInt(productClientCountField.getText());
        String pn = productNameField.getText();
        int pp = Integer.parseInt(productPriceField.getText());
        int st = f.addNewClientOrder(coid, uid, amount, smo, sppo, sfpo);
        int sto = f.addNewOrder(oid, coid, pid, pc);
        int stp = f.changeProduct(pid, pn, pc,  pp);


        if (st != 0)
            Main.showErrorWindow(st);
        else
            Main.showClientWindow(log);
    }
    @FXML private void backClicked() throws Exception {
        Main.showClientWindow(log);
    }
}
