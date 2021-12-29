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
import rental.Rental;
import clients.Client;

import java.io.IOException;
import java.util.Objects;

public class NormalCustomersController {

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
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField idTextField;

    private final Rental rental = new Rental();

    ObservableList<Client> normalCustomersList = FXCollections.observableArrayList(rental.getNormalCustomers().getNormalCustomersManager().getClientList());

    // Default function to initialize scene
    @FXML
    public void initialize() {
        backButton.setOnAction(new EventHandler<>() {

            // Button used to back to the Menu
            @Override
            public void handle(ActionEvent e) {
                try {
                    rental.getNormalCustomers().closeConnection();
                    backButton.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/Menu.fxml"))));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        // Set values of table
        informationColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getClientInfo()));
        table.setItems(normalCustomersList);

        // Button used to add Normal customer
        addButton.setOnAction(event -> {
            normalCustomersList.clear();
            rental.getNormalCustomers().insertNormalCustomers(String.valueOf(nameTextField.getText()), String.valueOf(lastNameTextField.getText()));
            normalCustomersList.addAll(rental.getNormalCustomers().getNormalCustomersManager().getClientList());
            PopupWindows.information("Normal customer has been added.");
        });

        // Function checks if Normal customer exists
        // If yes, delete this Client
        deleteButton.setOnAction(event -> {
            if (rental.checkClient(Integer.parseInt(idTextField.getText()), "N")) {
                normalCustomersList.clear();
                rental.getNormalCustomers().deleteNormalCustomer(Integer.parseInt(idTextField.getText()));
                normalCustomersList.addAll(rental.getNormalCustomers().getNormalCustomersManager().getClientList());
            } else {
                PopupWindows.warning("There is not that normal customer to delete!");
            }
        });
    }

}
