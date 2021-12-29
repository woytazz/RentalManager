package jdbc;

import managers.ClientManager;
import clients.CompanyCustomer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcCompanyCustomers {
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:VMRentalDB.db";

    private Connection conn;
    private Statement stat;

    public JdbcCompanyCustomers() {
        try {
            Class.forName(JdbcCompanyCustomers.DRIVER);
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

    // Create table 'CompanyCustomers' in DB
    public void createTable() {
        String createCompanyCustomers = "CREATE TABLE IF NOT EXISTS CompanyCustomers (clientID INTEGER PRIMARY KEY AUTOINCREMENT, name varchar(255), lastName varchar(255), NIP int)";
        try {
            stat.execute(createCompanyCustomers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert new Company customer to DB
    // ID is auto incremented
    public boolean insertCompanyCustomer(String name, String lastName, int NIP) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into CompanyCustomers values (NULL, ?, ?, ?);");
            prepStmt.setString(1, name);
            prepStmt.setString(2, lastName);
            prepStmt.setInt(3, NIP);

            prepStmt.execute();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    // Delete Company customer from table in DB
    public boolean deleteCompanyCustomer(int clientID) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "delete from CompanyCustomers where clientID = (?);");
            prepStmt.setInt(1, clientID);

            prepStmt.execute();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    // Get ClientManager of Company customers
    public ClientManager getCompanyCustomersManager() {
        ClientManager clientManager = new ClientManager();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM CompanyCustomers");
            int clientID, NIP;
            String name, lastName;
            while (result.next()) {
                clientID = result.getInt("clientID");
                name = result.getString("name");
                lastName = result.getString("lastName");
                NIP = result.getInt("NIP");
                clientManager.addClient(new CompanyCustomer(clientID, name, lastName, NIP));
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
