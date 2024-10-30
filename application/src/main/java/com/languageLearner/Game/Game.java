package com.languageLearner.game;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Game {
    
    private String language;
    private UUID languageUUID;  // uuid of the actual language (filipino)
    private String gameTitle;
    private String difficulty;
    private UUID uuid;          // uuid of the actual game
    private ArrayList<TextObject> textObjects;

    public Game(String language, UUID languageUUID, String gameTitle, String difficulty, UUID uuid, GameInfo info, ArrayList<TextObject> textObjects) {
        this.language = language;
        this.gameTitle = gameTitle;
        this.difficulty = difficulty;
        this.uuid = uuid;
        this.textObjects = textObjects;
    }

    // Methods to interact with game data... (setters/getters and these will interact with the Facade)
    public UUID getUUID() {
        return uuid;
    }

    public String getGameTitle() {
        return gameTitle;
    }



    // Methods to do game play...


    // Load the Game data from JSON (could also be done using a static method or factory method)
    @SuppressWarnings("unchecked")
    public static Game fromJson(JSONObject gameJson, String language, UUID languageUUID) {
        String gameTitle = (String) gameJson.get("GAME");
        String difficulty = (String) gameJson.get("DIFF");
        UUID uuid = UUID.fromString((String) gameJson.get("UUID"));
        
        JSONObject infoJson = (JSONObject) gameJson.get("INFO");
        GameInfo info = GameInfo.fromJson(infoJson, uuid);
        
        JSONArray textJsonArray = (JSONArray) gameJson.get("TEXT");
        ArrayList<TextObject> textObjects = (ArrayList<TextObject>) textJsonArray.stream()
                                            .map(obj -> TextObject.fromJson((JSONObject) obj, uuid))  // Cast each element to JSONObject also uuid passed
                                            .collect(Collectors.toCollection(ArrayList::new));

        Game game = new Game(language, languageUUID, gameTitle, difficulty, uuid, info, textObjects);
        GameData.getInstance().addGame(game);
        return game;
    }
    
}
