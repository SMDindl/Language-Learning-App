package com.languageLearner.data;

import java.util.ArrayList;

public class ProgressTracker {

    private String language;
    private ArrayList<DataKey> completedGames;
    private ArrayList<MissedQuestion> missedQuestions; // Multiple choice or t/f
    private ArrayList<MissedQuestion> missedMatching; 
    private ArrayList<MissedQuestion> missedFITB;

    // Constructor
    public ProgressTracker(String language) {
        this.language = language;
        this.completedGames = new ArrayList<>();
        this.missedQuestions = new ArrayList<>();
        this.missedMatching = new ArrayList<>();
        this.missedFITB = new ArrayList<>();
    }

    // Methods to add missed questions
    public void addMissedQuestion(MissedQuestion question) {
        missedQuestions.add(question);
    }

    public void addMissedMatching(MissedQuestion question) {
        missedMatching.add(question);
    }

    public void addMissedFITB(MissedQuestion question) {
        missedFITB.add(question);
    }

    // Methods to remove missed questions
    public void removeMissedQuestion(MissedQuestion question) {
        missedQuestions.remove(question);
    }

    public void removeMissedMatching(MissedQuestion question) {
        missedMatching.remove(question);
    }

    public void removeMissedFITB(MissedQuestion question) {
        missedFITB.remove(question);
    }

    // Retrieve all missed questions of each type
    public ArrayList<MissedQuestion> getMissedQuestions() {
        return missedQuestions;
    }

    public ArrayList<MissedQuestion> getMissedMatching() {
        return missedMatching;
    }

    public ArrayList<MissedQuestion> getMissedFITB() {
        return missedFITB;
    }

    // Getter for language
    public String getLanguage() {
        return language;
    }

    // Method to add a completed game
    public void addCompletedGame(DataKey key) {
        if (!completedGames.contains(key)) {
            completedGames.add(key);
        }
    }

    // Method to retrieve all completed games
    public ArrayList<DataKey> getCompletedGames() {
        return completedGames;
    }
}
