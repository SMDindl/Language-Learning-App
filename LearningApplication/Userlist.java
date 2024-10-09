import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserList {
    
    private static UserList instance;
    private List<User> users;

    // Private constructor to enforce singleton pattern
    private UserList() {
        users = new ArrayList<>(); // Initialize the users list
        populateUsers(); // Load users from the JSON file on initialization
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

    // Method to populate users from the JSON file
    public void populateUsers() {
        String filePath = "data/json/users.json"; // Path to the JSON file
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject jsonData = new JSONObject(content);
            JSONArray usersArray = jsonData.getJSONArray("users");

            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject userObj = usersArray.getJSONObject(i);

                // Extract user information from JSON
                String uuid = userObj.optString("uuid", UUID.randomUUID().toString());
                String username = userObj.getString("username");
                String email = userObj.getString("email");
                String displayName = userObj.getString("displayName");
                String password = userObj.getString("password");

                // Create a new User object
                User user = new User(email, username, displayName, password);
                user.setUUID(UUID.fromString(uuid));

                // Populate progress trackers if present
                JSONArray progressTrackers = userObj.optJSONArray("progressTrackers");
                if (progressTrackers != null) {
                    for (int j = 0; j < progressTrackers.length(); j++) {
                        JSONObject tracker = progressTrackers.getJSONObject(j);
                        String language = tracker.optString("language", "");
                        JSONArray completedGames = tracker.optJSONArray("completedGames");

                        List<String> gamesList = new ArrayList<>();
                        if (completedGames != null) {
                            for (int k = 0; k < completedGames.length(); k++) {
                                gamesList.add(completedGames.getString(k));
                            }
                        }

                        // Add the progress tracker to the user
                        user.addProgressTracker(language, gamesList);
                    }
                }

                // Add the user to the list
                users.add(user);
            }
        } catch (Exception e) {
            System.err.println("Error reading users from JSON file.");
            e.printStackTrace();
        }
    }

    // Method to create a new user account
    public void createUserAccount(String email, String username, String displayName, String password) {
        User newUser = new User(email, username, displayName, password); // Automatically generates UUID
        addUser(newUser); // Add the new user to the list
        DataWriter dataWriter = new DataWriter(); // Write new user to the JSON file
        dataWriter.writeUserData(username, displayName, email, password);
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
