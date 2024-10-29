package com.languageLearner.app;

import java.util.UUID;

/**
 * Singleton class to manage the application's current game and user state.
 */
public class AppFacade {

    // Singleton instance
    private static AppFacade instance;

    // All of these crossover with Game
    private String currentLanguage;
    private String currentGameDifficulty;
    private String currentGameTitle;
    private UUID currentGameUUID; 

    private User currentUser;

    // Private constructor to prevent instantiation
    private AppFacade() {}

    // Public method to provide access to the singleton instance
    public static AppFacade getInstance() {
        if (instance == null) {
            instance = new AppFacade();
        }
        return instance;
    }

    // Setters
    public void setCurrentLanguage(String lang) {
        this.currentLanguage = lang;
    }

    public void setCurrentGame(String title, String difficulty, UUID uuid) {
        this.currentGameTitle = title;
        this.currentGameDifficulty = difficulty;
        this.currentGameUUID = uuid;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    // Getters
    public String getCurrentLanguage() {
        return currentLanguage;
    }

    public String getCurrentGameTitle() {
        return currentGameTitle;
    }

    public String getCurrentGameDifficulty() {
        return currentGameDifficulty;
    }

    public UUID getCurrentGameUUID() {
        return currentGameUUID;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    // Clear methods
    public void clearCurrentGame() {
        this.currentGameTitle = null;
        this.currentGameDifficulty = null;
        this.currentGameUUID = null;
    }

    public void clearCurrentUser() {
        this.currentUser = null;
    }
}
