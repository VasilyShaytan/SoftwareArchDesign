package storage;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Gateway {
    static final String url = "jdbc:mysql://localhost:3307/schoolrobotsystem?serverTimezone=UTC";
    static final String user = "vasia";
    static final String password = "123456";
    static final String driver = "com.mysql.cj.jdbc.Driver";
    private static Gateway dataGateway;
    private static MysqlDataSource dataSource;

    private Gateway() throws IOException {
        dataSource = new MysqlDataSource();
        dataSource.setURL(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException se) {
            se.printStackTrace();
        }
    }

    public static Gateway getInstance() throws IOException {
        if(dataGateway == null)
            dataGateway = new Gateway();
        return dataGateway;
    }

    public MysqlDataSource getDataSource() {
        return dataSource;
    }

    /*public void dropAll() throws SQLException {
        executeSqlScript((Connection) getDataSource().getConnection(),
                new File("src\\main\\java\\db\\createdb.sql"));
    }*/

    private void executeSqlScript(Connection conn, File inputFile) {

        // Delimiter
        String delimiter = ";";

        // Create scanner
        Scanner scanner;
        try {
            scanner = new Scanner(inputFile).useDelimiter(delimiter);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            return;
        }

        // Loop through the SQL file statements
        Statement currentStatement = null;
        while(scanner.hasNext()) {

            // Get statement
            String rawStatement = scanner.next() + delimiter;
            try {
                // Execute statement
                currentStatement = conn.createStatement();
                currentStatement.execute(rawStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Release resources
                if (currentStatement != null) {
                    try {
                        currentStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                currentStatement = null;
            }
        }
        scanner.close();
    }
}
