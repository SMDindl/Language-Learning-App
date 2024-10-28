package com.languageLearner.data;

import java.util.ArrayList;

public class ProgressTracker {
    private String language;
    private ArrayList<String> completedGames;
    private ArrayList<String> missedQuestions;

    public ProgressTracker(String language) {
        this.language = language;
        this.completedGames = new ArrayList<>();
        this.missedQuestions = new ArrayList<>();
    }

    public void addCompletedGame(DataKey game) {
        if (!completedGames.contains(game.toString())) {
            completedGames.add(game.toString());
        }
    }

    public void addMissedQuestion(String questionId) {
        if (!missedQuestions.contains(questionId)) {
            missedQuestions.add(questionId);
        }
    }

    public ArrayList<String> getCompletedGames() {
        return completedGames;
    }

    public ArrayList<String> getMissedQuestions() {
        return missedQuestions;
    }
}
