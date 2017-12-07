package storage.mappers.user;

import storage.Gateway;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommonMapper {
    private Connection connection;

    public CommonMapper() throws SQLException, IOException {

        Gateway gateway = Gateway.getInstance();
        connection = gateway.getDataSource().getConnection();
    }

    public int getRoleId(String login) throws SQLException {

        String query = "SELECT * FROM users WHERE Login = ?;";
        PreparedStatement st = connection.prepareStatement(query);
        st.setString(1, login);
        ResultSet rs = st.executeQuery();
        if(!rs.next()) return 0;

        int roleId = rs.getInt("RoleId");
        return roleId;
    }

    public long getIdByLogin(String login) throws SQLException {
        String query = "SELECT * FROM users WHERE Login = ?;";
        PreparedStatement st = connection.prepareStatement(query);
        st.setString(1, login);
        ResultSet rs = st.executeQuery();
        if(!rs.next()) return 0;

        int userId = rs.getInt("UserId");
        return userId;
    }
    public int getClientMoney(long uid) throws SQLException {
        String query = "SELECT * FROM users WHERE UserId = ?;";
        PreparedStatement st = connection.prepareStatement(query);
        st.setLong(1, uid);
        ResultSet rs = st.executeQuery();
        if(!rs.next()) return 0;

        int money = rs.getInt("Money");
        return money;
    }

}
