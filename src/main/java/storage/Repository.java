package storage;

import businesslogic.clientfactory.Client;
import businesslogic.clientfactory.ClientFactory;
import businesslogic.orderfactory.*;
import storage.mappers.createid.CreateIdMapper;
import storage.mappers.order.ClientOrderMapper;
import storage.mappers.order.ManagerOrderMapper;
import storage.mappers.order.ProductInStockMapper;
import storage.mappers.user.ClientMapper;
import storage.mappers.user.CommonMapper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Repository {
    private static ClientMapper clientMapper;
    private static ClientOrderMapper clientOrderMapper;
    private static ProductInStockMapper productInStockMapper;
    private static CreateIdMapper createIdMapper;
    private static CommonMapper commonMapper;
    private static ManagerOrderMapper managerOrderMapper;

    public Repository() {
        try {
            if (clientMapper == null) clientMapper = new ClientMapper();
            if (clientOrderMapper == null) clientOrderMapper = new ClientOrderMapper();
            if (productInStockMapper == null) productInStockMapper = new ProductInStockMapper();
            if (createIdMapper == null) createIdMapper = new CreateIdMapper();
            if (commonMapper == null) commonMapper = new CommonMapper();
            if (managerOrderMapper == null) managerOrderMapper = new ManagerOrderMapper();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public int createUserId() throws SQLException {
        return createIdMapper.createUserId();
    }
    public int createClientOrderId() throws SQLException {
        return createIdMapper.createClientOrderId();
    }
    public int createProductId() throws SQLException {
        return createIdMapper.createProductId();
    }
    public int createOrderId() throws SQLException {
        return createIdMapper.createOrderId();
    }
    public long createManagerOrderId() throws SQLException {
        return createIdMapper.createManagerOrderId();
    }
    public long createMOrderId() throws SQLException {
        return createIdMapper.createMOrderId();
    }

    public Client getClient(String login) throws SQLException {
        return clientMapper.findUserByLogin(login);
    }

    public boolean addNewClient(long id, String login, String password, String name, String surname, int roleId, int money) throws SQLException{
        Client u = ClientFactory.createClient(id, login, password, name, surname, roleId, money);
        try {
            clientMapper.addNewClient(u);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public ArrayList<ClientOrder> getClientOrders(String login) throws SQLException, IOException {
        return clientOrderMapper.getClientOrders(login);
    }

    public ArrayList<ProductInStock> getTableProductInStock() throws SQLException {
        return productInStockMapper.getTableProductInStock();
    }
    public ArrayList<ProductInStock> getTableProviderProductInStock() throws SQLException {
        return productInStockMapper.getTableProviderProductInStock();
    }

    public boolean addNewClientOrder(long cid, long uid, int amount, byte smo, byte sppo, byte sfpo) throws SQLException{
        ClientOrder co = ClientOrderFactory.createClientOrder(cid, uid, amount, smo, sppo, sfpo);
        try {
            clientOrderMapper.addNewClientOrder(co);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean addNewOrder(long oid, long coid, long pid, int pc ) throws SQLException{
        Order o = OrderFactory.createOrder(oid, coid, pid, pc);
        try {
            clientOrderMapper.addNewOrder(o);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean changeProduct(long pid, String pn, int pc, int pp) throws SQLException{
        ProductInStock pis = ProductInStockFactory.createProductInStock(pid, pn, pc, pp);
        try {
            productInStockMapper.editProductInStock(pis);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public int getRoleId(String login) throws SQLException {
        return commonMapper.getRoleId(login);
    }
    public ArrayList<ClientOrder> getTableClientOrder() throws SQLException {
        return clientOrderMapper.getTableClientOrder();
    }

    public byte confirmationOrder(long clientOrderId) throws SQLException {
        return clientOrderMapper.confirmationOrder(clientOrderId);
    }

    public void createNewProduct(long pid, String name, int count, int price) throws Exception{
        productInStockMapper.createNewProduct(pid, name, count, price);
    }
    public void editProductLine(long pid, String name, int count, int price) throws SQLException {
        productInStockMapper.editProductLine(pid, name, count, price);
    }

    public ArrayList<ManagerOrder> getTableManagerOrder(long uid) throws SQLException {
        return managerOrderMapper.getTableManagerOrder(uid);
    }
    public ArrayList<ManagerOrder> getTableManagerOrder() throws SQLException {
        return managerOrderMapper.getTableManagerOrder();
    }
    public long getIdByLogin(String login) throws SQLException {
        return commonMapper.getIdByLogin(login);
    }

    public boolean addNewManagerOrder(long manoid, long uid, int amount, byte spo, byte sfpo) throws SQLException{
        ManagerOrder mo = ManagerOrderFactory.createManagerOrder(manoid, uid, amount, spo, sfpo);
        try {
            managerOrderMapper.addNewManagerOrder(mo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean addNewMOrder(long moid, long manoid, long pid, int pmc) throws SQLException{
        Order o = OrderFactory.createOrder(moid, manoid, pid, pmc);
        try {
            managerOrderMapper.addNewMOrder(o);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean changeProviderProduct(long pid, String pn, int pmc, int pp) throws SQLException{
        ProductInStock pis = ProductInStockFactory.createProductInStock(pid, pn, pmc, pp);
        try {
            productInStockMapper.editProviderProductInStock(pis);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void editProductLineClient(long pid, String name, int count, int price) throws SQLException {
        productInStockMapper.editProductLineClient(pid, name, count, price);
    }

    public byte confirmationManagerOrder(long managerOrderId) throws SQLException {
        return managerOrderMapper.confirmationManagerOrder(managerOrderId);
    }
    public int getClientMoney(long uid) throws SQLException {
        return commonMapper.getClientMoney(uid);
    }
    public void topUpClientMoney(long uid, int money) throws SQLException {
        clientMapper.topUpClientMoney(uid, money);
    }
    public int getStatusMOrder(long coid) throws SQLException {
        return clientOrderMapper.getStatusMOrder(coid);
    }
    public int getStatusFullPayOrder(long coid) throws SQLException {
        return clientOrderMapper.getStatusFullPayOrder(coid);
    }
    public int getAmountOrder(long coid) throws SQLException {
        return clientOrderMapper.getAmountOrder(coid);
    }
    public void changeClientBalance(long uid, int amount) throws SQLException {
        clientMapper.changeClientBalance(uid, amount);
    }
    public int setStatusFullPayOrder(long coid) throws SQLException {
        return clientOrderMapper.setStatusFullPayOrder(coid);
    }
}
