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

public class CustomVMsController {

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
    private TextField gpuTextField;
    @FXML
    private TextField snapshotsTextField;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField VMIDTextField;

    Rental rental = new Rental();

    ObservableList<VM> customVMsList = FXCollections.observableArrayList(rental.getCustomVMs().getCustomVMsManager().getVMList());


    // Default function to initialize scene
    @FXML
    public void initialize() {
        backButton.setOnAction(new EventHandler<>() {

            // Button used to back to the Menu
            @Override
            public void handle(ActionEvent e) {
                try {
                    rental.getCustomVMs().closeConnection();
                    backButton.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/Menu.fxml"))));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        // Set values of table
        informationColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getVMInfo()));
        table.setItems(customVMsList);

        // Button used to add Custom VM
        addButton.setOnAction(event -> {
            customVMsList.clear();
            rental.getCustomVMs().insertCustomVM(String.valueOf(osTextField.getText()), Integer.parseInt(diskSizeTextField.getText()), Integer.parseInt(threadsTextField.getText()), Integer.parseInt(basicPriceTextField.getText()),
                    String.valueOf(gpuTextField.getText()), Integer.parseInt(snapshotsTextField.getText()));
            customVMsList.addAll(rental.getCustomVMs().getCustomVMsManager().getVMList());
            PopupWindows.information("Custom VM has been added.");
        });

        // Function checks if Custom VM exists
        // If yes, delete this VM
        deleteButton.setOnAction(event -> {
            if (rental.checkVM(Integer.parseInt(VMIDTextField.getText()), "CVM")) {
                customVMsList.clear();
                rental.getCustomVMs().deleteCustomVM(Integer.parseInt(VMIDTextField.getText()));
                customVMsList.addAll(rental.getCustomVMs().getCustomVMsManager().getVMList());
            } else {
                PopupWindows.warning("There is not that Custom VM to delete!");
            }
        });

    }

}
