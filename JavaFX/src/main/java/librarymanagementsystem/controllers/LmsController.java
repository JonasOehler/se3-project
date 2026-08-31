package librarymanagementsystem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import librarymanagementsystem.models.Library;
import librarymanagementsystem.models.Media;
import librarymanagementsystem.utils.ISBN;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LmsController implements Initializable {
    @FXML
    private ListView<Media> mediaListView;
    @FXML
    private TextField isbn;
    @FXML
    private TextField title;
    @FXML
    private TextField author;
    @FXML
    private TextField director;
    @FXML
    private Label label;

    ControllerManager controllerManager = new ControllerManager();
    Library library = new Library();
    private static final Logger log = LogManager.getLogger(LmsController.class);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String fxmlFilePath = "/manageBooks.fxml";
        String fxmlFilePath2 = "/manageMovies.fxml";
        String fxmlFilePath3 = "/viewMedia.fxml";
        String loadedFilePath = url.getFile();

        if (loadedFilePath.endsWith(fxmlFilePath)) {
            controllerManager.loadListView("from Book", mediaListView);
        } else if (loadedFilePath.endsWith(fxmlFilePath2)) {
            controllerManager.loadListView("from Movie", mediaListView);
        } else if (loadedFilePath.endsWith(fxmlFilePath3)) {
            controllerManager.loadListView("FROM Book a WHERE a.user IS NOT NULL", "FROM Movie a WHERE a.user IS NOT NULL", mediaListView);
        } else {
            log.info("No ListView available");
        }

    }

    public void switchScene(ActionEvent event) throws IOException {
        controllerManager.loadNewStage((Button) event.getTarget(), event);
    }

    public void newBook() {
        if (isbn.getText().isEmpty()) {
            label.setText("Please enter a isbn");
        } else if (title.getText().isEmpty()) {
            label.setText("Please enter a title");
        } else if (author.getText().isEmpty()) {
            label.setText("Please enter a author");
        } else if (ISBN.isbnRequirements(isbn.getText()) == false) {
            label.setText("Please enter a valid isbn (10: 1860920497 or 13: 9783596705818)");
        } else if (title.getText().length() > 50 || author.getText().length() > 30) {
            label.setText("Title or author is to long");
        } else {
            library.addBook(isbn.getText(), title.getText(), author.getText());
            controllerManager.loadListView("from Book", mediaListView);
            label.setText("");
        }
    }

    public void updateBook() {
        if (isbn.getText().isEmpty()) {
            label.setText("Please enter a isbn");
        } else if (title.getText().isEmpty()) {
            label.setText("Please enter a title");
        } else if (author.getText().isEmpty()) {
            label.setText("Please enter a author");
        } else if (ISBN.isbnRequirements(isbn.getText()) == false) {
            label.setText("Please enter a valid isbn (10: 1860920497 or 13: 9783596705818)");
        } else if (title.getText().length() > 50 || author.getText().length() > 30) {
            label.setText("Title or author is to long");
        } else {
            library.updateBook(mediaListView.getSelectionModel().getSelectedIndex(), isbn.getText(), title.getText(), author.getText());
            controllerManager.loadListView("from Book", mediaListView);
            label.setText("");
        }
    }

    public void deleteBook() {
        library.deleteBook(mediaListView.getSelectionModel().getSelectedIndex());
        controllerManager.loadListView("from Book", mediaListView);
    }

    public void newMovie() {
        if (title.getText().isEmpty()) {
            label.setText("Please enter a title");
        } else if (director.getText().isEmpty()) {
            label.setText("Please enter a director");
        } else if (title.getText().length() > 50 || director.getText().length() > 30) {
            label.setText("Title or director is to long");
        } else {
            library.addMovie(title.getText(), director.getText());
            controllerManager.loadListView("from Movie", mediaListView);
        }
    }

    public void updateMovie() {
        if (title.getText().isEmpty()) {
            label.setText("Please enter a title");
        } else if (director.getText().isEmpty()) {
            label.setText("Please enter a director");
        } else if (title.getText().length() > 50 || director.getText().length() > 30) {
            label.setText("Title or director is to long");
        } else {
            library.updateMovie(mediaListView.getSelectionModel().getSelectedIndex(), title.getText(), director.getText());
            controllerManager.loadListView("from Movie", mediaListView);
        }
    }

    public void deleteMovie() {
        library.deleteMovie(mediaListView.getSelectionModel().getSelectedIndex());
        controllerManager.loadListView("from Movie", mediaListView);
    }

}
