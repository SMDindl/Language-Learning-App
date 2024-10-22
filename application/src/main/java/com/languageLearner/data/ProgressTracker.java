package com.languageLearner.data;

import java.util.ArrayList;

public class ProgressTracker {

    private String language;
    private ArrayList<DataKey> completedGames;

    // Constructor
    public ProgressTracker(String language, ArrayList<DataKey> completedGames) {
        this.language = language;
        this.completedGames = completedGames;
    }

    // // Method to update the progress for the current tracker (stub, you can define the logic)
    // public void updateProgress() {
    //     // Implement logic to update progress for the tracker
    // }

    // // Method to update the list of completed games
    // public void updateTrackerList() {
    //     // Logic to update the tracker list
    // }


    // Method to add a completed game to the list
    public void addCompletedGame(DataKey key) {
        if (!completedGames.contains(key)) {
            completedGames.add(key);
        }
    }

    // Method to retrieve the list of completed games
    public ArrayList<DataKey> getCompletedGames() {
        return completedGames;
    }

    // Getters
    public String getLanguage() {
        return language;
    }
}

