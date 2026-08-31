package librarymanagementsystem.models;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book implements Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private long ID;
    @Column(name ="isbn")
    private String isbn;
    @Column(name ="title")
    private String title;
    @Column(name ="author")
    private String author;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Book() {
    }

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.user = null;
    }
    @Override
    public long getID() { return ID; }

    @Override
    public User getUser() { return user; }

    public String getISBN() {
            return isbn;
    }
    @Override
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }


    public void setID(long ID){ this.ID = ID; }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    @Override
    public void setUser(User user) { this.user = user; }


    @Override
    public String toString() {
        return "Book{" +
                "ID=" + ID +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}