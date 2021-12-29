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
import vms.VM;

import java.io.IOException;
import java.util.Objects;

public class BasicVMsController {

    @FXML
    private TableView<VM> table;
    @FXML
    private TableColumn<VM, String> informationColumn;
    @FXML
    private Button backButton;
    @FXML
    private TextField osTextField;
    @FXML
    private TextField diskSizeTextField;
    @FXML
    private TextField threadsTextField;
    @FXML
    private TextField basicPriceTextField;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField VMIDTextField;

    Rental rental = new Rental();

    ObservableList<VM> basicVMsList = FXCollections.observableArrayList(rental.getBasicVMs().getBasicVMsManager().getVMList());

    // Default function to initialize scene
    @FXML
    public void initialize() {
        backButton.setOnAction(new EventHandler<>() {

            // Button used to back to the Menu
            @Override
            public void handle(ActionEvent e) {
                try {
                    rental.getBasicVMs().closeConnection();
                    backButton.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/Menu.fxml"))));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        // Set values of table
        informationColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getVMInfo()));
        table.setItems(basicVMsList);

        // Button used to add Basic VM
        addButton.setOnAction(event -> {
            basicVMsList.clear();
            rental.getBasicVMs().insertBasicVM(String.valueOf(osTextField.getText()), Integer.parseInt(diskSizeTextField.getText()), Integer.parseInt(threadsTextField.getText()), Integer.parseInt(basicPriceTextField.getText()));
            basicVMsList.addAll(rental.getBasicVMs().getBasicVMsManager().getVMList());
            PopupWindows.information("Basic VM has been added.");
        });

        // Function checks if Basic VM exists
        // If yes, delete this VM
        deleteButton.setOnAction(event -> {
            if (rental.checkVM(Integer.parseInt(VMIDTextField.getText()), "BVM")) {
                basicVMsList.clear();
                rental.getBasicVMs().deleteBasicVM(Integer.parseInt(VMIDTextField.getText()));
                basicVMsList.addAll(rental.getBasicVMs().getBasicVMsManager().getVMList());
            } else {
                PopupWindows.warning("There is not that Basic VM to delete!");
            }
        });
    }

}
