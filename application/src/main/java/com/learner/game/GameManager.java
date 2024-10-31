package com.learner.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.learner.game.questions.Question;

public class GameManager {

    private static GameManager instance; // Singleton instance

    private final HashMap<Language, ArrayList<UUID>> languages; // Key = Language instance, value = list of Game UUIDs for that language
    private final HashMap<UUID, ArrayList<Game>> easyGames;     // Key = UUID of each language, value = list of Game instances for that language's easy games
    private final HashMap<UUID, ArrayList<Game>> mediumGames;   // Key = UUID of each language, value = list of Game instances for that language's medium games
    private final HashMap<UUID, ArrayList<Game>> hardGames;     // Key = UUID of each language, value = list of Game instances for that language's hard games
    private final HashMap<UUID, ArrayList<Question>> questions; // Key = UUID of the game, value = list of Question instances for that Game

    // Private constructor to prevent instantiation from outside
    private GameManager() {
        languages = new HashMap<>();
        easyGames = new HashMap<>();
        mediumGames = new HashMap<>();
        hardGames = new HashMap<>();
        questions = new HashMap<>();
    }

    // Public method to get the singleton instance
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    // Method to initialize a language in the languages map if needed
    public void initializeLanguage(Language language) {
        languages.computeIfAbsent(language, k -> new ArrayList<>());
    }

    // Setter method to add a language with a list of game UUIDs
    public void addLanguage(Language language, ArrayList<UUID> gameUUIDs) {
        languages.put(language, gameUUIDs);
    }

    // Getter for languages, returning a list of Language instances
    public List<Language> getLanguages() {
        return new ArrayList<>(languages.keySet());
    }

    // Methods to add games by difficulty
    public void addEasyGame(UUID languageUUID, Game game) {
        easyGames.computeIfAbsent(languageUUID, k -> new ArrayList<>()).add(game);
    }

    public void addMediumGame(UUID languageUUID, Game game) {
        mediumGames.computeIfAbsent(languageUUID, k -> new ArrayList<>()).add(game);
    }

    public void addHardGame(UUID languageUUID, Game game) {
        hardGames.computeIfAbsent(languageUUID, k -> new ArrayList<>()).add(game);
    }

    // Getters for games by difficulty
    public ArrayList<Game> getEasyGames(UUID languageUUID) {
        return easyGames.getOrDefault(languageUUID, new ArrayList<>());
    }

    public ArrayList<Game> getMediumGames(UUID languageUUID) {
        return mediumGames.getOrDefault(languageUUID, new ArrayList<>());
    }

    public ArrayList<Game> getHardGames(UUID languageUUID) {
        return hardGames.getOrDefault(languageUUID, new ArrayList<>());
    }

    // Method to add a question to the question list for a specific game
    public void addQuestion(UUID gameUUID, Question question) {
        questions.computeIfAbsent(gameUUID, k -> new ArrayList<>()).add(question);
    }

    // Getter for questions associated with a specific game
    public ArrayList<Question> getQuestions(UUID gameUUID) {
        return questions.getOrDefault(gameUUID, new ArrayList<>());
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\u001B[33m" + "DATA LOADER TO STRING:\n\n" + "\u001B[0m");

        for (Language language : getLanguages()) {
            s.append(language.toString()).append("\n");
            ArrayList<UUID> gameUUIDs = languages.get(language);

            // Loop through each game UUID and retrieve games by difficulty
            for (UUID gameUUID : gameUUIDs) {
                s.append("Easy Games:\n");
                for (Game game : getEasyGames(gameUUID)) {
                    s.append(game.toString()).append("\n");
                }

                s.append("Medium Games:\n");
                for (Game game : getMediumGames(gameUUID)) {
                    s.append(game.toString()).append("\n");
                }

                s.append("Hard Games:\n");
                for (Game game : getHardGames(gameUUID)) {
                    s.append(game.toString()).append("\n");
                }
            }
        }

        s.append("\u001B[33m").append("END OF DATA LOADER TO STRING\n\n").append("\u001B[0m");
        return s.toString();
    }
}

