package com.learner.game;

import java.util.UUID;

public class Facade {

    // Singleton instance
    private static Facade instance;

    // All of these crossover with Game (To keep track and manage current game)
    private UUID currentLanguageUUID;
    private String currentDiffuclty;
    private UUID currentGameUUID;

    private Language currentLanguage;
    private Game currentGame;

    private User currentUser;

    public void pickLanguage(UUID languageUUID) {
        
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

    public void pickGame(UUID languageUUID) {
        
    }


    
}
