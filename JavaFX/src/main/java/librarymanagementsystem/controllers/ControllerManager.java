package librarymanagementsystem.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import librarymanagementsystem.HelloApplication;
import librarymanagementsystem.db.SQLManager;
import librarymanagementsystem.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerManager {
    SQLManager sqlManager = new SQLManager();
    ArrayList queryResultList = new ArrayList<>();
    private static final Logger log = LogManager.getLogger(ControllerManager.class);
    public void loadNewStage(Button button, ActionEvent event) throws IOException {
        String buttonID = ((Control) event.getSource()).getId();
        Parent root = FXMLLoader.load(getClass().getResource("/librarymanagementsystem/" + buttonID + ".fxml"));
        Stage stage = (Stage) button.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        log.info("successfully switched to " + buttonID);
    }

    public void loadListView(String query, ListView listView) {
        ArrayList queryResultList;
        try {
            queryResultList = (ArrayList) sqlManager.getQueryResult(query);
            ObservableList observableList = FXCollections.observableArrayList(queryResultList);
            listView.getItems().clear();
            listView.setItems(observableList);
            listView.refresh();
        } catch (NullPointerException e){
            log.error("No Data available");
        }

    }
    public void loadListView(String query,String query2, ListView listView){
        try {
            queryResultList.clear();
            queryResultList.addAll(sqlManager.getQueryResult(query));
            queryResultList.addAll(sqlManager.getQueryResult(query2));
            ObservableList observableList = FXCollections.observableArrayList(queryResultList);
            listView.getItems().clear();
            listView.setItems(observableList);
            listView.refresh();
        } catch (NullPointerException e){
            log.error("No Data available");
        }

    }

    public boolean saveSelectedUser(ListView listView) {
        try {
            queryResultList = (ArrayList) sqlManager.getQueryResult("from User");
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            HelloApplication.selectedUser = (User) queryResultList.get(selectedIndex);
            log.info("This User was selected: " + HelloApplication.selectedUser);
            return true;
        } catch (IndexOutOfBoundsException exception) {
            log.error("User needs to be selected");
        }
        return false;
    }
}
