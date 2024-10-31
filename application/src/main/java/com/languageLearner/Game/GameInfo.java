package com.languageLearner.game;

import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONObject;

/**
 * GameInfo <-- Game (has difficulty) <--- Language
 */
public class GameInfo {
    
    private final String description;
    private final String objective;
    private final ArrayList<String> instructions;
    private final String introConcept;
    private final String exampleUsage;
    private final String gameTip;
    private final UUID gameUUID;

    /**
     * 
     * @param description
     * @param objective
     * @param instructions
     * @param introConcept
     * @param exampleUsage
     * @param gameTip
     * @param gameUUID
     */
    public GameInfo(String description, String objective, ArrayList<String> instructions, 
                    String introConcept, String exampleUsage, String gameTip, UUID gameUUID) {

        this.description = description;
        this.objective = objective;
        this.instructions = instructions;
        this.introConcept = introConcept;
        this.exampleUsage = exampleUsage;
        this.gameTip = gameTip;
        this.gameUUID = gameUUID;
    }

    // UUID getters
    public UUID getGameUUID() {
        return gameUUID;
    }

    // Methods to interact with game info...
    public String instructionsToString() {
        String s = "";
        int i = 1;
        for(String intruc : instructions) {
            s += i++ + ") " + intruc + " \n";
        }
        return s;
    }


    // Used to load from json, will be accessed in dataloader
    @SuppressWarnings("unchecked")
    public static GameInfo fromJson(JSONObject infoJson, UUID gameUUID) {
        String description = (String) infoJson.get("description");
        String objective = (String) infoJson.get("objective");
        ArrayList<String> instructions = (ArrayList<String>) infoJson.get("instructions");

        JSONObject prepJson = (JSONObject) infoJson.get("prep");
        String introConcept = (String) prepJson.get("introConcept");
        String exampleUsage = (String) prepJson.get("exampleUsage");
        String gameTip = (String) prepJson.get("gameTip");

        GameInfo gameInfo = new GameInfo(description, objective, instructions, introConcept, exampleUsage, gameTip, gameUUID);
        return gameInfo;
    }

    @Override
    public String toString() {
        return "\n---- ALL GAME INFO ----\n\n" 
            + "Description:\n"
            + description + "\n\n"
            + "Objective:\n" 
            +  objective + "\n\n"
            + "Intructions List:\n" 
            + instructionsToString() + "\n"  
            + "Intro Concept:\n" 
            + introConcept + "\n\n" 
            + "Example:\n" 
            + exampleUsage + "\n\n" 
            + "Tip:\n" 
            + gameTip + "\n\n"
            + "GameUUID:\n" 
            + gameUUID + "\n\n"
            + "--- END OF ALL GAME INFO --- \n";
    }
    
}
