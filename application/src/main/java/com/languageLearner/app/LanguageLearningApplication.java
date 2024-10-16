package com.languageLearner.app;

import com.languageLearner.data.User;
import com.languageLearner.data.UserList;

// Implemented facade for login/signup/logout + stubs
public class LanguageLearningApplication {

    private User currentUser;
    private static LanguageLearningApplication instance;

    // Private constructor to implement singleton pattern
    private LanguageLearningApplication() {
    }

    // Get the instance of the facade
    public static LanguageLearningApplication getInstance() {
        if (instance == null) {
            instance = new LanguageLearningApplication();
        }
        return instance;
    }

    // Load users and game data when the application starts
    public void load() {
        UserList.getInstance();  // Loading existing users
        // Additional loading logic for game data if needed
    }

    // User signup (add a new user)
    public boolean signup(String email, String username, String displayName, String password) {
        User newUser = new User(email, username, displayName, password);
        return UserList.getInstance().addUser(newUser);
    }

    // User login
    public boolean signin(String email, String password) {
        User user = UserList.getInstance().login(email, password);
        if (user != null) {
            this.currentUser = user;
            return true; // Successful login
        }
        return false; // Login failed
    }

    // Logout current user
    public void logout() {
        this.currentUser = null;
    }

    // STUB: Update the user's preferred language
    public void updateLanguage(String language) {
        // STUB: Implement logic to update the user's preferred language
    }

    // STUB: Update the user's selected difficulty
    public void updateDifficulty(String difficulty) {
        // STUB: Implement logic to update the user's selected difficulty
    }

    // STUB: Update the game type the user is playing
    public void updateGameType(String gameType) {
        // STUB: Implement logic to update the game type
    }

    // STUB: Load game data (if needed)
    private void loadGameData() {
        // STUB: Implement logic to load game data
    }

    // STUB: Load user data (if needed)
    private void loadUsers() {
        // STUB: Implement logic to load users
    }
}
