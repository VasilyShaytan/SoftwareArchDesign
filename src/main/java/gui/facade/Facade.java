package gui.facade;

import businesslogic.ErrorCodes;
import businesslogic.clientfactory.Client;
import businesslogic.orderfactory.ClientOrder;
import businesslogic.orderfactory.ManagerOrder;
import businesslogic.orderfactory.ProductInStock;
import storage.Repository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Facade {
    private Repository rep;
    private ArrayList<ClientOrder> clientOrders = new ArrayList<ClientOrder>();

    public Facade(){
        rep = new Repository();
    }

    public int createUserId() throws SQLException {
        return rep.createUserId();
    }
    public int createClientOrderId() throws SQLException {
        return rep.createClientOrderId();
    }
    public int createProductId() throws SQLException {
        return rep.createProductId();
    }
    public int createOrderId() throws SQLException {
        return rep.createOrderId();
    }
    public long createManagerOrderId() throws SQLException {
        return rep.createManagerOrderId();
    }
    public long createMOrderId() throws SQLException {
        return rep.createMOrderId();
    }

    public Client getClient(String login) throws SQLException {
        return rep.getClient(login);
    }
    public boolean authenticate(String login, String password) throws Exception{
        if (rep.getClient(login) != null)
            return true;
        else
            return false;
    }

    public int addNewClient(long id, String login, String password, String name, String surname, int roleId, int money) throws Exception{

        try {
            rep.addNewClient(id, login, password, name, surname, roleId, money);
            return 0;
        } catch (NumberFormatException e){
            return ErrorCodes.busyLogin;
        }

    }

    public ArrayList<String> getClientOrdersList(String login) throws SQLException, IOException {
        ArrayList<String> result = new ArrayList<String>();
        clientOrders = rep.getClientOrders(login);
        for (ClientOrder co : clientOrders){
            result.add(String.valueOf(co.getClientOrderId()));
        }
        return result;
    }

    public ArrayList<String> getOrderDescription(String clientOrderId) throws Exception {
        ArrayList<String> result = new ArrayList<String>();
        for (ClientOrder co : clientOrders){
            if (co.getClientOrderId() == Long.parseLong(clientOrderId)) {
                result.add("Amount: " + String.valueOf(co.getAmount()));
                result.add("Status confirmation order: " + String.valueOf(co.getStatusMOrder()));
                result.add("Status pre pay order: " + String.valueOf(co.getStatusPPayOrder()));
                result.add("Status full pay order: " + String.valueOf(co.getStatusFullPayOrder()));
            }
        }
        return result;
    }

    public ArrayList<ProductInStock> getTableProductInStock() throws SQLException {
        return rep.getTableProductInStock();
    }
    public ArrayList<ProductInStock> getTableProviderProductInStock() throws SQLException {
        return rep.getTableProviderProductInStock();
    }

    public int addNewClientOrder(long cid, long uid, int amount, byte smo, byte sppo, byte sfpo) throws SQLException {
        try {
            rep.addNewClientOrder(cid, uid, amount, smo, sppo, sfpo);
            return 0;
        } catch (NumberFormatException e){
            return ErrorCodes.busyClientOrder;
        }
    }

    public int addNewOrder(long oid, long coid, long pid, int pc) throws SQLException {
        try {
            rep.addNewOrder(oid, coid, pid, pc);
            return 0;
        } catch (NumberFormatException e){
            return ErrorCodes.busyClientOrder;
        }
    }

    public int changeProduct(long pid, String pn, int pc, int pp) throws SQLException {
        try {
            rep.changeProduct(pid, pn, pc,  pp);
            return 0;
        } catch (NumberFormatException e){
            return ErrorCodes.busyClientOrder;
        }
    }
    public int getRoleId(String login) throws SQLException {
        return rep.getRoleId(login);
    }

    public ArrayList<ClientOrder> getTableClientOrder() throws SQLException {
        return rep.getTableClientOrder();
    }

    public byte confirmationOrder(long clientOrderId) throws SQLException {
        return rep.confirmationOrder(clientOrderId);
    }

    public void createNewProduct(long pid, String name, int count, int price) throws Exception{
        rep.createNewProduct(pid, name, count, price);
    }

    public void editProductLine(long pid, String name, int count, int price) throws SQLException {
        rep.editProductLine(pid, name, count, price);
    }


    public ArrayList<ManagerOrder> getTableManagerOrder(long uid) throws SQLException {
        return rep.getTableManagerOrder(uid);
    }
    public ArrayList<ManagerOrder> getTableManagerOrder() throws SQLException {
        return rep.getTableManagerOrder();
    }

    public long getIdByLogin(String login) throws SQLException {
        return rep.getIdByLogin(login);
    }

    public int addNewManagerOrder(long manoid, long uid, int amount, byte spo, byte sfpo) throws SQLException {
        try {
            rep.addNewManagerOrder(manoid, uid, amount, spo, sfpo);
            return 0;
        } catch (NumberFormatException e){
            return ErrorCodes.mistake;
        }
    }

    public int addNewMOrder(long moid, long manoid, long pid, int pmc) throws SQLException {
        try {
            rep.addNewMOrder(moid, manoid, pid, pmc);
            return 0;
        } catch (NumberFormatException e){
            return ErrorCodes.mistake;
        }
    }

    public int changeProviderProduct(long pid, String pn, int pmc, int pp) throws SQLException {
        try {
            rep.changeProviderProduct(pid, pn, pmc,  pp);
            return 0;
        } catch (NumberFormatException e){
            return ErrorCodes.mistake;
        }
    }
    public void editProductLineClient(long pid, String name, int count, int price) throws SQLException {
        rep.editProductLineClient(pid, name, count, price);
    }

    public byte confirmationManagerOrder(long managerOrderId) throws SQLException {
        return rep.confirmationManagerOrder(managerOrderId);
    }

    public int getClientMoney(long uid) throws SQLException {
        return rep.getClientMoney(uid);
    }
    public void topUpClientMoney(long uid, int money) throws SQLException {
        rep.topUpClientMoney(uid, money);
    }

    public int getStatusMOrder(long coid) throws SQLException {
        return rep.getStatusMOrder(coid);
    }
    public int getStatusFullPayOrder(long coid) throws SQLException {
        return rep.getStatusFullPayOrder(coid);
    }
    public int getAmountOrder(long coid) throws SQLException {
        return rep.getAmountOrder(coid);
    }
    public void changeClientBalance(long uid, int amount) throws SQLException {
        rep.changeClientBalance(uid, amount);
    }
    public int setStatusFullPayOrder(long coid) throws SQLException {
        return rep.setStatusFullPayOrder(coid);
    }




}
