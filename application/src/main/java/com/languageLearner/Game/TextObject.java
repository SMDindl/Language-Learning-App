package com.languageLearner.game;

import java.util.UUID;

import org.json.simple.JSONObject;

public class TextObject {
    
    private String text;
    private String englishText;
    private String linkedText;
    private String englishLinkedText;
    private String helperText;
    private UUID uuid;
    private UUID gameUUID;

    public TextObject(String text, String englishText, String linkedText, String englishLinkedText, String helperText, UUID uuid, UUID gameUUID) {
        this.text = text;
        this.englishText = englishText;
        this.linkedText = linkedText;
        this.englishLinkedText = englishLinkedText;
        this.helperText = helperText;
        this.uuid = uuid;
        this.gameUUID = gameUUID;
    }

    // Data Getters
    public UUID getUUID() {
        return uuid;
    }

    public UUID getGameUUID() {
        return gameUUID;
    }

    // Standard getters

    public static TextObject fromJson(JSONObject textJson, UUID gameUUID) {
        String text = (String) textJson.get("text");
        String englishText = (String) textJson.get("englishText");
        String linkedText = (String) textJson.get("linkedText");
        String englishLinkedText = (String) textJson.get("englishLinkedText");
        String helperText = (String) textJson.get("helperText");
        UUID uuid = UUID.fromString((String) textJson.get("UUID"));

        // Make sure the data is being loaded in correct places

    
        return new TextObject(text, englishText, linkedText, englishLinkedText, helperText, uuid, gameUUID);
    }
    
}
