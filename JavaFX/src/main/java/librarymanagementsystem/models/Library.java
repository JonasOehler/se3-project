package librarymanagementsystem.models;

import librarymanagementsystem.HelloApplication;
import librarymanagementsystem.db.HQLManager;
import librarymanagementsystem.db.SQLManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;

public class Library {

    private static final Logger log = LogManager.getLogger(Library.class);

    public Library() {
    }

    SQLManager sqlManager = new SQLManager();
    ArrayList<Book> bookList = (ArrayList) sqlManager.getQueryResult("from Book");
    ArrayList<Movie> movieList = (ArrayList) sqlManager.getQueryResult("from Movie");
    ArrayList<Media> mediaList = new ArrayList();
    HQLManager HQLManager = new HQLManager();

    public void addBook(String isbn, String title, String author) {
        Book bookToAdd = MediaFactory.getInstance(isbn, title, author);
        HQLManager.persistMedia(bookToAdd);
    }

    public void updateBook(int selectedIndex, String isbn, String title, String author) {
        try {
            Book bookToBeUpdated = bookList.get(selectedIndex);
            log.debug("selected: " + bookToBeUpdated);
            bookToBeUpdated.setIsbn(isbn);
            bookToBeUpdated.setTitle(title);
            bookToBeUpdated.setAuthor(author);
            HQLManager.updateMediaInDb(bookToBeUpdated);

        } catch (IndexOutOfBoundsException exception) {
            log.error("Book needs to be selected");
        }
    }

    public void deleteBook(int selectedIndex) {
        try {
            Book bookToBeDeleted = bookList.get(selectedIndex);
            log.debug("selected: " + bookToBeDeleted);
            HQLManager.deleteMediaFromDb(MediaType.BOOK, bookToBeDeleted.getID());

        } catch (IndexOutOfBoundsException exception) {
            log.error("Book needs to be selected");
        }

    }

    public void addMovie(String title, String director) {
        Movie movieToAdd = MediaFactory.getInstance(title, director);
        HQLManager.persistMedia(movieToAdd);
    }

    public void updateMovie(int selectedIndex, String title, String director) {
        try {
            Movie movieToBeUpdated = movieList.get(selectedIndex);
            log.debug("selected: " + movieToBeUpdated);
            movieToBeUpdated.setTitle(title);
            movieToBeUpdated.setDirector(director);
            HQLManager.updateMediaInDb(movieToBeUpdated);

        } catch (IndexOutOfBoundsException exception) {
            log.error("Movie needs to be selected");
        }
    }

    public void deleteMovie(int selectedIndex) {
        try {
            Movie movieToBeDeleted = movieList.get(selectedIndex);
            log.debug("selected: " + movieToBeDeleted);
            HQLManager.deleteMediaFromDb(MediaType.MOVIE, movieToBeDeleted.getID());

        } catch (IndexOutOfBoundsException exception) {
            log.error("Movie needs to be selected");
        }

    }

    public void issueMedia(int selectedIndex, LocalDate issueDate, LocalDate returnDate) {
        mediaList.clear();
        mediaList.addAll(sqlManager.getQueryResult("FROM Book a WHERE a.user IS NULL"));
        mediaList.addAll(sqlManager.getQueryResult("FROM Movie a WHERE a.user IS NULL"));
        try {
            Media mediaToBeIssued = mediaList.get(selectedIndex);
            long mediaId = mediaToBeIssued.getID();
            long userId = HelloApplication.selectedUser.getId();
            sqlManager.insertQuery(mediaToBeIssued, mediaId, userId, issueDate, returnDate);
            mediaToBeIssued.setUser(HelloApplication.selectedUser);
            HQLManager.updateMediaInDb(mediaToBeIssued);
        } catch (IndexOutOfBoundsException exception) {
            log.error("Media needs to be selected");
        }
    }

    public void returnMedia(int selectedIndex){
        mediaList.clear();
        try {
            long userId = HelloApplication.selectedUser.getId();
            mediaList.addAll(sqlManager.getQueryResult("SELECT b FROM Book b JOIN b.user u WHERE u.id =" + Long.toString(userId)));
            mediaList.addAll(sqlManager.getQueryResult("SELECT b FROM Movie b JOIN b.user u WHERE u.id =" + Long.toString(userId)));
            Media mediaToBeReturned = mediaList.get(selectedIndex);
            long mediaId = mediaToBeReturned.getID();
            sqlManager.updateQuery(mediaToBeReturned, userId, mediaId);
            mediaToBeReturned.setUser(null);
            HQLManager.updateMediaInDb(mediaToBeReturned);
        } catch (IndexOutOfBoundsException exception){
            log.error("Media needs to be selected");
        }

    }
}
