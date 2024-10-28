package com.languageLearner.app;

import com.languageLearner.data.DataKey;
import com.languageLearner.data.GameData;

import java.util.Set;

public class GameDataBasicTester {

    public static void main(String[] args) {
        GameData gameData = GameData.getInstance();

        // Initialize DataLoader and populate GameData (assuming DataLoader loads data)
        System.out.println("Loading data into GameData...");
        gameData.populateData(new DataLoader().loadWords(), 
                              new DataLoader().loadQuestions(), 
                              new DataLoader().loadStories(), 
                              new DataLoader().loadLetters());

        System.out.println("\n--- Verifying GameData Contents ---");

        // Check wordsMap
        System.out.println("Words Map Loaded: " + !gameData.getWordsMap().isEmpty());

        // Check questionsMap
        System.out.println("Questions Map Loaded: " + !gameData.getQuestionsMap().isEmpty());

        // Check storiesMap
        System.out.println("Stories Map Loaded: " + !gameData.getStoriesMap().isEmpty());

        // Check lettersMap
        System.out.println("Letters Map Loaded: " + !gameData.getLettersMap().isEmpty());

        // Print available games for each dataKey loaded in GameData
        Set<DataKey> loadedKeys = gameData.getLoadedKeys();
        System.out.println("\n--- Available Games by DataKey ---");
        for (DataKey dataKey : loadedKeys) {
            System.out.println("DataKey: " + dataKey);
            System.out.println("Available Games: " + gameData.getAvailableGamesForDifficulty(dataKey));
        }

        System.out.println("--- End of Data Verification ---");
    }
}
