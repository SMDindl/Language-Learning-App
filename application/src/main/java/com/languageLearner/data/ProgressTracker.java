package com.languageLearner.data;

import java.util.ArrayList;

public class ProgressTracker {
    
    private String language;
    private ArrayList<DataKey> completedGames;
    private ArrayList<String> missedQuestions; // Store question UUIDs directly

    // Constructor
    public ProgressTracker(String language) {
        this.language = language;
        this.completedGames = new ArrayList<>();
        this.missedQuestions = new ArrayList<>();
    }

    public String getLanguage() {
        return language;
    }

    public ArrayList<DataKey> getCompletedGames() {
        return completedGames;
    }

    public ArrayList<String> getMissedQuestions() {
        return missedQuestions;
    }

    /**
     * Adds a game to the list of completed games if it's not already there.
     */
    public void addCompletedGame(DataKey game) {
        if (!completedGames.contains(game)) {
            completedGames.add(game);
        }
    }

    /**
     * Adds a question to the list of missed questions if it's not already there.
     */
    public void addMissedQuestion(String questionUUID) {
        if (!missedQuestions.contains(questionUUID)) {
            missedQuestions.add(questionUUID);
        }
    }

    /**
     * Removes a question from the list of missed questions if it exists.
     */
    public void removeMissedQuestion(String questionUUID) {
        missedQuestions.remove(questionUUID);
    }
}
