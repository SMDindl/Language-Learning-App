package com.learner.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import com.learner.game.innerdata.TextObject;
import com.learner.game.questions.Question;

public class GameManager {

    private static GameManager instance; // Singleton instance

    // HashMap<key, value>, .key(---), .value(---)
    private final HashMap<UUID, Game> games;                      // Key = game uuid,       Value = instance of game 
    private final HashMap<UUID, ArrayList<UUID>> easyGameUUIDs;   // Key = language uuid,   Value = list of easy game uuids
    private final HashMap<UUID, ArrayList<UUID>> mediumGameUUIDs; // Key = language uuid,   Value = list of medium game uuids
    private final HashMap<UUID, ArrayList<UUID>> hardGameUUIDs;   // Key = language uuid,   Value = list of hard game uuids
    private final HashMap<Language, ArrayList<UUID>> languages;   // Key = Language,        Value = list of game uuids 
    private final HashMap<UUID, ArrayList<UUID>> textObjects;     // Key = game uuid,       Value = list of TextObject uuids 
    private final HashMap<UUID, ArrayList<UUID>> questions;       // Key = game uuid,       Value = list of Question UUIDs

    // Private instance to stasify singleton pattern
    private GameManager() {
        games = new HashMap<>();
        easyGameUUIDs = new HashMap<>();
        mediumGameUUIDs = new HashMap<>();
        hardGameUUIDs = new HashMap<>();
        languages = new HashMap<>();
        textObjects = new HashMap<>();
        questions = new HashMap<>();
    }

    /**
     * 
     * @return instance of GameManager
     */
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    /**
     * 
     * @param language
     */
    public void initializeLanguage(Language language) {
        languages.computeIfAbsent(language, k -> new ArrayList<>());
    }

    /**
     * Add a game to all HashMaps where the game / game uuid needs to be accounted for
     */
    public void addGame(Game game) {
        UUID gameUUID = game.getUUID();
        games.put(gameUUID, game);

        HashMap<UUID, ArrayList<UUID>> difficultyMap;
        switch (game.getDifficulty()) {
            case EASY -> difficultyMap = easyGameUUIDs;
            case MEDIUM -> difficultyMap = mediumGameUUIDs;
            case HARD -> difficultyMap = hardGameUUIDs;
            default -> {
                return;
            }
        }
        difficultyMap.computeIfAbsent(game.getLanguageUUID(), k -> new ArrayList<>()).add(gameUUID);
        languages.computeIfAbsent(getLanguageByUUID(game.getLanguageUUID()), k -> new ArrayList<>()).add(gameUUID);
    }

    // TextObject Management

    /**
     * Add textObject to the textObjects HashMap
     */
    public void addTextObjectUUID(UUID gameUUID, UUID textObjectUUID) {
        textObjects.computeIfAbsent(gameUUID, k -> new ArrayList<>()).add(textObjectUUID);
    }

    /**
     * Search for textObject based from gameUUID & textObject
     */
    public TextObject findTextObjectInGame(UUID gameUUID, UUID textObjectUUID) {
        Game game = findGameByUUID(gameUUID);
        return (game != null) ? game.getTextObject(textObjectUUID) : null;
    }

    // Question Management

    /**
     * 
     */
    public void addQuestionUUID(UUID gameUUID, UUID questionUUID) {
        questions.computeIfAbsent(gameUUID, k -> new ArrayList<>()).add(questionUUID);
    }

    /**
     * 
     * @param gameUUID
     * @param questionUUID
     * @return
     */
    public Question findQuestionInGame(UUID gameUUID, UUID questionUUID) {
        Game game = findGameByUUID(gameUUID);
        return (game != null) ? game.getQuestion(questionUUID) : null;
    }

    /**
     * 
     */
    public ArrayList<UUID> getQuestionUUIDs(UUID gameUUID) {
        return questions.getOrDefault(gameUUID, new ArrayList<>());
    }

    /**
     * Get questions of a game, by using the game uuid
     * 
     * @param gameUUID
     * @return
     */
    public ArrayList<Question> getQuestions(UUID gameUUID) {
        Game game = findGameByUUID(gameUUID);
        return (game != null) ? game.getQuestions() : new ArrayList<>();
    }

    // Game Retrieval
    // Can be used to get game, then game can be used to get languageUUID, and other info
    public Game findGameByUUID(UUID gameUUID) {
        return games.get(gameUUID);
    }

    // Language Retrieval
    private Language getLanguageByUUID(UUID languageUUID) {
        for (Language language : languages.keySet()) {
            if (language.getUUID().equals(languageUUID)) {
                return language;
            }
        }
        return null;
    }

    /**
     * 
     * @return gameUUID that the targetUUID (question or textObject, is contained within)
     */
    public UUID findGameUUIDByQuestionOrTextObjectUUID(UUID targetUUID) {
        // First, search in the questions HashMap
        for (HashMap.Entry<UUID, ArrayList<UUID>> entry : questions.entrySet()) {
            UUID gameUUID = entry.getKey();
            ArrayList<UUID> questionList = entry.getValue();
    
            // Check if the targetUUID is in the list of question UUIDs
            if (questionList.contains(targetUUID)) {
                return gameUUID;  // Return gameUUID if found in questions
            }
        }
    
        // If not found in questions, search in the textObjects HashMap
        for (HashMap.Entry<UUID, ArrayList<UUID>> entry : textObjects.entrySet()) {
            UUID gameUUID = entry.getKey();
            ArrayList<UUID> textObjectList = entry.getValue();
    
            // Check if the targetUUID is in the list of textObject UUIDs
            if (textObjectList.contains(targetUUID)) {
                return gameUUID;  // Return gameUUID if found in textObjects
            }
        }
    
        // Return null if not found in either HashMap
        return null;
    }

    // toString Method for Debugging
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("\u001B[33m").append("GAME MANAGER TO STRING:").append("\u001B[0m\n");
    
        // Iterate through each language
        for (Language language : languages.keySet()) {
            s.append(language.toString()).append("\n");
            s.append("Easy ").append(language.getLanguageName()).append(" games:\n");
            s.append(easyGameUUIDs.get(language.getUUID())).append("\n");
            s.append("Medium ").append(language.getLanguageName()).append(" games:\n");
            s.append(mediumGameUUIDs.get(language.getUUID())).append("\n");
            s.append("Hard ").append(language.getLanguageName()).append(" games:\n");
            s.append(hardGameUUIDs.get(language.getUUID())).append("\n");
        }
    
        s.append("\u001B[33m").append("END OF GAME MANAGER TO STRING\n\n").append("\u001B[0m");
        return s.toString();
    }
}
