package librarymanagementsystem.models;

public interface Media {
    String getTitle();
    long getID();
    User getUser();
    void setUser(User userId);
}
