package librarymanagementsystem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import librarymanagementsystem.HelloApplication;
import librarymanagementsystem.models.User;
import librarymanagementsystem.utils.Password;

import java.io.IOException;


public class LoginController {

    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    TextField username;
    @FXML
    TextField password;
    @FXML
    private TextField confirmPassword;
    @FXML
    Label wrongLogin;

    ControllerManager controllerManager = new ControllerManager();
    LoginManager loginManager = new LoginManager();


    public void switchScene(ActionEvent event) throws IOException {
        controllerManager.loadNewStage((Button) event.getTarget(), event);
    }

    public void handleButtonAction(ActionEvent event, TextField textField) {
        new animatefx.animation.Shake(textField).play();
    }

    public void validateLogin(ActionEvent event) throws Exception {
        HelloApplication.usernameField = username.getText();
        HelloApplication.passwordField = password.getText();

        User userToLogin = loginManager.userAlreadyExist(HelloApplication.usernameField);
        if (userToLogin != null && userToLogin.getPassword().equals(HelloApplication.passwordField)) {
            controllerManager.loadNewStage((Button) event.getTarget(), event);
        } else {
            handleButtonAction(event, username);
            handleButtonAction(event, password);
            wrongLogin.setText("invalid Login");
        }
    }

    public void validateSignup(ActionEvent event) throws Exception {

        HelloApplication.usernameField = username.getText();
        HelloApplication.passwordField = password.getText();
        HelloApplication.confpasswordField = confirmPassword.getText();
        User userForRegister = loginManager.userAlreadyExist(HelloApplication.usernameField);

        if (userForRegister != null) {
            handleButtonAction(event, username);
            wrongLogin.setText("User already exists!");
        } else if (HelloApplication.usernameField.isEmpty()) {
            handleButtonAction(event, username);
            wrongLogin.setText("Please enter a username!");
        } else if (!Password.passwordRequirements(HelloApplication.passwordField)) {
            handleButtonAction(event, password);
            wrongLogin.setText("Password requierements:\n- at least 5 characters\n- one special character !@#$%^&*()\n- one number\n- one uppercase and lowercase letter");
        } else if (!HelloApplication.passwordField.equals(HelloApplication.confpasswordField)) {
            handleButtonAction(event, password);
            wrongLogin.setText("Passwords do not match!");
        } else if (username.getText().length() > 30) {
            wrongLogin.setText("Username to long");
        } else {
            handleButtonAction(event, password);
            handleButtonAction(event, confirmPassword);
            controllerManager.loadNewStage((Button) event.getTarget(), event);
        }
    }

    public void addUser(ActionEvent event) throws Exception {
        if (firstname.getText().length() > 30 || lastname.getText().length() > 30) {
            wrongLogin.setText("Firstname or lastname to long");
        }else{
            loginManager.addUser(firstname.getText(), lastname.getText());
            controllerManager.loadNewStage((Button) event.getTarget(), event);
        }
    }
}