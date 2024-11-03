package com.learner.game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.learner.game.innerdata.GameInfo;
import com.learner.game.innerdata.TextObject;

/**
 * The game will pull questions from GameManger
 */
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

    public void game() {
        intro();
        play();
        quiz();
    }

    // Introduce user to game info and give instructions
    private void intro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(info);
        System.out.println("Press Enter to continue...");
        scanner.nextLine();  // Waits for Enter key press
    }

    // Play Game
    private void play() {

    }

    // Pull Questions & ask them
    private void quiz() {

    }

    // Getters
    /**
     * 
     * @return
     */
    public UUID getLanguageUUID() { return languageUUID; }

    /**
     * 
     * @return
     */
    public String getGameTitle() { return gameTitle; }

    /**
     * 
     * @return
     */
    public Difficulty getDifficulty() { return difficulty; }

    /**
     * 
     * @return
     */
    public UUID getUUID() { return uuid; }

    /**
     * 
     * @return
     */
    public GameInfo getInfo() { return info; }

    // Handling textObjects
    /**
     * 
     * @return
     */
    public ArrayList<TextObject> getTextObjects() {
        if (textObjects.isEmpty()) {
            return null;  
        }
        return textObjects;
    }

    public ArrayList<UUID> getTextObjectUUIDs() {
        if (textObjects.isEmpty()) {
            return null;
        }
        return textObjects.stream().map(TextObject::getUUID).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * 
     * @param textObjectUUID
     * @return
     */
    public TextObject getTextObject(UUID textObjectUUID) {
        if (textObjects.isEmpty()) {
            return null;  
        }
        for(TextObject textObject : textObjects) {
            if(textObject.getUUID() == textObjectUUID) {
                return textObject;
            }
        }
        return null; 
    }

    /**
     * 
     * @return
     */
    public TextObject getRandomTextObject() {
        if (textObjects.isEmpty()) {
            return null;  
        }
        Random random = new Random();
        return textObjects.get(random.nextInt(textObjects.size()));  
    }

    /**
     * 
     * @param textObjectUUID
     * @return
     */
    public TextObject getNextTextObject(UUID textObjectUUID) {
        if (textObjects.isEmpty()) {
            return null;  
        }

        for (int i = 0; i < textObjects.size(); i++) {
            if (textObjects.get(i).getUUID().equals(textObjectUUID)) {
                int nextIndex = (i + 1) % textObjects.size();
                return textObjects.get(nextIndex);
            }
        }
        return null; // UUID not found
    }

    /**
     * Used to load the Game data from JSON
     */
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

    /**
     * toString method for debugging
     */
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
