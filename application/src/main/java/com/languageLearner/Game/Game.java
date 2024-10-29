package com.languageLearner.game;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Game {
    
    private String gameTitle;
    private String difficulty;
    private UUID uuid;
    private ArrayList<TextObject> textObjects;

    public Game(String gameTitle, String difficulty, UUID uuid, GameInfo info, ArrayList<TextObject> textObjects) {
        this.gameTitle = gameTitle;
        this.difficulty = difficulty;
        this.uuid = uuid;
        this.textObjects = textObjects;
    }

    // Methods to interact with game data... (setters/getters and these will interact with the Facade)

    // Methods to do game play...


    // Load the Game data from JSON (could also be done using a static method or factory method)
    public static Game fromJson(JSONObject gameJson) {
        String gameTitle = (String) gameJson.get("GAME");
        String difficulty = (String) gameJson.get("DIFF");
        UUID uuid = UUID.fromString((String) gameJson.get("UUID"));
        
        JSONObject infoJson = (JSONObject) gameJson.get("INFO");
        GameInfo info = GameInfo.fromJson(infoJson);
        
        JSONArray textJsonArray = (JSONArray) gameJson.get("TEXT");
        ArrayList<TextObject> textObjects = (ArrayList<TextObject>) textJsonArray.stream()
                .map(obj -> TextObject.fromJson((JSONObject) obj))  // Cast each element to JSONObject
                .collect(Collectors.toCollection(ArrayList::new));
    
        return new Game(gameTitle, difficulty, uuid, info, textObjects);
    }
    
}
