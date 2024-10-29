package com.languageLearner.data;

import java.util.Objects;

/**
 * Singleton class representing a unique identifier for language learning data. 
 * This class holds attributes for language, game type, and difficulty, ensuring
 * only one instance exists per combination.
 */
public class DataKey extends DataConstants {

    private static DataKey instance; // Static instance variable
    private String language;
    private String gameType;
    private String difficulty;

    /**
     * Private constructor to prevent external instantiation.
     *
     * @param language the language for this DataKey
     * @param gameType the game type for this DataKey
     * @param difficulty the difficulty level for this DataKey
     */
    private DataKey(String language, String gameType, String difficulty) {
        setLanguage(language);
        setGameType(gameType);
        setDifficulty(difficulty);
    }

    /**
     * Returns the single instance of DataKey, creating or updating it with 
     * specified attributes if needed.
     *
     * @param language the language for this DataKey
     * @param gameType the game type for this DataKey
     * @param difficulty the difficulty level for this DataKey
     * @return the single instance of DataKey
     */
    public static DataKey getInstance(String language, String gameType, String difficulty) {
        instance = new DataKey(language, gameType, difficulty);
        return instance;
    }
    
    /**
     * Returns the current instance of DataKey.
     *
     * @return the current DataKey instance
     */
    public static DataKey getInstance() {
        return instance; 
    }

    /**
     * Returns the language attribute.
     *
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Returns the difficulty attribute.
     *
     * @return the difficulty
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Returns the game type attribute.
     *
     * @return the game type
     */
    public String getGameType() {
        return gameType;
    }

    /**
     * Sets the language, validating it against allowed values.
     *
     * @param language the language to set
     * @throws IllegalArgumentException if the language is invalid
     */
    public void setLanguage(String language) {
        if (language != null && !VALID_LANGUAGES.contains(language)) {
            throw new IllegalArgumentException("Invalid language: " + language);
        }
        this.language = language;
    }

    /**
     * Sets the difficulty, validating it against allowed values.
     *
     * @param difficulty the difficulty to set
     * @throws IllegalArgumentException if the difficulty is invalid
     */
    public void setDifficulty(String difficulty) {
        if (difficulty != null && !VALID_DIFFICULTIES.contains(difficulty)) {
            throw new IllegalArgumentException("Invalid difficulty: " + difficulty);
        }
        this.difficulty = difficulty;
    }

    /**
     * Sets the game type, validating it against allowed values.
     *
     * @param gameType the game type to set
     * @throws IllegalArgumentException if the game type is invalid
     */
    public void setGameType(String gameType) {
        if (gameType != null && !VALID_GAME_TYPES.contains(gameType)) {
            throw new IllegalArgumentException("Invalid game type: " + gameType);
        }
        this.gameType = gameType;
    }

    /**
     * Creates a DataKey instance from a formatted string.
     *
     * @param dataKeyString a string in "language-gameType-difficulty" format
     * @return a new DataKey if valid, null otherwise
     */
    public static DataKey fromString(String dataKeyString) {
        String[] parts = dataKeyString.split("-");
        if (parts.length == 3) {
            return new DataKey(parts[0], parts[1], parts[2]);
        }
        return null;
    }

    /**
     * Checks equality between this DataKey and another object.
     *
     * @param obj the object to compare
     * @return true if objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DataKey dataKey = (DataKey) obj;
        return Objects.equals(language, dataKey.language) && 
               Objects.equals(gameType, dataKey.gameType) && 
               Objects.equals(difficulty, dataKey.difficulty);
    }

    /**
     * Returns a hash code for this DataKey.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(language, gameType, difficulty);
    }

    /**
     * Returns a string representation of the DataKey in "language-gameType-difficulty" format.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return (language != null ? language : "null") + "-" + 
               (gameType != null ? gameType : "null") + "-" + 
               (difficulty != null ? difficulty : "null");
    }
}
