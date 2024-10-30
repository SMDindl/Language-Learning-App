package com.languageLearner.game;

import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataLoader {

    private final GameData gameData = GameData.getInstance();

    public void loadData(String jsonString) {
        JSONParser parser = new JSONParser();

        try {
            // Parse the JSON string into a JSONObject
            JSONObject jsonData = (JSONObject) parser.parse(jsonString);

            // Get the "LANGUAGES" array
            JSONArray languagesArray = (JSONArray) jsonData.get("LANGUAGES");

            for (Object languageObj : languagesArray) {
                JSONObject language = (JSONObject) languageObj;

                // Retrieve language and UUID
                String languageName = (String) language.get("LANG");
                String languageUUIDString = (String) language.get("UUID");

                if (languageName == null || languageUUIDString == null) continue;
                UUID languageUUID = UUID.fromString(languageUUIDString);

                // Add language to GameData
                gameData.addLanguage(languageUUID, languageName);

                // Process GAMES array within each language
                JSONArray gamesArray = (JSONArray) language.get("GAMES");
                if (gamesArray != null) {
                    loadGames(gamesArray, languageUUID, languageName);
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void loadGames(JSONArray gamesArray, UUID languageUUID, String languageName) {
        for (Object gameObj : gamesArray) {
            JSONObject game = (JSONObject) gameObj;

            // Retrieve game details
            String gameName = (String) game.get("GAME");
            String difficulty = (String) game.get("DIFF");
            String gameUUIDString = (String) game.get("UUID");

            if (gameName == null || difficulty == null || gameUUIDString == null) continue;
            UUID gameUUID = UUID.fromString(gameUUIDString);

            // Parse GameInfo if present
            JSONObject infoObj = (JSONObject) game.get("INFO");
            GameInfo gameInfo = null;
            if (infoObj != null) {
                gameInfo = parseGameInfo(infoObj, gameUUID);
                if (gameInfo != null) {
                    gameData.addGameInfo(gameInfo); // Add GameInfo to GameData
                }
            }

            // Parse TEXT array and add each TextObject to GameData
            JSONArray textArray = (JSONArray) game.get("TEXT");
            ArrayList<TextObject> textObjects = new ArrayList<>();
            if (textArray != null) {
                for (Object textObj : textArray) {
                    JSONObject text = (JSONObject) textObj;
                    TextObject textObject = parseTextObject(text, gameUUID);
                    if (textObject != null) {
                        textObjects.add(textObject);
                        gameData.addTextObject(textObject); // Add TextObject to GameData
                    }
                }
            }

            // Create Game object
            Game newGame = new Game(languageName, languageUUID, gameName, difficulty, gameUUID, gameInfo, textObjects);
            gameData.addGame(newGame); // Add Game to GameData
        }
    }

    private GameInfo parseGameInfo(JSONObject infoObj, UUID gameUUID) {
        // Parse fields from the INFO object
        String description = (String) infoObj.get("description");
        String objective = (String) infoObj.get("objective");
        JSONArray instructionsArray = (JSONArray) infoObj.get("instructions");

        // Convert instructions JSONArray to ArrayList<String>
        ArrayList<String> instructions = new ArrayList<>();
        if (instructionsArray != null) {
            for (Object instruction : instructionsArray) {
                instructions.add(instruction.toString());
            }
        }

        String introConcept = (String) ((JSONObject) infoObj.get("prep")).get("introConcept");
        String exampleUsage = (String) ((JSONObject) infoObj.get("prep")).get("exampleUsage");
        String gameTip = (String) ((JSONObject) infoObj.get("prep")).get("gameTip");

        // Create and return GameInfo
        GameInfo gameInfo = new GameInfo(description, objective, instructions, introConcept, exampleUsage, gameTip, gameUUID);
        return gameInfo;
    }

    private TextObject parseTextObject(JSONObject textObj, UUID gameUUID) {
        // Parse fields for the TextObject
        String text = (String) textObj.get("text");
        String englishText = (String) textObj.get("englishText");
        String linkedText = (String) textObj.get("linkedText");
        String englishLinkedText = (String) textObj.get("englishLinkedText");
        String helperText = (String) textObj.get("helperText");
        String textUUIDString = (String) textObj.get("UUID");

        if (text == null || englishText == null || textUUIDString == null) return null;
        UUID textUUID = UUID.fromString(textUUIDString);

        // Create and return TextObject
        TextObject textObject = new TextObject(text, englishText, linkedText, englishLinkedText, helperText, textUUID, gameUUID);
        return textObject;
    }

    public static void main(String[] args) {
        DataLoader loader = new DataLoader();
        loader.loadDataFromFile("data.json"); 
    }
}
