package com.languageLearner.app;

import com.languageLearner.data.DataConstants;
import com.languageLearner.data.DataKey;
import com.languageLearner.data.DataLoader;
import com.languageLearner.data.User;
import com.languageLearner.data.UserList;

// Implemented facade for login/signup/logout + stubs
public class LanguageLearningApplication extends DataConstants {

    private User currentUser;
    private static LanguageLearningApplication instance;

    // Private constructor to implement singleton pattern
    private LanguageLearningApplication() {}

    // Get the instance of the facade
    public static LanguageLearningApplication getInstance() {
        if (instance == null) {
            instance = new LanguageLearningApplication();
        }
        return instance;
    }

    // Load users and game data when the application starts
    public void load() {
        new DataLoader().loadGameData();  
        new DataLoader().loadUsers();   
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

    // Check if a user is currently logged in
    public boolean isLoggedIn() {
        return currentUser != null;
    }

    // Logout current user
    public void logout() {
        this.currentUser = null;
    }

    // Logic to start a game based on datakey, can be extended when new games are added
    public void startGame(DataKey dataKey) {
        String gameType = dataKey.getGameType();

        switch (gameType) {
            case DataConstants.COLORS_GAME:
                ColorsGame colorsGame = new ColorsGame();
                colorsGame.startGame();
                break;
            case DataConstants.ALPHABET_GAME:
                AlphabetGame alphabetGame = new AlphabetGame();
                alphabetGame.startGame();
                break;
            case DataConstants.NUMBERS_GAME:
                NumbersGame numbersGame = new NumbersGame();
                numbersGame.startGame();
                break;
            case DataConstants.STORIES_GAME:
                StoriesGame storiesGame = new StoriesGame();
                storiesGame.startGame();
                break;
            default:
                // Invalid Game Type or null
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    // // STUB: Update the user's preferred language
    // public void updateLanguage(String language) {
    //     // STUB: Implement logic to update the user's preferred language
    // }

    // // STUB: Update the user's selected difficulty
    // public void updateDifficulty(String difficulty) {
    //     // STUB: Implement logic to update the user's selected difficulty
    // }

    // // STUB: Update the game type the user is playing
    // public void updateGameType(String gameType) {
    //     // STUB: Implement logic to update the game type
    // }

}
