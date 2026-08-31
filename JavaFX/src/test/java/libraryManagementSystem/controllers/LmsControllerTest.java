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
class LmsControllerTest {
    LmsController lmsController = new LmsController();

    @Start
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("manageBooks.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setScene(scene);
        stage.show();
    }

    //Manage books tests
    @Test
    void clickOnAddBookButtonLabelTextShouldBePleaseEnterAIsbn(FxRobot fxRobot) {
        fxRobot.clickOn("#title").write("testTitle");
        fxRobot.clickOn("#author").write("testAuthor");
        fxRobot.clickOn("#addMediaButton");
        Assertions.assertThat(fxRobot.lookup("#label").queryAs(Label.class)).hasText("Please enter a isbn");
    }

    @Test
    void clickOnAddBookButtonLabelTextShouldBePleaseEnterATitle(FxRobot fxRobot) {
        fxRobot.clickOn("#isbn").write("testIsbn");
        fxRobot.clickOn("#author").write("testAuthor");
        fxRobot.clickOn("#addMediaButton");
        Assertions.assertThat(fxRobot.lookup("#label").queryAs(Label.class)).hasText("Please enter a title");
    }

    @Test
    void clickOnAddBookButtonLabelTextShouldBePleaseEnterAnAuthor(FxRobot fxRobot) {
        fxRobot.clickOn("#isbn").write("testIsbn");
        fxRobot.clickOn("#title").write("testTitle");
        fxRobot.clickOn("#addMediaButton");
        Assertions.assertThat(fxRobot.lookup("#label").queryAs(Label.class)).hasText("Please enter an author");
    }

    @Test
    void clickOnAddBookButtonLabelTextShouldBePleaseEnterAValidIsbn(FxRobot fxRobot) {
        fxRobot.clickOn("#isbn").write("1860920496");
        fxRobot.clickOn("#title").write("testTitle");
        fxRobot.clickOn("#author").write("testAuthor");
        fxRobot.clickOn("#addMediaButton");
        Assertions.assertThat(fxRobot.lookup("#label").queryAs(Label.class)).hasText("Please enter a valid isbn (10: 1860920497 or 13: 9783596705818)");
    }

    @Test
    void clickOnAddBookButtonLabelTextShouldBeAuthorOrTitleIsToLong(FxRobot fxRobot){
        fxRobot.clickOn("#isbn").write("1860920497");
        fxRobot.clickOn("#title").write("testTitle");
        fxRobot.clickOn("#author").write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        fxRobot.clickOn("#addMediaButton");
        Assertions.assertThat(fxRobot.lookup("#label").queryAs(Label.class)).hasText("Title or author is to long");
    }

    @Test
    void clickOnUpdateBookButtonLabelTextShouldBePleaseEnterAIsbn(FxRobot fxRobot) {
        fxRobot.clickOn("#title").write("testTitle");
        fxRobot.clickOn("#author").write("testAuthor");
        fxRobot.clickOn("#updateMediaButton");
        Assertions.assertThat(fxRobot.lookup("#label").queryAs(Label.class)).hasText("Please enter a isbn");
    }

    @Test
    void clickOnUpdateBookButtonLabelTextShouldBePleaseEnterATitle(FxRobot fxRobot) {
        fxRobot.clickOn("#isbn").write("testIsbn");
        fxRobot.clickOn("#author").write("testAuthor");
        fxRobot.clickOn("#updateMediaButton");
        Assertions.assertThat(fxRobot.lookup("#label").queryAs(Label.class)).hasText("Please enter a title");
    }

    @Test
    void clickOnUpdateBookButtonLabelTextShouldBePleaseEnterAnAuthor(FxRobot fxRobot) {
        fxRobot.clickOn("#isbn").write("testIsbn");
        fxRobot.clickOn("#title").write("testTitle");
        fxRobot.clickOn("#updateMediaButton");
        Assertions.assertThat(fxRobot.lookup("#label").queryAs(Label.class)).hasText("Please enter an author");
    }

    @Test
    void clickOnUpdateBookButtonLabelTextShouldBePleaseEnterAValidIsbn(FxRobot fxRobot) {
        fxRobot.clickOn("#isbn").write("1860920496");
        fxRobot.clickOn("#title").write("testTitle");
        fxRobot.clickOn("#author").write("testAuthor");
        fxRobot.clickOn("#updateMediaButton");
        Assertions.assertThat(fxRobot.lookup("#label").queryAs(Label.class)).hasText("Please enter a valid isbn (10: 1860920497 or 13: 9783596705818)");
    }

    @Test
    void clickOnUpdateBookButtonLabelTextShouldBeTitleOrAuthorIsToLong(FxRobot fxRobot) {
        fxRobot.clickOn("#isbn").write("1860920497");
        fxRobot.clickOn("#title").write("testTitle");
        fxRobot.clickOn("#author").write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        fxRobot.clickOn("#updateMediaButton");
        Assertions.assertThat(fxRobot.lookup("#label").queryAs(Label.class)).hasText("Title or author is to long");
    }


    //Manage movies tests
    @Test
    void clickOnAddMovieButtonLabelTextShouldBePleaseEnterATitle(FxRobot fxRobot) {
        fxRobot.clickOn("#manageMovies");
        fxRobot.clickOn("#director").write("testDirector");
        fxRobot.clickOn("#addMediaButton");
        Assertions.assertThat(fxRobot.lookup("#label").queryAs(Label.class)).hasText("Please enter a title");
    }

    @Test
    void clickOnAddMovieButtonLabelTextShouldBePleaseEnterADirector(FxRobot fxRobot) {
        fxRobot.clickOn("#manageMovies");
        fxRobot.clickOn("#title").write("testTitle");
        fxRobot.clickOn("#addMediaButton");
        Assertions.assertThat(fxRobot.lookup("#label").queryAs(Label.class)).hasText("Please enter a director");
    }

    @Test
    void clickOnAddMovieButtonLabelTextShouldBeTitleOrDirectorToLong(FxRobot fxRobot){
        fxRobot.clickOn("#manageMovies");
        fxRobot.clickOn("#title").write("testTitle");
        fxRobot.clickOn("#director").write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        fxRobot.clickOn("#addMediaButton");
        Assertions.assertThat(fxRobot.lookup("#label").queryAs(Label.class)).hasText("Title or director is to long");
    }

    @Test
    void clickOnUpdateMovieButtonLabelTextShouldBePleaseEnterATitle(FxRobot fxRobot) {
        fxRobot.clickOn("#manageMovies");
        fxRobot.clickOn("#director").write("testDirector");
        fxRobot.clickOn("#updateMediaButton");
        Assertions.assertThat(fxRobot.lookup("#label").queryAs(Label.class)).hasText("Please enter a title");
    }

    @Test
    void clickOnUpdateMovieButtonLabelTextShouldBePleaseEnterADirector(FxRobot fxRobot) {
        fxRobot.clickOn("#manageMovies");
        fxRobot.clickOn("#title").write("testTitle");
        fxRobot.clickOn("#updateMediaButton");
        Assertions.assertThat(fxRobot.lookup("#label").queryAs(Label.class)).hasText("Please enter a director");
    }

    @Test
    void clickOnUpdateMovieButtonLabelTextShouldBeTitleOrDirectorToLong(FxRobot fxRobot){
        fxRobot.clickOn("#manageMovies");
        fxRobot.clickOn("#title").write("testTitle");
        fxRobot.clickOn("#director").write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        fxRobot.clickOn("#updateMediaButton");
        Assertions.assertThat(fxRobot.lookup("#label").queryAs(Label.class)).hasText("Title or director is to long");
    }
}