import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import clients.Client;
import rental.Rental;

import java.io.IOException;
import java.util.Objects;

public class StudentsController {

    @FXML
    private TableView<Client> table;
    @FXML
    private TableColumn<Client, String> informationColumn;
    @FXML
    private Button backButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField indexNumberTextField;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField idTextField;

    private final Rental rental = new Rental();

    ObservableList<Client> studentsList = FXCollections.observableArrayList(rental.getStudents().getStudentsManager().getClientList());

    // Default function to initialize scene
    @FXML
    public void initialize() {
        backButton.setOnAction(new EventHandler<>() {

            // Button used to back to the Menu
            @Override
            public void handle(ActionEvent e) {
                try {
                    rental.getStudents().closeConnection();
                    backButton.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/Menu.fxml"))));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        // Set values of table
        informationColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getClientInfo()));
        table.setItems(studentsList);

        // Button used to add Student
        addButton.setOnAction(event -> {
            // Check if Student exists with this index number
            if (rental.getStudents().getStudentsManager().checkNewClient(Integer.parseInt(indexNumberTextField.getText()))) {
                studentsList.clear();
                rental.getStudents().insertStudent(String.valueOf(nameTextField.getText()), String.valueOf(lastNameTextField.getText()), Integer.parseInt(indexNumberTextField.getText()));
                studentsList.addAll(rental.getStudents().getStudentsManager().getClientList());
                PopupWindows.information("Student has been added.");
            } else {
                PopupWindows.error("Someone has the same index number!");
            }
        });

        // Function checks if Company customer exists
        // If yes, delete this Client
        deleteButton.setOnAction(event -> {
            if (rental.checkClient(Integer.parseInt(idTextField.getText()), "S")) {
                studentsList.clear();
                rental.getStudents().deleteStudent(Integer.parseInt(idTextField.getText()));
                studentsList.addAll(rental.getStudents().getStudentsManager().getClientList());
            } else {
                PopupWindows.warning("There is not that student to delete!");
            }
        });
    }

}
