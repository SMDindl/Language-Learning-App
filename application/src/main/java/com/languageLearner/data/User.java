package com.languageLearner.data;

import java.util.ArrayList;
import java.util.UUID;

/**
 * User class representing a user in the language learning app.
 * Manages user details and tracks progress in various languages.
 */
public class User {

    private UUID id; // Unique identifier for the user.
    private String username; // User's chosen name.
    private String email; // User's email address.
    private String displayName; // User's display name.
    private String password; // User's password for login.
    private ArrayList<ProgressTracker> progressTrackers; // Tracks progress in different languages.

    /**
     * Creates a User with the given details and a specified UUID.
     * 
     * @param email       User's email address.
     * @param username    User's chosen username.
     * @param displayName User's display name.
     * @param password    User's password.
     * @param id          Unique identifier for the user.
     */
    public User(String email, String username, String displayName, String password, UUID id) {
        this.email = email;
        this.username = username;
        this.displayName = displayName;
        this.password = password;
        this.id = id;
        this.progressTrackers = new ArrayList<>(); // Initialize the progress trackers
    }

    /**
     * Creates a User with the given details, generating a new UUID.
     * 
     * @param email       User's email address.
     * @param username    User's chosen username.
     * @param displayName User's display name.
     * @param password    User's password.
     */
    public User(String email, String username, String displayName, String password) {
        this(email, username, displayName, password, generateUUID());
    }

    /**
     * Validates the password based on length.
     * 
     * @param password The password to validate.
     * @return true if valid, false otherwise.
     */
    public boolean validatePassword(String password) {
        return password.length() >= 8; // Minimum length of 8 characters, could be replaced with more rules
    }

    /**
     * Generates a new UUID for a user.
     * 
     * @return A newly generated UUID.
     */
    public static UUID generateUUID() {
        return UUID.randomUUID();
    }

    /**
     * Gets the UUID of the user.
     * 
     * @return The UUID.
     */
    public UUID getUuid() {
        return id;
    }

    /**
     * Gets the username of the user.
     * 
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the email of the user.
     * 
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the display name of the user.
     * 
     * @return The display name.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the password of the user.
     * 
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the list of progress trackers.
     * 
     * @return The list of progress trackers.
     */
    public ArrayList<ProgressTracker> getProgressTrackers() {
        return progressTrackers;
    }

    /**
     * Adds a progress tracker for the user.
     * Prevents duplicate trackers for the same language.
     * 
     * @param newTracker The progress tracker to add.
     */
    public void addProgressTracker(ProgressTracker newTracker) {
        for (ProgressTracker tracker : progressTrackers) {
            if (tracker.getLanguage().equals(newTracker.getLanguage())) {
                return; // If a tracker for this language exists, do not add a new one.
            }
        }
        progressTrackers.add(newTracker);
    }

    /**
     * Adds a progress tracker for the specified language and list of completed games.
     * Prevents duplicate trackers for the same language.
     * 
     * @param language The language of the progress tracker.
     * @param gamesList The list of completed games to track.
     */
    public void addProgressTracker(String language, ArrayList<DataKey> gamesList) {
        for (ProgressTracker tracker : progressTrackers) {
            if (tracker.getLanguage().equals(language)) {
                return; // If a tracker for this language exists, do not add another.
            }
        }
        ProgressTracker newTracker = new ProgressTracker(language, gamesList);
        progressTrackers.add(newTracker);
    }
}
