package com.languageLearner.game;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader {


    private final GameManager gameManager = GameManager.getInstance();
    private final String GAME_DATA_FILE = "json\\gamesData.json";

    
        public void loadGameData() {
        try (FileReader reader = new FileReader(GAME_DATA_FILE)) {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            // Retrieve the array of languages
            JSONArray languagesArray = (JSONArray) jsonObject.get("LANGUAGES");

            // Parse each language entry
            loadLanguages(languagesArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadLanguages(JSONArray languagesArray) {
        LanguageManager languageManager = LanguageManager.getInstance(); // Assuming singleton pattern

        for (Object languageObj : languagesArray) {
            JSONObject languageData = (JSONObject) languageObj;

            // Retrieve the language name and UUID
            String languageName = (String) languageData.get("LANG");
            UUID languageUUID = UUID.fromString((String) languageData.get("UUID"));

            // Create a Language instance and add it to LanguageManager
            Language languageInstance = new Language(languageUUID, languageName);
            languageManager.addLanguage(languageInstance);

            // Get the games array for this language
            JSONArray gamesArray = (JSONArray) languageData.get("GAMES");

            // Load games for this language
            loadGames(gamesArray, languageInstance, languageUUID);
        }
    }

    private void loadGames(JSONArray gamesArray, Language languageInstance, UUID languageUUID) {
        for (Object gameObj : gamesArray) {
            JSONObject gameJson = (JSONObject) gameObj;

            // Create a Game instance from JSON, including language name and UUID
            Game game = Game.fromJson(gameJson, languageInstance.getName(), languageUUID);

            // Add the game to the appropriate list in LanguageManager based on difficulty
            languageInstance.addGame(game.getDifficulty(), game);
        }
    }

    @Override
    public String toString() { 
        String s = "\u001B[33m" + "DATA LOADER TO STRING:\n\n" + "\u001B[0m";
        s += "Languages:\n ";
        // LanguageManager.getInstance().getAllLanguages();
        s += "\u001B[33m" + "END OF DATA LOADER TO STRING\n\n" + "\u001B[0m";
        return s;
    }

    /**
     * DataLoader class tester
     * @param args
     */
    public static void main(String[] args) {
        DataLoader loader = new DataLoader();
        // LanguageManager gameData = LanguageManager.getInstance();
        loader.loadGameData(); 

       System.out.println(loader.toString());

    }
}
