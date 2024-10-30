package com.languageLearner.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

import com.languageLearner.app.Question;

/**
 * Singleton class to manage game data
 */
@SuppressWarnings("FieldMayBeFinal")
public class GameData {

    private static GameData instance; // Singleton instance

    // ArrayLists for managing different types of game data
    private final HashMap<UUID, String> languages;           // Language management
    private final ArrayList<String> DIFFICULTIES = new ArrayList<>(Arrays.asList("EASY", "MEDIUM", "HARD")); // Difficulty levels
    private final ArrayList<Game> games;                // Game data management
    private final ArrayList<Question> questions;        // Question data management
    private final ArrayList<TextObject> textObjects;    // TextObject data management
    private final ArrayList<GameInfo> gameInfo;         // GameInfo data management

    // Private constructor initializing ArrayLists
    private GameData() {
        languages = new HashMap<>();      // Initialize languages list
        games = new ArrayList<>();          // Initialize games list
        questions = new ArrayList<>();      // Initialize questions list
        textObjects = new ArrayList<>();    // Initialize textObjects list
        gameInfo = new ArrayList<>();       // Initialize gameInfo list
    }

    // Singleton access method
    public static GameData getInstance() {
        if (instance == null) {
            instance = new GameData();
        }
        return instance;
    }

    // Getters
    public ArrayList<String> getLanguageStrings() {
        return new ArrayList<>(languages.values()); // List of language names
    }

    public ArrayList<UUID> getLanguageUUIDs() {
        return new ArrayList<>(languages.keySet()); // List of language UUIDs
    }

    public HashMap<UUID, String> getLanguages() {
        return new HashMap<>(languages); // Map of UUID to language name
    }

    public ArrayList<String> getDifficulties() {
        return DIFFICULTIES;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public ArrayList<TextObject> getTextObjects() {
        return textObjects;
    }

    public ArrayList<GameInfo> getGameInfo() {
        return gameInfo;
    }

    // Adder methods
    /**
     * Adds a language if it doesn't already exist.
     *
     * @param languageName the name of the language to add
     */
    public void addLanguage(UUID languageUUID, String languageName) {
        if (!languages.containsKey(languageUUID)) {
            languages.put(languageUUID, languageName);
        }
    }

    /**
     * Adds a game to the games list.
     *
     * @param game the Game to add
     */
    public void addGame(Game game) {
        if (!games.contains(game)) {
            games.add(game);
        }
    }

    /**
     * Adds a question to the questions list.
     *
     * @param question the Question to add
     */
    public void addQuestion(Question question) {
        if (!questions.contains(question)) {
            questions.add(question);
        }
    }

    /**
     * Adds a TextObject to the textObjects list.
     *
     * @param textObject the TextObject to add
     */
    public void addTextObject(TextObject textObject) {
        if (!textObjects.contains(textObject)) {
            textObjects.add(textObject);
        }
    }

    /**
     * Adds a GameInfo object to the gameInfo list.
     *
     * @param gameInfo the GameInfo to add
     */
    public void addGameInfo(GameInfo gameInfo) {
        if (!this.gameInfo.contains(gameInfo)) {
            this.gameInfo.add(gameInfo);
        }
    }

    public Object search(UUID uuid) {
        return uuid;
    }
}
