import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;


public class MainApp extends Application {

    // Load first scene of application
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/Menu.fxml")));

        // Set title of application's window
        stage.setTitle("Online VM");
        // Set size of application's window
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }

    // Start application
    public static void main(String[] args) {
        launch();
    }

}
