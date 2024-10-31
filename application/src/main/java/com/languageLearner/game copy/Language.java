package com.languageLearner.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Language -> Game (has difficulty level) -> GameInfo
 * Language -> Game (has difficulty level) -> TextObject
 * Language -> Game (has difficulty level) -> Question
 */
public class Language {

    private final UUID uuid;
    private final String languageName;
    private final HashMap<Difficulty, ArrayList<Game>> games;            // key = Difficulty, value = ArrayList of Game
    private final HashMap<Difficulty, ArrayList<Question>> questions;    // key = Difficulty, value = ArrayList of Questions

    public Language(UUID uuid, String languageName) {
        this.uuid = uuid;
        this.languageName = languageName;
        this.games = new HashMap<>();
        this.questions = new HashMap<>();
    }

    // Getters
    public Language getLanguage() {
        return this;
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getLanguageName() {
        return languageName;
    }

    /**
     * Get games for a specificed difficulty
     * Pass in int 1-3 to pick easy, medium, or hard
     */
    public ArrayList<Game> getGames(int difficulty) {
        switch(difficulty) {
            case 1 -> {
                return games.getOrDefault(Difficulty.EASY, new ArrayList<>());
            } case 2 -> {
                return games.getOrDefault(Difficulty.MEDIUM, new ArrayList<>());
            } case 3 -> {
                return games.getOrDefault(Difficulty.HARD, new ArrayList<>());
            }
        }
        return null;
    }

   /**
     * Get games for a specificed difficulty
     * Pass in String equal to easy, medium, or hard
     * @param difficulty
     * @return
     */
    public ArrayList<Game> getGames(Difficulty difficulty) {
        return games.getOrDefault(difficulty, new ArrayList<>());
    }

    public ArrayList<Game> getGames() {
        ArrayList<Game> allGames = new ArrayList<>();
    
        // Iterate over each list in the HashMap and add all games to allGames
        for (ArrayList<Game> gameList : games.values()) {
            allGames.addAll(gameList);
        }
    
        return allGames;
    }

    public ArrayList<Question> getQuestions(Difficulty difficulty) {
        ArrayList<Question> questionsList = new ArrayList<>();
        
        if (questions.containsKey(difficulty)) {
            questionsList.addAll(questions.get(difficulty));
        }
        
        return questionsList;
    }

    public ArrayList<Question> getQuestions() {
        ArrayList<Question> allQuestions = new ArrayList<>();
        
        for (ArrayList<Question> questionsList : questions.values()) {
            allQuestions.addAll(questionsList);
        }
        
        return allQuestions;
    }

    // Adders
    public void addGame(Difficulty difficulty, Game game) {
        games.computeIfAbsent(difficulty, k -> new ArrayList<>()).add(game);
    }

    public void addQuestion(Difficulty difficulty, Question question) {
        questions.computeIfAbsent(difficulty, k -> new ArrayList<>()).add(question);
    }

    // toString for testing population
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Language Name: ").append(languageName).append("\n");
        sb.append("UUID: ").append(uuid).append("\n");
        
        sb.append("Games:\n");
        sb.append(getGames());
        
        sb.append("Questions:\n");
        sb.append(getQuestions());

        return sb.toString();
    }
}
