package storage.mappers.user;

import businesslogic.clientfactory.Client;
import businesslogic.clientfactory.ClientFactory;
import storage.Gateway;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientMapper {

    private static ArrayList<Client> users = new ArrayList<Client>();
    private Connection connection;

    public ClientMapper() throws SQLException, IOException {

        Gateway gateway = Gateway.getInstance();
        connection = gateway.getDataSource().getConnection();
    }



    public Client findUserByLogin(String login) throws SQLException {

        String query = "SELECT * FROM users WHERE Login = ?;";
        PreparedStatement st = connection.prepareStatement(query);
        st.setString(1, login);
        ResultSet rs = st.executeQuery();
        if(!rs.next()) return null;

        int uid = rs.getInt("UserId");
        String log = rs.getString("Login");
        String pass = rs.getString("Password");
        String name = rs.getString("Name");
        String surname = rs.getString("Surname");
        int roleid = rs.getInt("RoleId");
        int money = rs.getInt("Money");
        Client u = ClientFactory.createClient(uid, log, pass, name, surname, roleid, money);
        addToUsers(u);
        return u;
    }



    public void addToUsers(Client u){
        for (int i = 0; i < users.size(); ++i){
            if (users.get(i).getLogin().equals(u.getLogin())){
                users.set(i, u);
                return;
            }
        }
        users.add(u);
    }

    public void addNewClient(Client client) throws SQLException {
        String querySQL = "INSERT INTO users(users.UserId, users.Login, users.Password, users.Name, users.Surname, users.RoleId, users.Money) VALUES (?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
        preparedStatement.setLong(1, client.getUserId());
        preparedStatement.setString(2, client.getLogin());
        preparedStatement.setString(3, client.getPassword());
        preparedStatement.setString(4, client.getName());
        preparedStatement.setString(5, client.getSurname());
        preparedStatement.setInt(6, client.getRoleId());
        preparedStatement.setInt(7, client.getMoney());
        // execute insert SQL stetement
        preparedStatement.executeUpdate();
        //return true;
    }

    public long findUserIdByLogin(String login) throws SQLException {
        String querySQL = "SELECT * FROM users WHERE Login = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
        preparedStatement.setString(1, login);
        ResultSet rs = preparedStatement.executeQuery();
        if(!rs.next()) return 0;
        return rs.getLong("UserId");

    }

    public void topUpClientMoney(long uid, int money) throws SQLException {
        String querySQL = "SELECT * FROM users WHERE UserId = ?";
        PreparedStatement ps = connection.prepareStatement(querySQL);
        ps.setLong(1, uid);
        ResultSet rs = ps.executeQuery();
        if(!rs.next()) return;
        int oldMoney = rs.getInt("Money");

        String querySQL1 = "UPDATE users SET Money = ? WHERE UserId = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(querySQL1);
        preparedStatement.setInt(1, oldMoney + money);
        preparedStatement.setLong(2, uid);

        preparedStatement.executeUpdate();
    }

    public void changeClientBalance(long uid, int amount) throws SQLException {
        String querySQL = "SELECT * FROM users WHERE UserId = ?";
        PreparedStatement ps = connection.prepareStatement(querySQL);
        ps.setLong(1, uid);
        ResultSet rs = ps.executeQuery();
        if(!rs.next()) return;
        int oldMoney = rs.getInt("Money");

        String querySQL1 = "UPDATE users SET Money = ? WHERE UserId = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(querySQL1);
        preparedStatement.setInt(1, oldMoney - amount);
        preparedStatement.setLong(2, uid);

        preparedStatement.executeUpdate();
    }

}
