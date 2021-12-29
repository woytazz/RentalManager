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

public class CompanyCustomersController {

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
    private TextField nipTextField;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField idTextField;

    private final Rental rental = new Rental();

    ObservableList<Client> companyCustomersList = FXCollections.observableArrayList(rental.getCompanyCustomers().getCompanyCustomersManager().getClientList());

    // Default function to initialize scene
    @FXML
    public void initialize() {
        backButton.setOnAction(new EventHandler<>() {

            // Button used to back to the Menu
            @Override
            public void handle(ActionEvent e) {
                try {
                    rental.getCompanyCustomers().closeConnection();
                    backButton.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/Menu.fxml"))));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        // Set values of table
        informationColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getClientInfo()));
        table.setItems(companyCustomersList);

        // Button used to add Company customer
        addButton.setOnAction(event -> {
            // Check if Company customer exists with this NIP number
            if (rental.getCompanyCustomers().getCompanyCustomersManager().checkNewClient(Integer.parseInt(nipTextField.getText()))) {
                companyCustomersList.clear();
                rental.getCompanyCustomers().insertCompanyCustomer(String.valueOf(nameTextField.getText()), String.valueOf(lastNameTextField.getText()), Integer.parseInt(nipTextField.getText()));
                companyCustomersList.addAll(rental.getCompanyCustomers().getCompanyCustomersManager().getClientList());
                PopupWindows.information("Company customer has been added.");
            } else {
                PopupWindows.error("Someone has the same NIP number");
            }
        });

        // Function checks if Company customer exists
        // If yes, delete this Client
        deleteButton.setOnAction(event -> {
            if (rental.checkClient(Integer.parseInt(idTextField.getText()), "C")) {
                companyCustomersList.clear();
                rental.getCompanyCustomers().deleteCompanyCustomer(Integer.parseInt(idTextField.getText()));
                companyCustomersList.addAll(rental.getCompanyCustomers().getCompanyCustomersManager().getClientList());
            } else {
                PopupWindows.warning("There is not that company customer to delete!");
            }
        });

    }

}
