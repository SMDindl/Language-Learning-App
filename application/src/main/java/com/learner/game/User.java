package com.learner.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

import com.learner.game.questions.Question;

public class User {

    private String email;
    private String username;
    private String displayName;
    private String password;
    private UUID uuid;
    private HashSet<ProgressTracker> progressTrackers; // HashSet ensures no dupulicates

    // Constructor
    public User(String email, String username, String displayName, String password, UUID uuid) {
        this.email = email;
        this.username = username;
        this.displayName = displayName;
        this.password = password;
        this.uuid = uuid;
        this.progressTrackers = new HashSet<>(); 
    }

    // Constructor for creating a user without a specified UUID (generates one)
    public User(String email, String username, String displayName, String password) {
        this(email, username, displayName, password, generateUUID());
    }

    // Generates a uuid
    public static UUID generateUUID() {
        return UUID.randomUUID();
    }

    // Getters
    public UUID getUUID() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPassword() {
        return password;
    }

    // ProgressTracker management
    public void addProgressTracker(Language language) {
        progressTrackers.add(new ProgressTracker(language.getUUID(), language.getLanguageName()));
    }

    public ProgressTracker getProgressTracker(UUID languageUUID) {
        for(ProgressTracker tracker : progressTrackers) {
            if(tracker.getUUID() == languageUUID) {
                return tracker;
            }
        }
        return null;
    }

    public void addMissedQuestion(Question question) {
        UUID questionLangUUID = question.getLanguageUUID();
        ProgressTracker currentProgressTracker = getProgressTracker(questionLangUUID);
        currentProgressTracker.addMissedQuestion(question);
    }

    public void removeMissedQuestion(Question question) {
        UUID questionLangUUID = question.getLanguageUUID();
        ProgressTracker currentProgressTracker = getProgressTracker(questionLangUUID);
        currentProgressTracker.removeMissedQuestion(question);
    }

    /**
     * Inner nestest class used for traking user progress
     * UUID of ProgressTrack is equal to languageUUID
     */
    public class ProgressTracker {

        // replace Game with game uuids
        // questions can remain question instances as sometimes these are unquie 
        private static ArrayList<Question> missedQuestions; // UUID of the game (that the question is apart of), the question itself (loaded by uuid)
        private static ArrayList<Game> completedGames;      // UUID of the game, the Game
        private final UUID uuid;            // = to languageUUID
        private final String languageName; 

        public ProgressTracker(UUID languageUUID, String languageName) {
            this.uuid = languageUUID;
            this.languageName = languageName;
        }

        // getters
        public ProgressTracker getTracker() {
            return this;
        }

        public String getLanguageName() {
            return languageName;
        }

        public UUID getUUID() {
            return uuid;
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
