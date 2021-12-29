package jdbc;

import managers.ClientManager;
import clients.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcStudents {
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:VMRentalDB.db";

    private Connection conn;
    private Statement stat;

    public JdbcStudents() {
        try {
            Class.forName(JdbcStudents.DRIVER);
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

    // Create table 'Students' in DB
    public void createTable() {
        String createStudents = "CREATE TABLE IF NOT EXISTS Students (clientID INTEGER PRIMARY KEY AUTOINCREMENT, name varchar(255), lastName varchar(255), indexNumber int)";
        try {
            stat.execute(createStudents);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert new Company customer to DB
    // ID is auto incremented
    public boolean insertStudent(String name, String lastName, int indexNumber) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into Students values (NULL, ?, ?, ?);");
            prepStmt.setString(1, name);
            prepStmt.setString(2, lastName);
            prepStmt.setInt(3, indexNumber);

            prepStmt.execute();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    // Delete Student from table in DB
    public boolean deleteStudent(int clientID) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "delete from Students where clientID = (?);");
            prepStmt.setInt(1, clientID);

            prepStmt.execute();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    // Get ClientManager of Students
    public ClientManager getStudentsManager() {
        ClientManager clientManager = new ClientManager();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM Students");
            int clientID, indexNumber;
            String name, lastName;
            while (result.next()) {
                clientID = result.getInt("clientID");
                name = result.getString("name");
                lastName = result.getString("lastName");
                indexNumber = result.getInt("indexNumber");
                clientManager.addClient(new Student(clientID, name, lastName, indexNumber));
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
