package librarymanagementsystem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import librarymanagementsystem.HelloApplication;
import librarymanagementsystem.models.Library;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IssueReturnController implements Initializable {
    @FXML
    private ListView userListView;
    @FXML
    private ListView mediaListView;
    @FXML
    private Button issueMedia2;
    @FXML
    private Button lms;
    @FXML
    private Button manageBooks;
    @FXML
    private DatePicker issueDate;
    @FXML
    private DatePicker returnDate;
    @FXML
    private Label label;

    ControllerManager controllerManager = new ControllerManager();
    Library library = new Library();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String fxmlFilePath = "/issueMedia.fxml";
        String fxmlFilePath2 = "/returnMedia.fxml";
        String fxmlFilePath3 = "/returnMedia2.fxml";
        String fxmlFilePath4 = "issueMedia2.fxml";
        String loadedFilePath = url.getFile();

        if (loadedFilePath.endsWith(fxmlFilePath) || loadedFilePath.endsWith(fxmlFilePath2)) {
            controllerManager.loadListView("FROM User", userListView);
        } else if (loadedFilePath.endsWith(fxmlFilePath3)) {
            long userId = HelloApplication.selectedUser.getId();
            controllerManager.loadListView("SELECT b FROM Book b JOIN b.user u WHERE u.id =" + Long.toString(userId), "SELECT b FROM Movie b JOIN b.user u WHERE u.id =" + Long.toString(userId), mediaListView);
        } else if (loadedFilePath.endsWith(fxmlFilePath4)){
            controllerManager.loadListView("FROM Book a WHERE a.user IS NULL","FROM Movie a WHERE a.user IS NULL", mediaListView);
        }
    }

    public void switchScene(ActionEvent event) throws IOException {
        controllerManager.loadNewStage((Button) event.getTarget(), event);
    }

    public void selectUser(ActionEvent event) throws IOException {
        if (controllerManager.saveSelectedUser(userListView)){
            controllerManager.loadNewStage((Button) event.getTarget(), event);
        }else {
            label.setText("Select a User");
        }

    }

    public void issueMedia() {
        if (issueDate.getValue() == null) {
            label.setText("Please select issue date");
        } else if (returnDate.getValue() == null) {
            label.setText("Please select return date");
        } else if (returnDate.getValue().isBefore(issueDate.getValue())) {
            label.setText("Set Return Date after Issue Date");
            System.out.println(mediaListView.getSelectionModel().getSelectedIndex());
        } else {
            library.issueMedia(mediaListView.getSelectionModel().getSelectedIndex(), issueDate.getValue(), returnDate.getValue());
            controllerManager.loadListView("FROM Book a WHERE a.user IS NULL","FROM Movie a WHERE a.user IS NULL", mediaListView);
            label.setText("");
        }

    }

    public void returnMedia() {
        library.returnMedia(mediaListView.getSelectionModel().getSelectedIndex());
        long userId = HelloApplication.selectedUser.getId();
        controllerManager.loadListView("SELECT b FROM Book b JOIN b.user u WHERE u.id =" + Long.toString(userId), "SELECT b FROM Movie b JOIN b.user u WHERE u.id =" + Long.toString(userId), mediaListView);
        label.setText("");
    }
}
