package storage.mappers.order;

import businesslogic.orderfactory.ManagerOrder;
import businesslogic.orderfactory.ManagerOrderFactory;
import businesslogic.orderfactory.Order;
import storage.Gateway;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManagerOrderMapper {
    private static ArrayList<ManagerOrder> managerOrders = new ArrayList<ManagerOrder>();
    private Connection connection;

    public ManagerOrderMapper() throws SQLException, IOException {
        Gateway gateway = Gateway.getInstance();
        connection = gateway.getDataSource().getConnection();
    }

    public ArrayList<ManagerOrder> getTableManagerOrder(Long uid) throws SQLException {
        ArrayList<ManagerOrder> res = new ArrayList<ManagerOrder>();
        String querySQL = "SELECT * FROM managerorders WHERE UserId = ?";
        PreparedStatement ps = connection.prepareStatement(querySQL);
        ps.setLong(1, uid);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Long moid = rs.getLong("ManagerOrderId");
            int am = rs.getInt("Amount");
            Byte spo = rs.getByte("StatusPOrder");
            Byte sfpo = rs.getByte("StatusFullPayOrder");
            ManagerOrder mo = ManagerOrderFactory.createManagerOrder(moid, uid, am, spo, sfpo);
            addToManagerOrders(mo);
            if(mo.getManagerOrderId() != 0)
                res.add(mo);
        }
        return res;
    }
    public ArrayList<ManagerOrder> getTableManagerOrder() throws SQLException {
        ArrayList<ManagerOrder> res = new ArrayList<ManagerOrder>();
        String querySQL = "SELECT * FROM managerorders ";
        PreparedStatement ps = connection.prepareStatement(querySQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            long moid = rs.getLong("ManagerOrderId");
            long uid = rs.getLong("UserId");
            int am = rs.getInt("Amount");
            Byte spo = rs.getByte("StatusPOrder");
            Byte sfpo = rs.getByte("StatusFullPayOrder");
            ManagerOrder mo = ManagerOrderFactory.createManagerOrder(moid, uid, am, spo, sfpo);
            addToManagerOrders(mo);
            if(mo.getManagerOrderId() != 0)
                res.add(mo);
        }
        return res;
    }

    public void addToManagerOrders(ManagerOrder mo){
        for (int i = 0; i < managerOrders.size(); ++i){
            if ((managerOrders.get(i).getManagerOrderId()) == (mo.getManagerOrderId())){
                managerOrders.set(i, mo);
                return;
            }
        }
        managerOrders.add(mo);
    }

    public void addNewManagerOrder(ManagerOrder managerOrder) throws SQLException {
        String querySQL = "INSERT INTO managerorders(" +
                "managerorders.ManagerOrderId, " +
                "managerorders.UserId, " +
                "managerorders.Amount, " +
                "managerorders.StatusPOrder, " +
                "managerorders.StatusFullPayOrder) " +
                "VALUES (?, ?, ?, ?, ?);";

        PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
        preparedStatement.setLong(1, managerOrder.getManagerOrderId());
        preparedStatement.setLong(2, managerOrder.getUserId());
        preparedStatement.setInt(3, managerOrder.getAmount());
        preparedStatement.setByte(4, managerOrder.getStatusPOrder());
        preparedStatement.setByte(5, managerOrder.getStatusFullPayOrder());

        preparedStatement.executeUpdate();
        //return true;
    }
    public void addNewMOrder(Order order) throws SQLException {
        String querySQL = "INSERT INTO morders(" +
                "morders.MOrderId, " +
                "morders.ManagerOrderId, " +
                "morders.ProductId, " +
                "morders.ProductCount) " +
                "VALUES (?, ?, ?, ?);";

        PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
        preparedStatement.setLong(1, order.getOrderId());
        preparedStatement.setLong(2, order.getClientOrderId());
        preparedStatement.setLong(3, order.getProductId());
        preparedStatement.setInt(4, order.getProductCount());

        preparedStatement.executeUpdate();
        //return true;
    }

    public byte confirmationManagerOrder(long managerOrderId) throws SQLException {
        String querySQL = "UPDATE managerorders SET StatusPOrder = ? WHERE ManagerOrderId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
        byte st = 1;
        preparedStatement.setInt(1, st);
        preparedStatement.setLong(2, managerOrderId);
        preparedStatement.executeUpdate();
        return st;

    }
}
