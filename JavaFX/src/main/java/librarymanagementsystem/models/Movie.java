package librarymanagementsystem.models;

import jakarta.persistence.*;

@Entity
@Table(name = "movies")
public class Movie implements Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private long ID;
    @Column(name ="title")
    private String title;
    @Column(name ="author")
    private String director;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Movie() {
    }

    public Movie(String title, String director) {
        this.title = title;
        this.director = director;
        this.user = null;
    }
    @Override
    public long getID() {return ID;}

    @Override
    public User getUser() { return user; }

    @Override
    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public void setID(long ID){this.ID = ID;}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
    }


    @Override
    public void setUser(User user) { this.user = user; }

    @Override
    public String toString() {
        return "Movie{" +
                "ID=" + ID +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                '}';
    }
}