import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class MenuController {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button basicVMsButton;
    @FXML
    private Button customVMsButton;
    @FXML
    private Button reservationsButton;
    @FXML
    private Button normalCustomersButton;
    @FXML
    private Button studentsButton;
    @FXML
    private Button companyCustomersButton;

    // Default function to initialize scene
    @FXML
    public void initialize() {
        // Button used to switch scene
        basicVMsButton.setOnAction(new EventHandler<>() {

            @Override
            public void handle(ActionEvent e) {
                try {
                    basicVMsButton.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/BasicVMs.fxml"))));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        // Button used to switch scene
        customVMsButton.setOnAction(new EventHandler<>() {

            @Override
            public void handle(ActionEvent e) {
                try {
                    customVMsButton.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/CustomVMs.fxml"))));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        // Button used to switch scene
        reservationsButton.setOnAction(new EventHandler<>() {

            @Override
            public void handle(ActionEvent e) {
                try {
                    reservationsButton.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/Reservations.fxml"))));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        // Button used to switch scene
        normalCustomersButton.setOnAction(new EventHandler<>() {

            @Override
            public void handle(ActionEvent e) {
                try {
                    normalCustomersButton.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/NormalCustomers.fxml"))));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        // Button used to switch scene
        studentsButton.setOnAction(new EventHandler<>() {

            @Override
            public void handle(ActionEvent e) {
                try {
                    studentsButton.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/Students.fxml"))));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        // Button used to switch scene
        companyCustomersButton.setOnAction(new EventHandler<>() {

            @Override
            public void handle(ActionEvent e) {
                try {
                    companyCustomersButton.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/CompanyCustomers.fxml"))));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

}
