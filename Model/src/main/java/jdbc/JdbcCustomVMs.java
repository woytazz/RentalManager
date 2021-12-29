package jdbc;

import vms.CustomVM;
import managers.VMManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcCustomVMs {
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:VMRentalDB.db";

    private Connection conn;
    private Statement stat;

    public JdbcCustomVMs() {
        try {
            Class.forName(JdbcCustomVMs.DRIVER);
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

    // Create table 'CustomVMs' in DB
    public void createTable() {
        String createCustomVMs = "CREATE TABLE IF NOT EXISTS CustomVMs (VMID INTEGER PRIMARY KEY AUTOINCREMENT, OS varchar(255), diskSize int, threads int, basicPrice int, isRented BOOLEAN NOT NULL CHECK (isRented IN (0, 1)), GPU varchar(255), snapshots int)";

        try {
            stat.execute(createCustomVMs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert new Basic VM to DB
    // ID is auto incremented and rented status is default as 'false'
    public boolean insertCustomVM(String OS, int diskSize, int threads, int basicPrice, String GPU, int snapshots) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into CustomVMs values (NULL, ?, ?, ?, ?, ?, ?, ?);");
            prepStmt.setString(1, OS);
            prepStmt.setInt(2, diskSize);
            prepStmt.setInt(3, threads);
            prepStmt.setInt(4, basicPrice);
            prepStmt.setBoolean(5, false);
            prepStmt.setString(6, GPU);
            prepStmt.setInt(7, snapshots);

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
                    "update CustomVMs set isRented = (?) where VMID = (?);");
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
                    "update CustomVMs set isRented = (?) where VMID = (?);");
            prepStmt.setBoolean(1, false);
            prepStmt.setInt(2, VMID);

            prepStmt.execute();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    // Delete Custom VM from table in DB
    public boolean deleteCustomVM(int VMID) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "delete from CustomVMs where VMID = (?);");
            prepStmt.setInt(1, VMID);

            prepStmt.execute();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    // Get VMManager of Custom VMs
    public VMManager getCustomVMsManager() {
        VMManager vmManager = new VMManager();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM CustomVMs");
            int VMID, diskSize, threads, basicPrice, snapshots;
            String OS, GPU;
            boolean isRented;
            while (result.next()) {
                VMID = result.getInt("VMID");
                OS = result.getString("OS");
                diskSize = result.getInt("diskSize");
                threads = result.getInt("threads");
                basicPrice = result.getInt("basicPrice");
                isRented = result.getBoolean("isRented");
                GPU = result.getString("GPU");
                snapshots = result.getInt("snapshots");

                vmManager.addVM(new CustomVM(VMID, OS, diskSize, threads, basicPrice, isRented, GPU, snapshots));
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
