import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import reservations.Reservation;
import rental.Rental;

import java.io.IOException;
import java.util.Objects;

public class ReservationsController {

    @FXML
    private TableView<Reservation> table;
    @FXML
    private TableColumn<Reservation, String> informationColumn;
    @FXML
    private Button backButton;
    @FXML
    private TextField clientIDTextField;
    @FXML
    private ChoiceBox<String> clientTypeChoiceBox;
    @FXML
    private TextField vmIDTextField;
    @FXML
    private ChoiceBox<String> vmTypeChoiceBox;
    @FXML
    private Button addButton;
    @FXML
    private TextField reservationIDTextField;
    @FXML
    private Button endButton;

    private final Rental rental = new Rental();

    ObservableList<Reservation> reservationsList = FXCollections.observableArrayList(rental.getReservations().getReservationsManager().getReservationsList());

    // Default function to initialize scene
    @FXML
    public void initialize() {
        backButton.setOnAction(new EventHandler<>() {

            // Button used to back to the Menu
            @Override
            public void handle(ActionEvent e) {
                try {
                    rental.getReservations().closeConnection();
                    backButton.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/Menu.fxml"))));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        // Set values of table
        informationColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getReservationInfo()));
        table.setItems(reservationsList);

        clientTypeChoiceBox.getItems().add("Normal Customer");
        clientTypeChoiceBox.getItems().add("Student");
        clientTypeChoiceBox.getItems().add("Company Customer");

        vmTypeChoiceBox.getItems().add("Basic VM");
        vmTypeChoiceBox.getItems().add("Custom VM");
    }

    // Button used to add Reservation
    public void clickAddButton() {
        String clientType = null, vmType = null;

        // Check what value is in client type choice box
        if (clientTypeChoiceBox.getSelectionModel().getSelectedIndex() == 0) {
            clientType = "N";
        } else if (clientTypeChoiceBox.getSelectionModel().getSelectedIndex() == 1) {
            clientType = "S";
        } else if (clientTypeChoiceBox.getSelectionModel().getSelectedIndex() == 2) {
            clientType = "C";
        } else {
            PopupWindows.error("Cannot choose client type!");
        }

        // Check what value is in VM type choice box
        if (vmTypeChoiceBox.getSelectionModel().getSelectedIndex() == 0) {
            vmType = "BVM";
        } else if (vmTypeChoiceBox.getSelectionModel().getSelectedIndex() == 1) {
            vmType = "CVM";
        } else {
            PopupWindows.error("Cannot choose VM type!");
        }

        // Check if this Client and VM exist
        // If not function display popup window with error
        if (rental.checkClient(Integer.parseInt(clientIDTextField.getText()), Objects.requireNonNull(clientType)) && rental.checkVM(Integer.parseInt(vmIDTextField.getText()), Objects.requireNonNull(vmType))) {
            // If VM can be rented, function rents this VM
            // If not function displays popup window with warning
            if (rental.rentVM(Integer.parseInt(vmIDTextField.getText()), vmType)) {
                reservationsList.clear();
                rental.getReservations().insertReservation(Integer.parseInt(clientIDTextField.getText()), clientType, Integer.parseInt(vmIDTextField.getText()), vmType);
                reservationsList.addAll(rental.getReservations().getReservationsManager().getReservationsList());
            } else {
                PopupWindows.warning("Cannot rent VM");
            }
        } else {
            PopupWindows.error("There is not that client or VM!");
        }
    }

    // Function for 'endButton'
    // Check if Reservations exists
    // If yes check if Reservation is ended
    // If not it ends Reservation end shows calculated price for this Reservation
    public void clickEndButton() {
        if (rental.checkReservation(Integer.parseInt(reservationIDTextField.getText()))) {
            if (!rental.checkIfReservationEnded(Integer.parseInt(reservationIDTextField.getText()))) {
                if (rental.unrentVM(Integer.parseInt(reservationIDTextField.getText()))) {
                    reservationsList.clear();
                    rental.getReservations().endReservation(Integer.parseInt(reservationIDTextField.getText()));
                    reservationsList.addAll(rental.getReservations().getReservationsManager().getReservationsList());
                    PopupWindows.information("You ended reservation.");
                    PopupWindows.information("You must pay: " + String.valueOf(rental.getCalculatedPrice(Integer.parseInt(reservationIDTextField.getText()))));
                } else {
                    PopupWindows.warning("Cannot unrent VM!");
                }
            } else {
                PopupWindows.information("This reservation is already ended!");
            }
        } else {
            PopupWindows.error("There is not that reservation!");
        }
    }

}
