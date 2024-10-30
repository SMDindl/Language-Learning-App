package com.languageLearner.game;

import java.util.ArrayList;
import java.util.UUID;

import com.languageLearner.app.Question;

/**
 * Language -> Game (has difficulty level) -> GameInfo
 * Language -> Game (has difficulty level) -> TextObject
 * Language -> Game (has difficulty level) -> Question
 */
public class Language implements HasUUID {

    private final String languageName;
    private final UUID uuid;
    private final ArrayList<Game> games;
    private final ArrayList<Question> questions;

    public Language(String languageName, UUID uuid) {
        this.languageName = languageName;
        this.uuid = uuid;
        this.games = new ArrayList<>();
        this.questions = new ArrayList<>();
    }

    // Getters
    @Override
    public UUID getUUID() {
        return uuid;
    }

    public String getLanguageName() {
        return languageName;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    // Setters
    // public void setLanguageName(String languageName) {
    //     this.languageName = languageName;
    // }

    // public void setUUID(UUID uuid) {
    //     this.uuid = uuid;
    // }

    // Adders
    public void addGame(Game game) {
        games.add(game);
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    // toString for testing population
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Language Name: ").append(languageName).append("\n");
        sb.append("UUID: ").append(uuid).append("\n");
        
        sb.append("Games:\n");
        for (Game game : games) {
            sb.append("  - ").append(game.toString()).append("\n");
        }
        
        sb.append("Questions:\n");
        for (Question question : questions) {
            sb.append("  - ").append(question.toString()).append("\n");
        }

        return sb.toString();
    }
}
