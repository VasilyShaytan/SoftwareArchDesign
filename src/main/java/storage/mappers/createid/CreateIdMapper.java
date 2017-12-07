package storage.mappers.createid;

import storage.Gateway;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateIdMapper {

    private Connection connection;

    public CreateIdMapper() throws SQLException, IOException {

        Gateway gateway = Gateway.getInstance();
        connection = gateway.getDataSource().getConnection();
    }

    public int createUserId() throws SQLException {
        String querySQL = "SELECT UserId FROM createid;";
        PreparedStatement st = connection.prepareStatement(querySQL);
        ResultSet rs = st.executeQuery();
        if(!rs.next()) return 0;
        int uid = rs.getInt("UserId");
        querySQL = "UPDATE createid SET UserId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
        preparedStatement.setInt(1, uid+1);
        preparedStatement.executeUpdate();
        return uid;
    }

    public int createClientOrderId() throws SQLException {

        String querySQL = "SELECT ClientOrderId FROM createid;";
        PreparedStatement st = connection.prepareStatement(querySQL);
        ResultSet rs = st.executeQuery();
        if(!rs.next()) return 0;
        int coid = rs.getInt("ClientOrderId");
        querySQL = "UPDATE createid SET ClientOrderId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
        preparedStatement.setInt(1, coid+1);
        preparedStatement.executeUpdate();
        return coid;
    }

    public int createProductId() throws SQLException {
        String querySQL = "SELECT ProductId FROM createid;";
        PreparedStatement st = connection.prepareStatement(querySQL);
        ResultSet rs = st.executeQuery();
        if(!rs.next()) return 0;
        int pid = rs.getInt("ProductId");
        querySQL = "UPDATE createid SET ProductId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
        preparedStatement.setInt(1, pid+1);
        preparedStatement.executeUpdate();
        return pid;
    }

    public int createOrderId() throws SQLException {

        String querySQL = "SELECT OrderId FROM createid;";
        PreparedStatement st = connection.prepareStatement(querySQL);
        ResultSet rs = st.executeQuery();
        if(!rs.next()) return 0;
        int oid = rs.getInt("OrderId");
        querySQL = "UPDATE createid SET OrderId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
        preparedStatement.setInt(1, oid+1);
        preparedStatement.executeUpdate();
        return oid;
    }

    public long createManagerOrderId() throws SQLException {
        String querySQL = "SELECT ManagerOrderId FROM createid;";
        PreparedStatement st = connection.prepareStatement(querySQL);
        ResultSet rs = st.executeQuery();
        if(!rs.next()) return 0;
        Long moid = rs.getLong("ManagerOrderId");
        querySQL = "UPDATE createid SET ManagerOrderId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
        preparedStatement.setLong(1, moid+1);
        preparedStatement.executeUpdate();
        return moid;
    }

    public long createMOrderId() throws SQLException {
        String querySQL = "SELECT MOrderId FROM createid;";
        PreparedStatement st = connection.prepareStatement(querySQL);
        ResultSet rs = st.executeQuery();
        if(!rs.next()) return 0;
        Long moid = rs.getLong("MOrderId");
        querySQL = "UPDATE createid SET MOrderId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
        preparedStatement.setLong(1, moid+1);
        preparedStatement.executeUpdate();
        return moid;
    }

}
