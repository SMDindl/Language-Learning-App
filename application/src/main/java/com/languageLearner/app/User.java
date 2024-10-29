package com.languageLearner.app;

import java.util.HashMap;
import java.util.UUID;

public class User {

    private String username;
    private String email;
    private String displayName;
    private String password;
    private UUID uuid;
    private HashMap<UUID, Progress> progressMap; // LanguageUUID, Progress(tracker) mapped to one another 

    /**
     * Inner nestest class used for traking user progress
     */
    public class Progress {

        private static HashMap<UUID, Question> missedQuestions; // UUID of question, the question itself
        private static HashMap<UUID, String> completedGames; // UUID of the game, the title of the game

        public Progress(String langauge) {
            
        }

    }

    
}
