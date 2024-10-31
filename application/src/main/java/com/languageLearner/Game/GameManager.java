package com.languageLearner.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class GameManager {

    private static GameManager instance; // Singleton instance

    private final HashMap<Language, ArrayList<UUID>> languages;  // Key = Language instance, value = list of Game UUIDs for that language
    private final HashMap<UUID, ArrayList<Game>> easyGames;      // Key = UUID of each language, value = list of Game instances for that language's easy games
    private final HashMap<UUID, ArrayList<Game>> mediumGames;    // Key = UUID of each language, value = list of Game instances for that language's medium games
    private final HashMap<UUID, ArrayList<Game>> hardGames;      // Key = UUID of each language, value = list of Game instances for that language's hard games

    // Private constructor to prevent instantiation from outside
    private GameManager() {
        languages = new HashMap<>();
        easyGames = new HashMap<>();
        mediumGames = new HashMap<>();
        hardGames = new HashMap<>();
    }

    // Public method to get the singleton instance
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    // Setter method to add a language with a list of game UUIDs
    public void setLanguage(Language language, ArrayList<UUID> gameUUIDs) {
        languages.put(language, gameUUIDs);
    }

    // Setter method to add an easy game for a given language UUID
    public void setEasyGame(UUID languageUUID, Game game) {
        easyGames.computeIfAbsent(languageUUID, k -> new ArrayList<>()).add(game);
    }

    // Setter method to add a medium game for a given language UUID
    public void setMediumGame(UUID languageUUID, Game game) {
        mediumGames.computeIfAbsent(languageUUID, k -> new ArrayList<>()).add(game);
    }

    // Setter method to add a hard game for a given language UUID
    public void setHardGame(UUID languageUUID, Game game) {
        hardGames.computeIfAbsent(languageUUID, k -> new ArrayList<>()).add(game);
    }

    // Method to initialize a language in the languages map if needed
    public void initializeLanguage(Language language) {
        languages.computeIfAbsent(language, k -> new ArrayList<>());
    }
}
