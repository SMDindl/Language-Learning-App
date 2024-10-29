package com.languageLearner.Game;

public class GameInfo {
    private String description;
    private String objective;
    private List<String> instructions;
    private String introConcept;
    private String exampleUsage;
    private String gameTip;

    public GameInfo(String description, String objective, List<String> instructions, 
                    String introConcept, String exampleUsage, String gameTip) {
        this.description = description;
        this.objective = objective;
        this.instructions = instructions;
        this.introConcept = introConcept;
        this.exampleUsage = exampleUsage;
        this.gameTip = gameTip;
    }

    public static GameInfo fromJson(JSONObject infoJson) {
        String description = (String) infoJson.get("description");
        String objective = (String) infoJson.get("objective");
        List<String> instructions = (List<String>) infoJson.get("instructions");

        JSONObject prepJson = (JSONObject) infoJson.get("prep");
        String introConcept = (String) prepJson.get("introConcept");
        String exampleUsage = (String) prepJson.get("exampleUsage");
        String gameTip = (String) prepJson.get("gameTip");

        return new GameInfo(description, objective, instructions, introConcept, exampleUsage, gameTip);
    }
}
