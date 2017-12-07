package storage.mappers.order;

import businesslogic.orderfactory.ClientOrder;
import businesslogic.orderfactory.ClientOrderFactory;
import businesslogic.orderfactory.Order;
import storage.Gateway;
import storage.mappers.user.ClientMapper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientOrderMapper {
    private static ArrayList<ClientOrder> clientOrders = new ArrayList<ClientOrder>();
    private Connection connection;
    //private ClientMapper clientMapper;
    //private ClientOrderMapper clientOrderMapper;

    public ClientOrderMapper() throws SQLException, IOException {
        Gateway gateway = Gateway.getInstance();
        connection = gateway.getDataSource().getConnection();
    }

    public ArrayList<ClientOrder> getClientOrders(String login) throws SQLException, IOException {
        ArrayList<ClientOrder> res = new ArrayList<ClientOrder>();
        ClientMapper cm = new ClientMapper();
        long uid = cm.findUserIdByLogin(login);    //1508409158;
        String query = "SELECT * FROM clientorders WHERE UserId = ?;";
        PreparedStatement st = connection.prepareStatement(query);
        st.setLong(1, uid);
        ResultSet rs = st.executeQuery();
        //if(!rs.next()) return null;
        while (rs.next()){
            long coid = rs.getLong("ClientOrderId");
            int am = rs.getInt("Amount");
            byte smo = rs.getByte("StatusMOrder");
            byte sppo = rs.getByte("StatusPPayOrder");
            byte sfpo = rs.getByte("StatusFullPayOrder");
            ClientOrder co = ClientOrderFactory.createClientOrder(coid, uid, am, smo, sppo, sfpo);
            addToClientOrders(co);
            if(co.getClientOrderId() != 0)
                res.add(co);

        }
        return res;
    }

    public void addToClientOrders(ClientOrder co){
        for (int i = 0; i < clientOrders.size(); ++i){
            if ((clientOrders.get(i).getClientOrderId()) == (co.getClientOrderId())){
                clientOrders.set(i, co);
                return;
            }
        }
        clientOrders.add(co);
    }

    public void addNewClientOrder(ClientOrder clientOrder) throws SQLException {
        String querySQL = "INSERT INTO clientorders(" +
                "clientorders.ClientOrderId, " +
                "clientorders.UserId, " +
                "clientorders.Amount, " +
                "clientorders.StatusMOrder, " +
                "clientorders.StatusPPayOrder, " +
                "clientorders.StatusFullPayOrder) " +
                "VALUES (?, ?, ?, ?, ?, ?);";

        PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
        preparedStatement.setLong(1, clientOrder.getClientOrderId());
        preparedStatement.setLong(2, clientOrder.getUserId());
        preparedStatement.setInt(3, clientOrder.getAmount());
        preparedStatement.setByte(4, clientOrder.getStatusMOrder());
        preparedStatement.setByte(5, clientOrder.getStatusPPayOrder());
        preparedStatement.setByte(6, clientOrder.getStatusFullPayOrder());

        preparedStatement.executeUpdate();
        //return true;
    }
    public void addNewOrder(Order order) throws SQLException {
        String querySQL = "INSERT INTO orders(" +
                "orders.OrderId, " +
                "orders.ClientOrderId, " +
                "orders.ProductId, " +
                "orders.Count) " +
                "VALUES (?, ?, ?, ?);";

        PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
        preparedStatement.setLong(1, order.getOrderId());
        preparedStatement.setLong(2, order.getClientOrderId());
        preparedStatement.setLong(3, order.getProductId());
        preparedStatement.setInt(4, order.getProductCount());

        preparedStatement.executeUpdate();
        //return true;
    }

    public ArrayList<ClientOrder> getTableClientOrder() throws SQLException {
        ArrayList<ClientOrder> res = new ArrayList<ClientOrder>();
        String querySQL = "SELECT * FROM clientorders";
        PreparedStatement ps = connection.prepareStatement(querySQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Long coid = rs.getLong("ClientOrderId");
            Long uid = rs.getLong("UserId");
            int am = rs.getInt("Amount");
            Byte smo = rs.getByte("StatusMOrder");
            Byte sppo = rs.getByte("StatusPPayOrder");
            Byte sfpo = rs.getByte("StatusFullPayOrder");
            ClientOrder co = ClientOrderFactory.createClientOrder(coid, uid, am, smo, sppo, sfpo);
            addToClientOrders(co);
            if(co.getClientOrderId() != 0)
                res.add(co);
        }
        return res;
    }

    public byte confirmationOrder(long clientOrderId) throws SQLException {
        String querySQL = "UPDATE clientorders SET StatusMOrder = ? WHERE ClientOrderId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
        byte st = 1;
        preparedStatement.setInt(1, st);
        preparedStatement.setLong(2, clientOrderId);
        preparedStatement.executeUpdate();
        return st;

    }

    public int getStatusMOrder(long coid) throws SQLException {
        String query = "SELECT * FROM clientorders WHERE ClientOrderId = ?;";
        PreparedStatement st = connection.prepareStatement(query);
        st.setLong(1, coid);
        ResultSet rs = st.executeQuery();
        if(!rs.next()) return 0;

        int status = rs.getInt("StatusMOrder");
        return status;
    }

    public int getStatusFullPayOrder(long coid) throws SQLException {
        String query = "SELECT * FROM clientorders WHERE ClientOrderId = ?;";
        PreparedStatement st = connection.prepareStatement(query);
        st.setLong(1, coid);
        ResultSet rs = st.executeQuery();
        if(!rs.next()) return 0;

        int status = rs.getInt("StatusFullPayOrder");
        return status;
    }

    public int getAmountOrder(long coid) throws SQLException {
        String query = "SELECT * FROM clientorders WHERE ClientOrderId = ?;";
        PreparedStatement st = connection.prepareStatement(query);
        st.setLong(1, coid);
        ResultSet rs = st.executeQuery();
        if(!rs.next()) return 0;

        int amount = rs.getInt("Amount");
        return amount;
    }

    public int setStatusFullPayOrder(long coid) throws SQLException {
        String querySQL = "UPDATE clientorders SET StatusFullPayOrder = ? WHERE ClientOrderId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
        byte st = 1;
        preparedStatement.setInt(1, st);
        preparedStatement.setLong(2, coid);
        preparedStatement.executeUpdate();
        return st;
    }



}
