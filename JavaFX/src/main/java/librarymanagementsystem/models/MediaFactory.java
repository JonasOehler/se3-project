package librarymanagementsystem.models;

public class MediaFactory {
    public static Book getInstance(String isbn, String title, String author){
        return new Book(isbn, title, author);
    }
    public static Movie getInstance(String title, String director){
        return new Movie(title, director);
    }
}
