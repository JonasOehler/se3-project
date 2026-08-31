package librarymanagementsystem.controllers;

import librarymanagementsystem.HelloApplication;
import librarymanagementsystem.db.HQLManager;
import librarymanagementsystem.db.SQLManager;
import librarymanagementsystem.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class LoginManager {

    public LoginManager(ArrayList<User> users) {
        this.users = users;
    }
    static SQLManager sqlManager = new SQLManager();


    public LoginManager() {
    }

    private static final Logger log = LogManager.getLogger(LoginManager.class);
    static ArrayList<User> users = new ArrayList<>();
    HQLManager hqlManager = new HQLManager();

    public void addUser(String firstname, String lastname) throws IOException {
        User user = new User(firstname, lastname, HelloApplication.usernameField, HelloApplication.passwordField);
        hqlManager.persistUser(user);
    }

    public User userAlreadyExist(String userName) {
        LoginManager.users = (ArrayList<User>) sqlManager.getQueryResult("from User");
        log.debug("accountAlreadyExist method started - returns User object");
        try {
            Optional<User> searchedUser = Optional.ofNullable(users.parallelStream()
                    .filter(user -> user.getUsername().equals(userName))
                    .findFirst()
                    .orElse(null));

            if (searchedUser.isEmpty()) {
                log.info("User with username " + userName + " does not exist - userAlreadyExists method returns null");
                return null;

            } else {
                return searchedUser.get();
            }
        } catch (NullPointerException e){
            log.error("UserList is empty");
        }
        return null;
    }

}
