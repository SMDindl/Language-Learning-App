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

    // Constructor for creating user without UUID (generates one)
    public User(String email, String username, String displayName, String password) {
        this(email, username, displayName, password, generateUUID());
    }

    // Stub - Validate password
    public boolean validatePassword(String password) {
        return password.length() >= 8; // Minimum length of 8 characters, could be replaced with more rules
    }

    /**
     * Generates a user ID.
     * @return A UUID representing the user ID.
     */
    public static UUID generateUUID() {
        return UUID.randomUUID();
    }

    // Getters
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

    /**
     * Adds or updates a progress tracker for the given language and list of completed games (using DataKey).
     * If a tracker for the language exists, it updates it, otherwise, it creates a new one.
     * 
     * @param language The language of the progress tracker.
     * @param gamesList The list of completed games to be tracked using DataKey.
     */
    public void addOrUpdateProgressTracker(String language, ArrayList<DataKey> gamesList) {
        // Check if a tracker for this language already exists
        for (ProgressTracker tracker : progressTrackers) {
            if (tracker.getLanguage().equals(language)) {
                // Tracker for this language exists, update the list of completed games
                for (DataKey game : gamesList) {
                    tracker.addCompletedGame(game);
                }
                return;
            }
        }

        // If no tracker exists for the language, create a new one
        ProgressTracker newTracker = new ProgressTracker(language);
        for (DataKey game : gamesList) {
            newTracker.addCompletedGame(game);
        }
        progressTrackers.add(newTracker);
    }

    /**
     * Adds or updates a progress tracker for the given language and a single completed game (using DataKey).
     * If a tracker for the language exists, it updates it, otherwise, it creates a new one.
     * 
     * @param language The language of the progress tracker.
     * @param game The completed game to add using DataKey.
     */
    public void addProgressTracker(ProgressTracker newTracker) {
        // Ensure there isn't already a tracker for the same language
        for (ProgressTracker tracker : progressTrackers) {
            if (tracker.getLanguage().equals(newTracker.getLanguage())) {
                // If a tracker for this language exists, we do not add a new one
                return;
            }
        }
        progressTrackers.add(newTracker); // Add new tracker if it doesn't exist
    }
    
}
