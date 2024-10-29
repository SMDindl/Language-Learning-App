package com.languageLearner.data;

import java.util.ArrayList;

/**
 * Tracks a user's progress in a specific language, including completed games and missed questions.
 */
public class ProgressTracker {

    private String language;
    private ArrayList<DataKey> completedGames;
    private ArrayList<Question> missedQuestions;
    // private HashMap<UUID, ArrayList<UUID>> matchingWords = new HashMap<>(); // Populated from user list of missed questions

    public ProgressTracker(String language) {
        this.language = language;
        this.completedGames = new ArrayList<>();
        this.missedQuestions = new ArrayList<>();
    }

    public ProgressTracker getTracker() {
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public ArrayList<DataKey> getCompletedGames() {
        return completedGames;
    }

    public ArrayList<Question> getMissedQuestions() {
        return missedQuestions;
    }

    public void addCompletedGame(DataKey game) {
        if (!completedGames.contains(game)) {
            completedGames.add(game);
        }
    }

    public void addMissedQuestion(Question question) {
        if (!missedQuestions.contains(question)) {
            missedQuestions.add(question);
        }
    }

    public void removeMissedQuestion(Question question) {
        missedQuestions.remove(question);
    }
}
