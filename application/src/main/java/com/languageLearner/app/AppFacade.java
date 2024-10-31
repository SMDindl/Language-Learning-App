package com.languageLearner.app;

import java.util.ArrayList;
import java.util.UUID;

import com.languageLearner.game.Game;
import com.languageLearner.game.GameData;
import com.languageLearner.game.User;

/**
 * Singleton class to manage the application's current game and user state.
 */
public class AppFacade {

    // Singleton instance
    private static AppFacade instance;
    private GameData gameData = GameData.getInstance();

    // All of these crossover with Game (To keep track and manage current game)
    private String currentLanguage;
    private String currentGameDifficulty;
    private Game currentGame;
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

    /**
     * Pick language by String
     * @param lang
     */
    public void pickLanguage(String language) {
        if(gameData.getLanguages().contains(language)) {
            setCurrentLanguage(language);
        }
    }

    /**
     * Pick language by UUID
     * @param uuid
     */
    public void pickLanguage(UUID uuid) {
        Object language = gameData.getLanguages().get(uuid);
        if (language instanceof String string) {
            setCurrentLanguage(string);
        } else if (language instanceof ArrayList) {
            System.out.println("Not set - multiple languages found: " + language);
        }
    }

    public void pickDifficulty(String difficulty) {
        ArrayList<String> difficulties = gameData.getDifficulties();
        if(currentLanguage != null) {
            for(String d : difficulties) {
                if(d.equalsIgnoreCase(difficulty)) {

                    setCurrentDifficulty(difficulty);

                } else {
                    System.out.println("Difficulty fail / not set"); // DEBUG STATEMENT
                }
            }
        } else {
            System.out.println("Lang not set"); // DEBUG STATEMENT
        }
    }

    public void pickGame(String gameName) {
        
    }

    // Setters
    public void setCurrentLanguage(String lang) {
        this.currentLanguage = lang;
    }

    public void setCurrentDifficulty(String difficulty) {
        this.currentGameDifficulty = difficulty;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    // Getters
    public String getCurrentLanguage() {
        return currentLanguage;
    }

    public Game getCurrentGame() {
        return currentGame;
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
        this.currentGame = null;
        this.currentGameDifficulty = null;
        this.currentGameUUID = null;
    }

    public void clearCurrentUser() {
        this.currentUser = null;
    }
}
