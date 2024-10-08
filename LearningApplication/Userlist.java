import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserList {
    private static UserList instance;
    private List<User> users;

    // Private constructor to enforce singleton pattern
    private UserList() {
        users = new ArrayList<>(); // Initialize the users list
    }

    // Method to get the single instance of UserList
    public static UserList getInstance() {
        if (instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    // Method to retrieve all users
    public List<User> getUsers() {
        return users; // Returns the list of users
    }

    // Method to populate users from a data source (you can implement this as needed)
    public void populateUsers() {
        // Read user data from a JSON file and populate the users list
    }

    // Method to create a new user account
    public void createUserAccount(String email, String username, String displayName, String password) {
        User newUser = new User(email, username, displayName, password); // Automatically generates UUID
        addUser(newUser); // Add the new user to the list
    }

    // Method to check if a user exists
    public boolean haveUser(User user) {
        return users.contains(user);
    }

    // Method to add a user to the list
    public void addUser(User user) {
        if (!haveUser(user)) {
            users.add(user); // Add user if they are not already in the list
        }
    }

    // Method to retrieve a user by username
    public User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user; // Return the user if the username matches
            }
        }
        return null; // Return null if no user is found
    }
}
