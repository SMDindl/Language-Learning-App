package com.languageLearner.app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

import com.languageLearner.game.Game;

public class User {

    private String username;
    private String email;
    private String displayName;
    private String password;
    private UUID uuid;
    private HashSet<ProgressTracker> progressTrackers; // LanguageUUID, Progress(tracker) mapped to one another 

    public void addProgressTracker(UUID languageUUID, String language) {

    }

    /**
     * Inner nestest class used for traking user progress
     */
    public class ProgressTracker {

        private static ArrayList<Question> missedQuestions;  // UUID of the game (that the question is apart of), the question itself (loaded by uuid)
        private static ArrayList<Game> completedGames;     // UUID of the game, the Game
        private final UUID languageUUID;
        private final String language;

        public ProgressTracker(UUID languageUUID, String language) {
            this.languageUUID = languageUUID;
            this.language = language;
        }

        // getters
        public ProgressTracker getTracker() {
            return this;
        }

        public String getLanguage() {
            return language;
        }

        public UUID getLanguageUUID() {
            return languageUUID;
        }
    
        public ArrayList<Game> getCompletedGames() {
            return completedGames;
        }

        public ArrayList<String> getCompletedGamesTitles() {
            ArrayList<String> completed = new ArrayList<>();
            for(Game game : completedGames) {
                completed.add(game.getDifficulty() + " " + game.getGameTitle());
            }
            return completed;
        }

        public ArrayList<Question> getMissedQuestions() {
            return missedQuestions;
        }
    
        // Adder methods
        public void addCompletedGame(Game game) {
            if (!completedGames.contains(game)) {
                completedGames.add(game);
            }
        }
    
        public void addMissedQuestion(Question question) {
            if (!missedQuestions.contains(question)) {
                missedQuestions.add(question);
            }
        }
    
        // Remove
        public void removeMissedQuestion(Question question) {
            missedQuestions.remove(question);
        }

    }

    
}
