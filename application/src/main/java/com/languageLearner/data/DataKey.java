package com.languageLearner.data;

import java.util.Objects;

/**
 * Singleton class for DataKey
 */
public class DataKey extends DataConstants {

    private static DataKey instance; // Static instance variable
    private String language;
    private String gameType;
    private String difficulty;

    // Private constructor to prevent instantiation
    private DataKey(String language, String gameType, String difficulty) {
        setLanguage(language);
        setGameType(gameType);
        setDifficulty(difficulty);
    }

    // Public static method to get the instance of DataKey or update instance if already created
    public static DataKey getInstance(String language, String gameType, String difficulty) {
        instance = new DataKey(language, gameType, difficulty);
        return instance;
    }
    
    // Public static method to get the instance of DataKey
    public static DataKey getInstance() {
        return instance; 
    }

    // Getters
    public String getLanguage() {
        return language;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getGameType() {
        return gameType;
    }

    // Setters with validation, but allow null values
    public void setLanguage(String language) {
        if (language != null && !VALID_LANGUAGES.contains(language)) {
            throw new IllegalArgumentException("Invalid language: " + language);
        }
        this.language = language;
    }

    public void setDifficulty(String difficulty) {
        if (difficulty != null && !VALID_DIFFICULTIES.contains(difficulty)) {
            throw new IllegalArgumentException("Invalid difficulty: " + difficulty);
        }
        this.difficulty = difficulty;
    }

    public void setGameType(String gameType) {
        if (gameType != null && !VALID_GAME_TYPES.contains(gameType)) {
            throw new IllegalArgumentException("Invalid game type: " + gameType);
        }
        this.gameType = gameType;
    }

    // Override equals and hashCode for proper functionality in collections
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        DataKey dataKey = (DataKey) obj;
        return Objects.equals(language, dataKey.language) && 
               Objects.equals(gameType, dataKey.gameType) && 
               Objects.equals(difficulty, dataKey.difficulty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(language, gameType, difficulty);
    }

    @Override
    public String toString() {
        return (language != null ? language : "null") + "-" + 
               (gameType != null ? gameType : "null") + "-" + 
               (difficulty != null ? difficulty : "null");
    }
}
