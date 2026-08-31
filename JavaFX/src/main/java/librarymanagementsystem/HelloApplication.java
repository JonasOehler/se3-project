package librarymanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import librarymanagementsystem.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import atlantafx.base.theme.NordLight;


import java.io.IOException;

public class HelloApplication extends Application {
    public static String usernameField;
    public static String passwordField;
    public static String confpasswordField;
    public static User selectedUser;

    private static final Logger log = LogManager.getLogger(HelloApplication.class);
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Application.setUserAgentStylesheet(new NordLight().getUserAgentStylesheet());
        Scene scene = new Scene(fxmlLoader.load(), 900, 560);
        stage.setTitle("Library Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        launch();
    }
}