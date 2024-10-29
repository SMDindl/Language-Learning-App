package com.languageLearner.data;

import java.util.ArrayList;
import java.util.UUID;

/**
 * User class representing a user in the language learning app.
 */
public class User {

    private UUID id;
    private String username;
    private String email;
    private String displayName;
    private String password;
    private ArrayList<ProgressTracker> progressTrackers;

    // Constructor
    public User(String email, String username, String displayName, String password, UUID id) {
        this.email = email;
        this.username = username;
        this.displayName = displayName;
        this.password = password;
        this.id = id;
        this.progressTrackers = new ArrayList<>(); // Initialize the progress trackers
    }

    // Constructor for creating a user without a specified UUID (generates one)
    public User(String email, String username, String displayName, String password) {
        this(email, username, displayName, password, generateUUID());
    }

    // Generates a user ID.
    public static UUID generateUUID() {
        return UUID.randomUUID();
    }

    // Getters for user properties
    public UUID getUuid() {
        return id;
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

    public ArrayList<ProgressTracker> getProgressTrackers() {
        return progressTrackers;
    }

    // Adds a new ProgressTracker to the user's list
    public void addProgressTracker(ProgressTracker tracker) {
        for (ProgressTracker existingTracker : progressTrackers) {
            if (existingTracker.getLanguage().equals(tracker.getLanguage())) {
                return; // Tracker for this language already exists
            }
        }
        progressTrackers.add(tracker); // Add new tracker if it doesn't exist
    }

    /**
     * Retrieves the ProgressTracker for a specific language.
     * If the tracker doesn't exist for that language, it creates a new one.
     *
     * @param language The language for which to retrieve the ProgressTracker.
     * @return The ProgressTracker for the specified language.
     */
    public ProgressTracker getProgressTrackerByLanguage(String language) {
        for (ProgressTracker tracker : progressTrackers) {
            if (tracker.getLanguage().equals(language)) {
                return tracker;
            }
        }
        // Create and add a new tracker if it doesn't exist
        ProgressTracker newTracker = ProgressTracker.getInstance();
        progressTrackers.add(newTracker);
        return newTracker;
    }

    /**
     * Tracks a missed question for a specific language.
     *
     * @param language The language for the missed question.
     * @param question The missed question.
     */
    public void trackMissedQuestion(String language, Question question) {
        ProgressTracker tracker = getProgressTrackerByLanguage(language);
        tracker.addMissedQuestion(question);
    }

    /**
     * Untracks a missed question for a specific language.
     *
     * @param language The language for the missed question.
     * @param question The question to untrack.
     */
    public void untrackMissedQuestion(DataKey key, Question question) {
        ProgressTracker tracker = getProgressTrackerByLanguage(key.getLanguage());
        tracker.removeMissedQuestion(question);
    }

    /**
     * Adds a completed game for a specific language.
     *
     * @param language The language of the completed game.
     * @param game The DataKey representing the completed game.
     */
    public void addCompletedGame(DataKey game) {
        ProgressTracker tracker = getProgressTrackerByLanguage(game.getLanguage());
        tracker.addCompletedGame(game);
    }

    /**
     * Displays progress for a specific language.
     *
     * @param language The language for which to display progress.
     */
    public void displayProgress(String language) {
        ProgressTracker tracker = getProgressTrackerByLanguage(language);
        System.out.println("\n=== Progress Overview for " + displayName + " ===");
        System.out.println("Language: " + language);
        System.out.println("Completed games: " + tracker.getCompletedGames().size());
        System.out.println("Missed questions: " + tracker.getMissedQuestions().size());
    }
}
