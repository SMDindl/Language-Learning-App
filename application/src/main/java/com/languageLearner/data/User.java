package com.languageLearner.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Stub for user
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

    // STUB - check if email
    public boolean checkEmailAvailability(String email) {
        return true; // Replace 
    }

    // STUB - check username
    public boolean checkUsernameAvailability(String username) {
        return true; // Replace 
    }

    // STUB - Validate password 
    public boolean validatePassword(String password) {
        return password.length() >= 8; // minimum length of 8 characters, could replace
    }

    /**
     * Generates a user ID.
     * @return A UUID representing the user ID.
     */
    public static UUID generateUUID() {
        return UUID.randomUUID();
    }

    // STUB - Create user account
    // This method may not be needed
    public void createUserAccount(String email, String username, String displayName, String password, String uuid) {
        
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

    // STUB - add a progress tracker to list of progressTrackers
    // will need to make sure the tracker for that language is not
    // already in the list
    public void addProgressTracker(ProgressTracker newTracker) { // needs added to UML
        progressTrackers.add(newTracker);
    }

    public void addProgressTracker(String language, List<String> gamesList) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
