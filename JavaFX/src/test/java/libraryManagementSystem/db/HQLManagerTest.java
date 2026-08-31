package librarymanagementsystem.db;

import librarymanagementsystem.models.Book;
import librarymanagementsystem.models.MediaType;
import librarymanagementsystem.models.Movie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HQLManagerTest {
    HQLManager hqlManager = new HQLManager();

    @Test
    void fetchedMediaShouldBeOfTypeBook() {
        assertTrue(hqlManager.getMediaFromDb(MediaType.BOOK, 22) instanceof Book);
    }

    @Test
    void fetchedMediaShouldBeOfTypeMovie() {
        assertTrue(hqlManager.getMediaFromDb(MediaType.MOVIE, 16) instanceof Movie);
    }

    @Test
    void wrongMediaIdShouldReturnNull() {
        assertNull(hqlManager.getMediaFromDb(MediaType.MOVIE, 0));
    }

    @Test
    void wrongUserIdShouldReturnNull() {
        assertNull(hqlManager.getUserFromDb(0));
    }

    @Test
    void rightUserIdShouldReturnUser() {
        assertNotNull(hqlManager.getUserFromDb(1));
    }
}