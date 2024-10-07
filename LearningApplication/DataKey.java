
import java.util.Arrays;
import java.util.HashSet;
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

    // Public static method to get the instance of DataKey
    public static DataKey getInstance(String language, String gameType, String difficulty) {
        if (instance == null) {
            instance = new DataKey(language, gameType, difficulty); // Create instance only if it doesn't exist
        }
        return instance; // Return the singleton instance
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

    // Setters with validation
    public void setLanguage(String language) {
        if (!VALID_LANGUAGES.contains(language)) {
            throw new IllegalArgumentException("Invalid language: " + language);
        }
        this.language = language;
    }

    public void setDifficulty(String difficulty) {
        if (!VALID_DIFFICULTIES.contains(difficulty)) {
            throw new IllegalArgumentException("Invalid difficulty: " + difficulty);
        }
        this.difficulty = difficulty;
    }

    public void setGameType(String gameType) {
        if (!VALID_GAME_TYPES.contains(gameType)) {
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
        return language.equals(dataKey.language) && 
               gameType.equals(dataKey.gameType) && 
               difficulty.equals(dataKey.difficulty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(language, gameType, difficulty);
    }

    @Override
    public String toString() {
        return language + "-" + gameType + "-" + difficulty;
    }
}
