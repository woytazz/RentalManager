package jdbc;

import vms.BasicVM;
import managers.VMManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcBasicVMs {
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:VMRentalDB.db";

    private Connection conn;
    private Statement stat;

    public JdbcBasicVMs() {
        try {
            Class.forName(JdbcBasicVMs.DRIVER);
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

    // Create table 'BasicVMs' in DB
    public void createTable() {
        String createBasicVMs = "CREATE TABLE IF NOT EXISTS BasicVMs (VMID INTEGER PRIMARY KEY AUTOINCREMENT, OS varchar(255), diskSize int, threads int, basicPrice int, isRented BOOLEAN NOT NULL CHECK (isRented IN (0, 1)))";

        try {
            stat.execute(createBasicVMs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert new Basic VM to DB
    // ID is auto incremented and rented status is default as 'false'
    public boolean insertBasicVM(String OS, int diskSize, int threads, int basicPrice) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into BasicVMs values (NULL, ?, ?, ?, ?, ?);");
            prepStmt.setString(1, OS);
            prepStmt.setInt(2, diskSize);
            prepStmt.setInt(3, threads);
            prepStmt.setInt(4, basicPrice);
            prepStmt.setBoolean(5, false);

            prepStmt.execute();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    // Update rented status to 'true'
    public boolean setRented(int VMID) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "update BasicVMs set isRented = (?) where VMID = (?);");
            prepStmt.setBoolean(1, true);
            prepStmt.setInt(2, VMID);

            prepStmt.execute();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    // Update rented status to 'false'
    public boolean setUnrented(int VMID) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "update BasicVMs set isRented = (?) where VMID = (?);");
            prepStmt.setBoolean(1, false);
            prepStmt.setInt(2, VMID);

            prepStmt.execute();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    // Delete Basic VM from table in DB
    public boolean deleteBasicVM(int VMID) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "delete from BasicVMs where VMID = (?);");
            prepStmt.setInt(1, VMID);

            prepStmt.execute();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    // Get VMManager of Basic VMs
    public VMManager getBasicVMsManager() {
        VMManager vmManager = new VMManager();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM BasicVMs");
            int VMID, diskSize, threads, basicPrice;
            String OS;
            boolean isRented;
            while (result.next()) {
                VMID = result.getInt("VMID");
                OS = result.getString("OS");
                diskSize = result.getInt("diskSize");
                threads = result.getInt("threads");
                basicPrice = result.getInt("basicPrice");
                isRented = result.getBoolean("isRented");

                vmManager.addVM(new BasicVM(VMID, OS, diskSize, threads, basicPrice, isRented));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return vmManager;
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
