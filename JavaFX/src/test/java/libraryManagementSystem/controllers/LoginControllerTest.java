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
class LoginControllerTest {

    LoginController loginController = new LoginController();


    @Start
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 560);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void labelTextShouldBeInvalidLogin(FxRobot fxRobot) {
        fxRobot.clickOn("#username").write("Adib01");
        fxRobot.clickOn("#password").write("0");
        fxRobot.clickOn("#lms");
        Assertions.assertThat(fxRobot.lookup("#wrongLogin").queryAs(Label.class)).hasText("invalid Login");
    }

    @Test
    void labelTextShouldBeUserAlreadyExists(FxRobot fxRobot) {
        fxRobot.clickOn("#signUp");
        fxRobot.clickOn("#username").write("Jonas01");
        fxRobot.clickOn("#password").write("1");
        fxRobot.clickOn("#confirmPassword").write("1");
        fxRobot.clickOn("#signUp2");
        Assertions.assertThat(fxRobot.lookup("#wrongLogin").queryAs(Label.class)).hasText("User already exists!");
    }

    @Test
    void labelTextShouldBePleaseEnterAUsername(FxRobot fxRobot) {
        fxRobot.clickOn("#signUp");
        fxRobot.clickOn("#password").write("1");
        fxRobot.clickOn("#confirmPassword").write("1");
        fxRobot.clickOn("#signUp2");
        Assertions.assertThat(fxRobot.lookup("#wrongLogin").queryAs(Label.class)).hasText("Please enter a username!");
    }

    @Test
    void thePasswordShouldNotMeetTheRequirements(FxRobot fxRobot) {
        fxRobot.clickOn("#signUp");
        fxRobot.clickOn("#username").write("Mustermann");
        fxRobot.clickOn("#password").write("1");
        fxRobot.clickOn("#confirmPassword").write("1");
        fxRobot.clickOn("#signUp2");
        Assertions.assertThat(fxRobot.lookup("#wrongLogin").queryAs(Label.class)).hasText("Password requierements:\n- at least 5 characters\n- one special character !@#$%^&*()\n- one number\n- one uppercase and lowercase letter");
    }

    @Test
    void labelTextShouldBePasswordsDoNotMatch(FxRobot fxRobot) {
        fxRobot.clickOn("#signUp");
        fxRobot.clickOn("#username").write("Mustermann");
        fxRobot.clickOn("#password").write("(Jon123Adjw");
        fxRobot.clickOn("#confirmPassword").write("(Jon123Adj");
        fxRobot.clickOn("#signUp2");
        Assertions.assertThat(fxRobot.lookup("#wrongLogin").queryAs(Label.class)).hasText("Passwords do not match!");
    }

    @Test
    void labelTextShouldBeUsernameToLong(FxRobot fxRobot) {
        fxRobot.clickOn("#signUp");
        fxRobot.clickOn("#username").write("asdjfladkfjalkfajlfkajsdlfkjsdfjaklsdfjalksdf");
        fxRobot.clickOn("#password").write("123(Aasjdk123");
        fxRobot.clickOn("#confirmPassword").write("123(Aasjdk123");
        fxRobot.clickOn("#signUp2");
        Assertions.assertThat(fxRobot.lookup("#wrongLogin").queryAs(Label.class)).hasText("Username to long");
    }
}