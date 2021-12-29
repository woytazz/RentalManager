package jdbc;

import reservations.Reservation;
import managers.ReservationManager;

import java.sql.*;

import java.util.Calendar;

public class JdbcReservations {
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:VMRentalDB.db";


    private Connection conn;
    private Statement stat;

    public JdbcReservations() {
        try {
            Class.forName(JdbcReservations.DRIVER);
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

    // Create table 'Reservations' in DB
    public void createTable() {
        String createReservations = "CREATE TABLE IF NOT EXISTS Reservations (reservationID INTEGER PRIMARY KEY AUTOINCREMENT, clientID int, clientType varchar(1), VMID int, vmType varchar(3), startDate DateTime, endDate DateTime, isEnded BOOLEAN NOT NULL CHECK (isEnded IN (0, 1)))";

        try {
            stat.execute(createReservations);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert new Reservation customer to DB
    // ID is auto incremented
    // 'startDate' is set by local time
    // 'endDare' is set default as null
    // 'isEnded' is set default as 'false'
    public boolean insertReservation(int clientID, String clientType, int VMID, String vmType) {
        Calendar cal = Calendar.getInstance();
        java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());

        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into Reservations values (NULL, ?, ?, ?, ?, ?, ?, ?);");
            prepStmt.setInt(1, clientID);
            prepStmt.setString(2, clientType);
            prepStmt.setInt(3, VMID);
            prepStmt.setString(4, vmType);
            prepStmt.setTimestamp(5, timestamp);
            prepStmt.setTimestamp(6, null);
            prepStmt.setBoolean(7, false);

            prepStmt.execute();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    // Update 'endDate' end 'isEnded' column in 'Reservations' table
    public boolean endReservation(int reservationID) {
        Calendar cal = Calendar.getInstance();
        java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());

        try {
            PreparedStatement prepStmt = conn.prepareStatement("update Reservations set endDate = (?), isEnded = (?) where reservationID = (?);");
            prepStmt.setTimestamp(1, timestamp);
            prepStmt.setBoolean(2, true);
            prepStmt.setInt(3, reservationID);

            prepStmt.execute();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    // Get ClientManager of Reservations
    public ReservationManager getReservationsManager() {
        ReservationManager reservationManager = new ReservationManager();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM Reservations");
            int reservationID, clientID, VMID;
            String clientType, vmType;
            Date startDate;
            Date endDate;
            boolean isEnded;
            while (result.next()) {
                reservationID = result.getInt("reservationID");
                clientID = result.getInt("clientID");
                clientType = result.getString("clientType");
                VMID = result.getInt("VMID");
                vmType = result.getString("vmType");
                startDate = result.getDate("startDate");
                endDate = result.getDate("endDate");
                isEnded = result.getBoolean("isEnded");

                reservationManager.addReservation(new Reservation(reservationID, clientID, clientType, VMID, vmType, startDate, endDate, isEnded));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return reservationManager;
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
