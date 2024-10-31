package com.learner.game;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.learner.game.innerdata.GameInfo;
import com.learner.game.innerdata.TextObject;

public class Game {

    private final UUID languageUUID;
    private final String gameTitle;
    private final Difficulty difficulty;
    private final UUID uuid;            // UUID of the actual game
    private final ArrayList<TextObject> textObjects;
    private final GameInfo info;

    public Game(UUID languageUUID, String gameTitle, Difficulty difficulty, UUID uuid, GameInfo info, ArrayList<TextObject> textObjects) {
        this.languageUUID = languageUUID;
        this.gameTitle = gameTitle;
        this.difficulty = difficulty;
        this.uuid = uuid;
        this.info = info;
        this.textObjects = textObjects;
    }

    // Getters
    public UUID getLanguageUUID() {
        return languageUUID;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public UUID getUUID() {
        return uuid;
    }

    public GameInfo getInfo() {
        return info;
    }

    public ArrayList<TextObject> getTextObjects() {
        return textObjects;
    }

    // Load the Game data from JSON (could also be done using a static method or factory method)
    @SuppressWarnings("unchecked")
    public static Game fromJson(JSONObject gameJson, String language, UUID languageUUID) {
        String gameTitle = (String) gameJson.get("GAME");
        Difficulty difficulty = (Difficulty) gameJson.get("DIFF");
        UUID uuid = UUID.fromString((String) gameJson.get("UUID"));

        JSONObject infoJson = (JSONObject) gameJson.get("INFO");
        GameInfo info = GameInfo.fromJson(infoJson, uuid);

        JSONArray textJsonArray = (JSONArray) gameJson.get("TEXT");
        ArrayList<TextObject> textObjects = (ArrayList<TextObject>) textJsonArray.stream()
                                            .map(obj -> TextObject.fromJson((JSONObject) obj, uuid))  // Cast each element to JSONObject, also UUID passed
                                            .collect(Collectors.toCollection(ArrayList::new));
        
        return new Game(languageUUID, gameTitle, difficulty, uuid, info, textObjects);
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "\n\n=== Game class data for " + difficulty + " " + gameTitle + " ===\n\n" 
            + "Language UUID:\n" 
            + languageUUID + "\n\n"
            + "Game UUID:\n" 
            + uuid + "\n\n"
            + "Info:\n" 
            + (info != null ? info.toString() : "No Info Available") + "\n\n"
            + "First Text Object:\n" 
            + (textObjects != null && !textObjects.isEmpty() ? textObjects.get(0).toString() : "No Text Objects Available") + "\n\n"
            + "====== End of data ======\n\n";
    }
    
}
