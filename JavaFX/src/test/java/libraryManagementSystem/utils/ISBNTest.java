package librarymanagementsystem.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ISBNTest {

    @Test
    void isbnShouldBeValid() {
        String isbn = "0306406152";
        assertTrue(ISBN.isbnRequirements(isbn));
    }
    @Test
    void isbnShouldBeInValid() {
        String isbn = "8147852369";
        assertFalse(ISBN.isbnRequirements(isbn));
    }

    @Test
    void thirteenDigitIsbnShouldBeValid() {
        String isbn = "9780300189698";
        assertTrue(ISBN.isbnRequirements(isbn));
    }
    @Test
    void thirteenDigitIsbnShouldBeInValid() {
        String isbn = "9999999999999";
        assertFalse(ISBN.isbnRequirements(isbn));
    }

}
