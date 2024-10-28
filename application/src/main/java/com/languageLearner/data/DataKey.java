package com.languageLearner.data;

import java.util.Objects;

/**
 * The DataKey class is a Singleton that represents a key for indentifying 
 * language data settings, such as language, game type, and difficulty level.
 * This class provides validation for these properties and ensures that only one
 * instance exists, following the Singleton pattern.
 */
public class DataKey extends DataConstants {

    private static DataKey instance; // Static instance variable

    /** Language setting for the data key */
    private String language;

    /** Game type setting for the data key */
    private String gameType;

    /** Difficulty setting for the data key */
    private String difficulty;

    /**
     * Pivate constructor to prevent instantiation from outside the class.
     * 
     * @param language the language setting
     * @param gameType the game type setting
     * @param difficulty the difficulty setting
     */
    private DataKey(String language, String gameType, String difficulty) {
        setLanguage(language);
        setGameType(gameType);
        setDifficulty(difficulty);
    }

    /**
     * Return the instance of DataKey, creating it if it doesn't exist.
     * 
     * @param language the language setting
     * @param gameType the game type setting
     * @param difficulty the difficulty setting
     * @return the instance of DataKey
     */
    public static DataKey getInstance(String language, String gameType, String difficulty) {
        if (instance == null) {
            instance = new DataKey(language, gameType, difficulty); 
        }
        instance.setLanguage(language);
        instance.setGameType(gameType);
        instance.setDifficulty(difficulty);
        return instance; 
    }

    /**
     * Return the instance of DataKey without parameters.
     * 
     * @return the instance of DataKey
     */
    public static DataKey getInstance() {
        return instance; 
    }

    /**
     * Getters for the properties
     * 
     * @return the language setting
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Getters for the properties
     * 
     * @return the difficulty setting
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Getters for the properties
     * 
     * @return the game type setting
     */
    public String getGameType() {
        return gameType;
    }

    /**
     * Set the language setting, validating against allowed values.
     * 
     * @param language the language setting
     * @throws IllegalArgumentException if the language is invalid
     */
    public void setLanguage(String language) {
        if (!VALID_LANGUAGES.contains(language)) {
            throw new IllegalArgumentException("Invalid language: " + language);
        }
        this.language = language;
    }

    /**
     * Set the difficulty setting, validating against allowed values.
     * 
     * @param difficulty the difficulty setting
     * @throws IllegalArgumentException if the difficulty is invalid
     */
    public void setDifficulty(String difficulty) {
        if (!VALID_DIFFICULTIES.contains(difficulty)) {
            throw new IllegalArgumentException("Invalid difficulty: " + difficulty);
        }
        this.difficulty = difficulty;
    }

    /**
     * Sets the game type setting, validating against allowed values.
     * 
     * @param gameType the game type setting
     * @throws IllegalArgumentException if the game type is invalid
     */
    public void setGameType(String gameType) {
        if (!VALID_GAME_TYPES.contains(gameType)) {
            throw new IllegalArgumentException("Invalid game type: " + gameType);
        }
        this.gameType = gameType;
    }

    /**
     * Compares this DataKey to another object for equality.
     * 
     * @param obj the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        DataKey dataKey = (DataKey) obj;
        return language.equals(dataKey.language) && 
               gameType.equals(dataKey.gameType) && 
               difficulty.equals(dataKey.difficulty);
    }

    /**
     * Returns a hash code value for the object.
     * 
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(language, gameType, difficulty);
    }
    
    /**
     * Returns a string representation of the DataKey.
     * 
     * @return a string representation of the DataKey
     */
    @Override
    public String toString() {
        return language + "-" + gameType + "-" + difficulty;
    }
}
