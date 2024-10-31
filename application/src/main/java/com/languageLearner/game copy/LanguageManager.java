package com.languageLearner.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Note: in a HashMap, the key is on the left (the first item in each entry), and the value is on the right (the second item)
 */
public class LanguageManager {

    private static LanguageManager instance;  // Singleton instance

    /**
     * Each language has their languageUUID stored within but
     * having it mapped as well allows for easier access
     */
    private final HashMap<UUID, Language> languages = new HashMap<>();

    // Private constructor to prevent instantiation
    private LanguageManager() {}

    // Public method to provide access to the singleton instance
    public static LanguageManager getInstance() {
        if (instance == null) {
            instance = new LanguageManager();
        }
        return instance;
    }

    // Add language by using Language (to HashMap<Language's UUID, Language> languages)
    public void addLanguage(Language language) {
        languages.put(language.getUUID(), language);
    }

    // Get language instance by using language UUID (get Language)
    public Language getLanguage(UUID languageUUID) {
        return languages.get(languageUUID);
    }

    // Get ArrayList of all language instances (get ArrayList of Language)
    public ArrayList<Language> getAllLanguages() {
        return new ArrayList<>(languages.values());
    }

    // Get ArrayList of language names (get ArrayList of Strings)
    public ArrayList<String> getAllLanguageNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Language language : languages.values()) {
            names.add(language.getLanguageName());
        }
        return names;
    }

    // Get ArrayList of games from language uuid 
    public ArrayList<Game> getGamesOf(UUID languageUUID) {
        return languages.get(languageUUID).getGames();
    }

    @Override
    public String toString() {
        String s = "Languages:\n";
        for (HashMap.Entry<UUID, Language> entry : languages.entrySet()) {
            s += "UUID: " + entry.getKey().toString() 
                + ", Language: " + entry.getValue().toString() 
                + "\n";
        }
        return s;
    }

}
