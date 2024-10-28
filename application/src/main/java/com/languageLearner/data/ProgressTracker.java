package com.languageLearner.data;

import java.util.ArrayList;
import java.util.UUID;

public class ProgressTracker {

    private String language;
    private ArrayList<DataKey> completedGames;
    private ArrayList<UUID> missedQuestions;

    public ProgressTracker(String language) {
        this.language = language;
        this.completedGames = new ArrayList<>();
        this.missedQuestions = new ArrayList<>();
    }

    public ProgressTracker(String language, ArrayList<DataKey> completedGames) {
        this.language = language;
        this.completedGames = completedGames;
        this.missedQuestions = new ArrayList<>();
    }

    public void addCompletedGame(DataKey game) { 
        if (!completedGames.contains(game)) {
            completedGames.add(game);
        }
    }

    public void addMissedQuestion(UUID questionId) {
        if (!missedQuestions.contains(questionId)) {
            missedQuestions.add(questionId);
        }
    }

    public void removeMissedQuestion(UUID questionId) {
        if (missedQuestions.contains(questionId)) {
            missedQuestions.remove(questionId);
        }
    }

    // Getters
    public ArrayList<DataKey> getCompletedGames() {
        return completedGames;
    }

    public ArrayList<UUID> getMissedQuestions() {
        return missedQuestions;
    }

    public String getLanguage() {
        return language;
    }

}
