import java.util.ArrayList;

public class UserList {
    private static UserList instance;
    private ArrayList<User> users;

    // Private constructor to enforce singleton pattern
    private UserList() {
       
    }

    // Method to get the single instance of UserList
    public static UserList getInstance() {
        if (instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    // Method to retrieve users
    public void getUsers() {
        // Implementation to return or print users
    }

    // Method to populate users from a data source
    public void populateUsers() {
        // Implementation to populate users (e.g., from a database)
    }

    // Method to create a new user account
    public void createUserAccount(String email, String username, String displayName, String password, String uuid) {
        // Implementation to create a new user account
        
    }

    // Method to check if a user exists
    public boolean haveUser(User user) {
        return users.contains(user);

    }

    // Method to add a user to the list
    public void addUser(User user) {
        
    }

    // Method to retrieve a user by username
    public User getUser(String username) {   
        return null; 
    }
}
