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
     * Adds a progress tracker for the user.
     * Checks if a progress tracker for the same language already exists before adding a new one.
     * 
     * @param newTracker The progress tracker to add.
     */
    public void addProgressTracker(ProgressTracker newTracker) {
        // Ensure there isn't already a tracker for the same language
        for (ProgressTracker tracker : progressTrackers) {
            if (tracker.getLanguage().equals(newTracker.getLanguage())) {
                // If a tracker for this language exists, we do not add a new one
                return;
            }
        }
        progressTrackers.add(newTracker);
    }

    /**
     * Adds a progress tracker for the given language and list of completed games (using DataKey).
     * 
     * @param language The language of the progress tracker.
     * @param gamesList The list of completed games to be tracked using DataKey.
     */
    public void addProgressTracker(String language, ArrayList<DataKey> gamesList) {
        // Check if a tracker for this language already exists
        for (ProgressTracker tracker : progressTrackers) {
            if (tracker.getLanguage().equals(language)) {
                // Tracker for this language already exists, so we won't add another
                return;
            }
        }

        // Create a new progress tracker for the language and gamesList
        ProgressTracker newTracker = new ProgressTracker(language, gamesList);
        progressTrackers.add(newTracker);
    }
}
