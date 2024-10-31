package com.languageLearner.game;

import java.util.UUID;

import com.languageLearner.app.AppFacade;

public class Facade {

    // Singleton instance
    private static AppFacade instance;

    // All of these crossover with Game (To keep track and manage current game)
    private UUID currentLanguageUUID;
    private String currentDiffuclty;
    private UUID currentGameUUID;

    private Language currentLanguage;
    private Game currentGame;

    private User currentUser;

    public void pickLanguage(UUID languageUUID) {
        
    }

    public void pickDifficulty(int difficultyID) {
        switch(difficultyID) {
            case 1:
                
                break;
            case 2:

                break;
            case 3:
                
                break;
        }
    }

    public void pickGame(UUID languageUUID) {
        
    }


    
}
