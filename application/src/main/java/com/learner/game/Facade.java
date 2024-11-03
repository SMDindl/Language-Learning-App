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
    // No instance of question / questions needed, although a questions HashMap is stored in gameManager, the Game class will pull questions itself

    // Public method to get the singleton instance
    public static Facade getInstance() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }

    public void showLanguages() {

    }


    public void pickLanguage(UUID languageUUID) {
        
    }

    public void showDifficulties() {

    }

    public void pickDifficulty(int choice) {
        switch(choice) {
            case 1:
                
                break;
            case 2:

                break;
            case 3:
                
                break;
        }
    }

    public void showGames() {

    }

    public void pickGame(UUID languageUUID) {
        
    }


    
}
