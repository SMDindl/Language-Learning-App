package com.learner.game;

import java.util.UUID;

public class Facade {

    // Singleton instance of this class
    private static Facade instance;

    /**
     * GameManger instance: 
     * [holds: 
     * HashMap  of all languages, 
     * HashMaps of all games (each difficulty is held in a seperate HashMap),
     * HashMap  of all questions]
     */
    private GameManager gameManager = GameManager.getInstance();

    private Language currentLanguage; // Stores languageUUID and languageName of current language
    private Game currentGame;         // Stores gameUUID and any info pertaining game
    private User currentUser;         // Stores list of ProgressTrackers and any info of current user

    // Public method to get the singleton instance
    public static Facade getInstance() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }

    public void loadData() {

    }

    public String showLanguages() {
        return null;
    }


    public void pickLanguage(UUID languageUUID) {
        
    }

    public void showDifficulties() {

    }

    public void pickDifficulty(int choice) {
        switch(choice) {
            case 1 -> {

            }
            case 2 -> {

            }
            case 3 -> {

            }
        }
    }

    public void showGames() {

    }

    public void pickGame(UUID languageUUID) {
        
    }

    // Will we need game methods directly interconnected to facade?
    
}
