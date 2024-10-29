package com.languageLearner.Game;

import java.util.UUID;

public class Game {
    private String gameName;
    private String difficulty;
    private UUID uuid;
    private GameInfo info;
    private ArrayList<TextEntry> textEntries;

    public Game(String gameName, String difficulty, UUID uuid, GameInfo info, ArrayList<TextEntry> textEntries) {
        this.gameName = gameName;
        this.difficulty = difficulty;
        this.uuid = uuid;
        this.info = info;
        this.textEntries = textEntries;
    }

    // Load the Game data from JSON (could also be done using a static method or factory method)
    public static Game fromJson(JSONObject gameJson) {
        String gameName = (String) gameJson.get("GAME");
        String difficulty = (String) gameJson.get("DIFF");
        UUID uuid = UUID.fromString((String) gameJson.get("UUID"));
        
        JSONObject infoJson = (JSONObject) gameJson.get("INFO");
        GameInfo info = GameInfo.fromJson(infoJson);
        
        JSONArray textJsonArray = (JSONArray) gameJson.get("TEXT");
        List<TextEntry> textEntries = textJsonArray.stream()
                .map(TextEntry::fromJson)
                .collect(Collectors.toList());

        return new Game(gameName, difficulty, uuid, info, textEntries);
    }

    // Methods to interact with game data...
}
