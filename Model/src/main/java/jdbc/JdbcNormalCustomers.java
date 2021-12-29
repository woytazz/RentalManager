package jdbc;

import managers.ClientManager;
import clients.NormalCustomer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcNormalCustomers {
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:VMRentalDB.db";

    private Connection conn;
    private Statement stat;

    public JdbcNormalCustomers() {
        try {
            Class.forName(JdbcNormalCustomers.DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        createTable();
    }

    // Create table 'NormalCustomers' in DB
    public void createTable() {
        String createNormalCustomers = "CREATE TABLE IF NOT EXISTS NormalCustomers (clientID INTEGER PRIMARY KEY AUTOINCREMENT, name varchar(255), lastName varchar(255))";
        try {
            stat.execute(createNormalCustomers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert new Company customer to DB
    // ID is auto incremented
    public boolean insertNormalCustomers(String name, String lastName) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into NormalCustomers values (NULL, ?, ?);");
            prepStmt.setString(1, name);
            prepStmt.setString(2, lastName);

            prepStmt.execute();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    // Delete Normal customer from table in DB
    public boolean deleteNormalCustomer(int clientID) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "delete from NormalCustomers where clientID = (?);");
            prepStmt.setInt(1, clientID);

            prepStmt.execute();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    // Get ClientManager of Normal customers
    public ClientManager getNormalCustomersManager() {
        ClientManager clientManager = new ClientManager();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM NormalCustomers");
            int clientID;
            String name, lastName;
            while (result.next()) {
                clientID = result.getInt("clientID");
                name = result.getString("name");
                lastName = result.getString("lastName");
                clientManager.addClient(new NormalCustomer(clientID, name, lastName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return clientManager;
    }

    // Close connection with DB
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
