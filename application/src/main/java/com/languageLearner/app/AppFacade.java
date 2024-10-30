package com.languageLearner.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

import com.languageLearner.game.Game;
import com.languageLearner.game.GameData;

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

    /**
     * Pick language by String
     * @param lang
     */
    public void pickLanguage(String lang) {
        HashMap<UUID, String> languages = gameData.getLanguages();
        Collection<String> languageNames = languages.values();

        for(String l : languageNames) {
            if(l.equalsIgnoreCase(lang)) {
                setCurrentLanguage(lang);
            }
        }
    }

    /**
     * Pick language by UUID
     * @param uuid
     */
    public void pickLanguage(UUID uuid) {
        HashMap<UUID, String> languages = gameData.getLanguages();
        
        if(languages.containsKey(uuid)) {
            String language = languages.get(uuid);
            setCurrentLanguage(language);
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
        HashMap<Game, UUID> games = gameData.getGamesWithUUIDs();
        Collection<String> gameNames = games.;

        if(currentGameDifficulty != currentGameDifficulty.get) {
            for(String d : game) {
                if(d.equalsIgnoreCase(difficulty)) {

                    setCurrentDifficulty(difficulty);

                } else {
                    System.out.println("Difficulty fail / not set"); // DEBUG STATEMENT
                }
            }
        } else {
            System.out.println("Diff not set"); // DEBUG STATEMENT
        }
    }

    // Setters
    public void setCurrentLanguage(String lang) {
        this.currentLanguage = lang;
    }

    public void setCurrentDifficulty(String difficulty) {
        this.currentGameDifficulty = difficulty;
    }

    // public void setCurrentGame(String title, String difficulty, UUID uuid) {
    //     this.currentGameTitle = title;
    //     this.currentGameDifficulty = difficulty;
    //     this.currentGameUUID = uuid;
    // }

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
