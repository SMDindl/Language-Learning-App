package com.languageLearner.game;

import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONObject;

/**
 * 
 */
public class GameInfo {
    
    private String description;
    private String objective;
    private ArrayList<String> instructions;
    private String introConcept;
    private String exampleUsage;
    private String gameTip;
    private UUID gameUUID;

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
    /**
     * 
     * @return UUID
     */
    public UUID getUUID() {
        return gameUUID;
    }

    // Methods to interact with game info...



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
        GameData.getInstance().addGameInfo(gameUUID, gameInfo);
        return gameInfo;
    }
}
