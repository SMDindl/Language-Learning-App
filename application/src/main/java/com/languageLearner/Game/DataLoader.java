package com.languageLearner.game;

import java.io.FileReader;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader {

    private final GameData gameData = GameData.getInstance();
    private String GAME_DATA_FILE = "json\\gamesData.json";

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
        for (Object languageObj : languagesArray) {
            JSONObject languageData = (JSONObject) languageObj;
    
            // Retrieve the language name and UUID
            String language = (String) languageData.get("LANG");
            UUID languageUUID = UUID.fromString((String) languageData.get("UUID"));
    
            // Add the language to GameData
            GameData.getInstance().addLanguage(languageUUID, language);
    
            // Get the games array for this language
            JSONArray gamesArray = (JSONArray) languageData.get("GAMES");
    
            // Load games for this language
            loadGames(gamesArray, language, languageUUID);
        }
    }
    
    private void loadGames(JSONArray gamesArray, String language, UUID languageUUID) {
        for (Object gameObj : gamesArray) {
            JSONObject gameJson = (JSONObject) gameObj;
    
            // Create a Game instance from JSON, including language and languageUUID
            Game game = Game.fromJson(gameJson, language, languageUUID);
    
            // Add each game to GameData
            GameData.getInstance().addGame(game);
        }
    }

    @Override
    public String toString() { 
        String s = "\u001B[33m" + "DATA LOADER TO STRING:\n\n" + "\u001B[0m";
        s += "Languages:\n "
        + GameData.getInstance().getLanguages();
        for(Game game : gameData.getGames()) {
            s += game;
        }
        s += "\u001B[33m" + "END OF DATA LOADER TO STRING\n\n" + "\u001B[0m";
        return s;
    }

    /**
     * DataLoader class tester
     * @param args
     */
    public static void main(String[] args) {
        DataLoader loader = new DataLoader();
        // GameData gameData = GameData.getInstance();
        loader.loadGameData(); 

       System.out.println(loader.toString());

    }
}
