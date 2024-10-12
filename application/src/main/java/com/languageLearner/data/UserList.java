package com.languageLearner.data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Populate users will be placed in DataLoader
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
        JSONParser parser = new JSONParser();
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject jsonData = (JSONObject) parser.parse(content);
            JSONArray usersArray = (JSONArray) jsonData.get("users");

            for (Object obj : usersArray) {
                JSONObject userObj = (JSONObject) obj;

                // Extract user information from JSON
                String uuid = (String) userObj.getOrDefault("uuid", UUID.randomUUID().toString());
                String username = (String) userObj.get("username");
                String email = (String) userObj.get("email");
                String displayName = (String) userObj.get("displayName");
                String password = (String) userObj.get("password");

                // Create a new User object
                User user = new User(email, username, displayName, password);

                // Populate progress trackers if present
                JSONArray progressTrackers = (JSONArray) userObj.get("progressTrackers");
                if (progressTrackers != null) {
                    for (Object trackerObj : progressTrackers) {
                        JSONObject tracker = (JSONObject) trackerObj;
                        String language = (String) tracker.getOrDefault("language", "");
                        JSONArray completedGames = (JSONArray) tracker.get("completedGames");

                        List<String> gamesList = new ArrayList<>();
                        if (completedGames != null) {
                            for (Object gameObj : completedGames) {
                                gamesList.add((String) gameObj);
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
