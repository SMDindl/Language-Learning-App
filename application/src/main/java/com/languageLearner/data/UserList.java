package com.languageLearner.data;

import java.util.ArrayList;

public class UserList {
    private static UserList instance = null;
    private ArrayList<User> users;

    // Private constructor (singleton pattern)
    private UserList() {
        users = new ArrayList<>();
    }

    // Get the instance (singleton pattern)
    public static UserList getInstance() {
        if (instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    // Get all users
    public ArrayList<User> getUsers() {
        return users;
    }

    // Add a user, with checks for uniqueness
    public boolean addUser(User newUser) {
        if (isEmailTaken(newUser.getEmail()) || isUsernameTaken(newUser.getUsername())) {
            return false; // Email or username already exists
        }
        users.add(newUser); // Add the user if the checks pass
        return true;
    }

    // Check if an email is already taken
    public boolean isEmailTaken(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true; // Email exists
            }
        }
        return false;
    }

    // Check if a username is already taken
    public boolean isUsernameTaken(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return true; // Username exists
            }
        }
        return false;
    }

    // Clear users (if needed, for testing or resetting purposes)
    public void clearUsers() {
        users.clear();
    }
}
