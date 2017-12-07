package gui.controllers;

import businesslogic.Main;
import businesslogic.orderfactory.ManagerOrder;
import businesslogic.orderfactory.ProductInStock;
import gui.facade.Facade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProviderWindowController {
    private Facade f = Main.facade;
    private String log;

    @FXML Text welcomeText;

    @FXML TableView<ManagerOrder> managerTableView;
    @FXML TableColumn <ManagerOrder, Long> managerOrderIdColumn;
    @FXML TableColumn <ManagerOrder, Long> userIdColumn;
    @FXML TableColumn <ManagerOrder, Integer> amountColumn;
    @FXML TableColumn <ManagerOrder, Byte> statusPColumn;
    @FXML TableColumn <ManagerOrder, Byte> statusFullPayColumn;
    @FXML TextField managerOrderIdField;
    @FXML TextField userIdField;
    @FXML TextField amountField;
    @FXML TextField statusPField;
    @FXML TextField statusFullPayField;


    @FXML private TableView<ProductInStock> productTableView;
    @FXML private TableColumn<ProductInStock, Long> productIdColumn;
    @FXML private TableColumn<ProductInStock, String> productNameColumn;
    @FXML private TableColumn<ProductInStock, Integer> productCountColumn;
    @FXML private TableColumn<ProductInStock, Integer> productPriceColumn;

    @FXML private TextField productIdField;
    @FXML private TextField productNameField;
    @FXML private TextField productCountField;
    @FXML private TextField productPriceField;
    //@FXML private TextField productClientCountField;

    @FXML private TextField newProductNameField;
    @FXML private TextField newProductCountField;
    @FXML private TextField newProductPriceField;


    public void init(String login) throws Exception {
        log = login;
        welcomeText.setText("Welcome, " + log);
        initTable(log);
        managerTableView.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 0) {
                getSelectLineManager();
            }
        });
        productTableView.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 0) {
                getSelectLine();
            }
        });
    }

    private void initTable(String login) throws SQLException {
        log = login;

        long uid = f.getIdByLogin(login);
        ArrayList<ManagerOrder> managerOrderList = f.getTableManagerOrder();
        ObservableList<ManagerOrder> data = FXCollections.observableArrayList(managerOrderList);
        managerOrderIdColumn.setCellValueFactory( new PropertyValueFactory<ManagerOrder,Long>("ManagerOrderId") );
        userIdColumn.setCellValueFactory( new PropertyValueFactory<ManagerOrder,Long>("UserId") );
        amountColumn.setCellValueFactory( new PropertyValueFactory<ManagerOrder,Integer>("Amount") );
        statusPColumn.setCellValueFactory( new PropertyValueFactory<ManagerOrder,Byte>("StatusPOrder") );
        statusFullPayColumn.setCellValueFactory( new PropertyValueFactory<ManagerOrder,Byte>("StatusFullPayOrder") );
        managerTableView.setItems(data);

        ArrayList<ProductInStock> productInStockList = f.getTableProviderProductInStock();
        ObservableList<ProductInStock> datap = FXCollections.observableArrayList(productInStockList);
        productIdColumn.setCellValueFactory( new PropertyValueFactory<ProductInStock,Long>("productId") );
        productNameColumn.setCellValueFactory( new PropertyValueFactory<ProductInStock,String>("productName") );
        productCountColumn.setCellValueFactory( new PropertyValueFactory<ProductInStock,Integer>("productCount") );
        productPriceColumn.setCellValueFactory( new PropertyValueFactory<ProductInStock,Integer>("productPrice") );
        productTableView.setItems(datap);


    }

    private void getSelectLineManager() {
        if (managerTableView.getSelectionModel().getSelectedItem() != null) {
            ManagerOrder selectedItem = managerTableView.getSelectionModel().getSelectedItem();
            managerOrderIdField.setText(String.valueOf(selectedItem.getManagerOrderId()));
            userIdField.setText(String.valueOf(selectedItem.getUserId()));
            amountField.setText(String.valueOf(selectedItem.getAmount()));
            statusPField.setText(String.valueOf(selectedItem.getStatusPOrder()));
            statusFullPayField.setText(String.valueOf(selectedItem.getStatusFullPayOrder()));
        }
    }
    private void getSelectLine() {
        if (productTableView.getSelectionModel().getSelectedItem() != null) {
            ProductInStock selectedItem = productTableView.getSelectionModel().getSelectedItem();
            productIdField.setText(String.valueOf(selectedItem.getProductId()));
            productNameField.setText(selectedItem.getProductName());
            productCountField.setText(String.valueOf(selectedItem.getProductCount()));
            productPriceField.setText(String.valueOf(selectedItem.getProductPrice()));
        }
    }

    @FXML public void createNewProductClicked() throws Exception {
        long pid = f.createProductId();
        String name = newProductNameField.getText();
        int count = Integer.parseInt(newProductCountField.getText());
        int price = Integer.parseInt(newProductPriceField.getText());
        f.createNewProduct(pid, name, count, price);
        Main.showProviderWindow(log);

    }

    @FXML public void editClicked() throws Exception {
        long pid = Long.parseLong(productIdField.getText());
        String name = productNameField.getText();
        int count = Integer.parseInt(productCountField.getText());
        int price = Integer.parseInt(productPriceField.getText());

        f.editProductLine(pid, name, count, price);
        Main.showProviderWindow(log);
    }

    @FXML public void confirmationOrderClicked() throws Exception {
        byte st = f.confirmationManagerOrder(Long.parseLong(managerOrderIdField.getText()));
        Main.showProviderWindow(log);
    }
    @FXML private void backClicked() throws Exception {
        Main.showStart();
    }
}
