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

import java.sql.SQLException;
import java.util.ArrayList;

public class CreateManagerOrderWindowController {
    private Facade f = Main.facade;
    private String log;

    @FXML TableView<ManagerOrder> managerTableView;
    @FXML TableColumn <ManagerOrder, Long> managerOrderIdColumn;
    @FXML TableColumn <ManagerOrder, Integer> amountColumn;
    @FXML TableColumn <ManagerOrder, Byte> statusPColumn;
    @FXML TableColumn <ManagerOrder, Byte> statusFullPayColumn;


    @FXML private TableView<ProductInStock> cproductTableView;
    @FXML private TableColumn<ProductInStock, Long> cproductIdColumn;
    @FXML private TableColumn<ProductInStock, String> cproductNameColumn;
    @FXML private TableColumn<ProductInStock, Integer> cproductCountColumn;
    @FXML private TableColumn<ProductInStock, Integer> cproductPriceColumn;
    @FXML private TextField cproductIdField;
    @FXML private TextField cproductNameField;
    @FXML private TextField cproductCountField;
    @FXML private TextField cproductPriceField;

    @FXML private TableView<ProductInStock> productTableView;
    @FXML private TableColumn<ProductInStock, Long> productIdColumn;
    @FXML private TableColumn<ProductInStock, String> productNameColumn;
    @FXML private TableColumn<ProductInStock, Integer> productCountColumn;
    @FXML private TableColumn<ProductInStock, Integer> productPriceColumn;
    @FXML private TextField productIdField;
    @FXML private TextField productNameField;
    @FXML private TextField productCountField;
    @FXML private TextField productPriceField;
    @FXML private TextField productManagerCountField;

    public void init(String login) throws SQLException {
        log = login;
        initTable(log);
        cproductTableView.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 0) {
                getSelectLinec();
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
        ArrayList<ManagerOrder> managerOrderList = f.getTableManagerOrder(uid);
        ObservableList<ManagerOrder> data = FXCollections.observableArrayList(managerOrderList);
        managerOrderIdColumn.setCellValueFactory( new PropertyValueFactory<ManagerOrder,Long>("ManagerOrderId") );
        amountColumn.setCellValueFactory( new PropertyValueFactory<ManagerOrder,Integer>("Amount") );
        statusPColumn.setCellValueFactory( new PropertyValueFactory<ManagerOrder,Byte>("StatusPOrder") );
        statusFullPayColumn.setCellValueFactory( new PropertyValueFactory<ManagerOrder,Byte>("StatusFullPayOrder") );
        managerTableView.setItems(data);

        ArrayList<ProductInStock> productInStockListc = f.getTableProductInStock();
        ObservableList<ProductInStock> datac = FXCollections.observableArrayList(productInStockListc);
        cproductIdColumn.setCellValueFactory( new PropertyValueFactory<ProductInStock,Long>("productId") );
        cproductNameColumn.setCellValueFactory( new PropertyValueFactory<ProductInStock,String>("productName") );
        cproductCountColumn.setCellValueFactory( new PropertyValueFactory<ProductInStock,Integer>("productCount") );
        cproductPriceColumn.setCellValueFactory( new PropertyValueFactory<ProductInStock,Integer>("productPrice") );
        cproductTableView.setItems(datac);

        ArrayList<ProductInStock> productInStockListp = f.getTableProviderProductInStock();
        ObservableList<ProductInStock> datap = FXCollections.observableArrayList(productInStockListp);
        productIdColumn.setCellValueFactory( new PropertyValueFactory<ProductInStock,Long>("productId") );
        productNameColumn.setCellValueFactory( new PropertyValueFactory<ProductInStock,String>("productName") );
        productCountColumn.setCellValueFactory( new PropertyValueFactory<ProductInStock,Integer>("productCount") );
        productPriceColumn.setCellValueFactory( new PropertyValueFactory<ProductInStock,Integer>("productPrice") );
        productTableView.setItems(datap);

    }

    private void getSelectLinec() {
        if (cproductTableView.getSelectionModel().getSelectedItem() != null) {
            ProductInStock selectedItem = cproductTableView.getSelectionModel().getSelectedItem();
            cproductIdField.setText(String.valueOf(selectedItem.getProductId()));
            cproductNameField.setText(selectedItem.getProductName());
            cproductCountField.setText(String.valueOf(selectedItem.getProductCount()));
            cproductPriceField.setText(String.valueOf(selectedItem.getProductPrice()));
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

    @FXML public void editProductClicked() throws Exception {
        long pid = Long.parseLong(cproductIdField.getText());
        String name = cproductNameField.getText();
        int count = Integer.parseInt(cproductCountField.getText());
        int price = Integer.parseInt(cproductPriceField.getText());

        f.editProductLineClient(pid, name, count, price);
        Main.showCreateManagerOrderWindow(log);
    }

    @FXML private void createNewOrderClicked() throws Exception {
        long manoid = f.createManagerOrderId();
        long moid = f.createMOrderId();
        long uid = f.getClient(log).getUserId();
        int amount = Integer.parseInt(productManagerCountField.getText()) * Integer.parseInt(productPriceField.getText());
        Byte spo = 0;
        Byte sfpo = 0;
        long pid = Long.parseLong(productIdField.getText());
        int pmc = Integer.parseInt(productManagerCountField.getText());
        String pn = productNameField.getText();
        int pp = Integer.parseInt(productPriceField.getText());
        int st = f.addNewManagerOrder(manoid, uid, amount, spo, sfpo);
        int sto = f.addNewMOrder(moid, manoid, pid, pmc);
        int stp = f.changeProviderProduct(pid, pn, pmc,  pp);

        if (st != 0)
            Main.showErrorWindow(st);
        else
            Main.showCreateManagerOrderWindow(log);
    }

    @FXML private void backClicked() throws Exception {
        Main.showManagerWindow(log);
    }

}
