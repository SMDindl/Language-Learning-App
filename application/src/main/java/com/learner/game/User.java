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
    private HashSet<ProgressTracker> progressTrackers; // Set of progress trackers for different languages

    /**
     * Constructor to initialize User with specified UUID
     */
    public User(String email, String username, String displayName, String password, UUID uuid) {
        this.email = email;
        this.username = username;
        this.displayName = displayName;
        this.password = password;
        this.uuid = uuid;
        this.progressTrackers = new HashSet<>(); 
    }

    /**
     * Constructor to initialize User without specified UUID (UUID will be generated)
     */
    public User(String email, String username, String displayName, String password) {
        this(email, username, displayName, password, generateUUID());
    }

    /**
     * Generates a new UUID
     * @return a randomly generated UUID
     */
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

    public HashSet<ProgressTracker> getProgressTrackers() {
        return progressTrackers;
    }

    /**
     * Adds a progress tracker for a specified language
     */
    public void addProgressTracker(ProgressTracker tracker) {
        progressTrackers.add(tracker);
    }

    /**
     * Retrieves the ProgressTracker for a specific language by UUID
     * @param languageUUID the UUID of the language
     * @return the ProgressTracker for the specified language, or null if not found
     */
    public ProgressTracker getProgressTracker(UUID languageUUID) {
        for (ProgressTracker tracker : progressTrackers) {
            if (tracker.getUUID().equals(languageUUID)) {
                return tracker;
            }
        }
        return null;
    }

    /**
     * Adds a missed question to the progress tracker of the question's language
     */
    public void addMissedQuestion(Question question) {
        UUID questionLangUUID = question.getLanguageUUID();
        ProgressTracker currentProgressTracker = getProgressTracker(questionLangUUID);
        if (currentProgressTracker != null) {
            currentProgressTracker.addMissedQuestion(question);
        }
    }

    /**
     * Removes a missed question from the progress tracker of the question's language
     */
    public void removeMissedQuestion(Question question) {
        UUID questionLangUUID = question.getLanguageUUID();
        ProgressTracker currentProgressTracker = getProgressTracker(questionLangUUID);
        if (currentProgressTracker != null) {
            currentProgressTracker.removeMissedQuestion(question);
        }
    }

    /**
     * Inner nested class used for tracking user progress.
     * Each ProgressTracker is tied to a language using its UUID.
     */
    public class ProgressTracker {
        
        private final ArrayList<Question> missedQuestions; // Stores missed questions directly
        private final ArrayList<UUID> completedGames;      // Stores UUIDs of completed games
        private final UUID uuid;                           // Equal to languageUUID
        private final String languageName; 

        /**
         * Initializes ProgressTracker with the specified language UUID and name
         */
        public ProgressTracker(UUID languageUUID, String languageName) {
            this.uuid = languageUUID;
            this.languageName = languageName;
            this.missedQuestions = new ArrayList<>(); 
            this.completedGames = new ArrayList<>();  
        }

        // Getters
        public String getLanguageName() {
            return languageName;
        }

        public UUID getUUID() {
            return uuid;
        }
        
        public ArrayList<UUID> getCompletedGames() {
            return completedGames;
        }

        public ArrayList<Question> getMissedQuestions() {
            return missedQuestions;
        }
        
        /**
         * Adds a completed game by its UUID
         */
        public void addCompletedGame(UUID gameUUID) {
            if (!completedGames.contains(gameUUID)) {
                completedGames.add(gameUUID);
            }
        }
        
        /**
         * Adds a missed question to the tracker
         */
        public void addMissedQuestion(Question question) {
            if (!missedQuestions.contains(question)) {
                missedQuestions.add(question);
            }
        }
        
        /**
         * Removes a missed question from the tracker
         */
        public void removeMissedQuestion(Question question) {
            missedQuestions.remove(question);
        }

        /**
         * Retrieves a list of titles for completed games by looking up each UUID in GameManager
         * @return list of titles and difficulty levels of completed games
         */
        public ArrayList<String> getCompletedGamesTitles() {
            ArrayList<String> completedTitles = new ArrayList<>();
            GameManager gameManager = GameManager.getInstance();

            for (UUID gameUUID : completedGames) {
                Game game = gameManager.findGameByUUID(gameUUID);
                if (game != null) {
                    completedTitles.add(game.getDifficulty() + " " + game.getGameTitle());
                } else {
                    completedTitles.add("Unknown Game (UUID: " + gameUUID + ")");
                }
            }

            return completedTitles;
        }
    }
}
