package librarymanagementsystem.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import librarymanagementsystem.HelloApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
class IssueReturnControllerTest {
    IssueReturnController issueReturnController = new IssueReturnController();

    @Start
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("issueMedia2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void labelTextShouldBePleaseSelectIssueDate(FxRobot fxRobot)  {
        fxRobot.clickOn("#returnDate").write("01.06.2023");
        fxRobot.clickOn("#issueMedia2");
        Assertions.assertThat(fxRobot.lookup("#label").queryAs(Label.class)).hasText("Please select issue date");
    }

    @Test
    void labelTextShouldBePleaseSelectReturnDate(FxRobot fxRobot)  {
        fxRobot.clickOn("#issueDate").write("01.03.2023");
        fxRobot.clickOn("#issueMedia2");
        Assertions.assertThat(fxRobot.lookup("#label").queryAs(Label.class)).hasText("Please select return date");
    }

}