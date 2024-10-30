package com.languageLearner.game;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader {

    // Singleton
    // Clear all ArrayLists/HashMaps before loading
    private DataLoader() {

    }

    // Method to load all games from a JSON file
    public static ArrayList<Game> loadGames(String filePath) {
        ArrayList<Game> games = new ArrayList<>();
        JSONParser parser = new JSONParser();
        
        try (FileReader reader = new FileReader(filePath)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            String language = (String) jsonObject.get("LANG");  // Retrieves the language as a String
            UUID langUUID = UUID.fromString((String) jsonObject.get("UUID"));  // Retrieves UUID as a String and converts to UUID

            JSONArray gamesArray = (JSONArray) jsonObject.get("GAMES");

            for (Object gameObj : gamesArray) {
                JSONObject gameJson = (JSONObject) gameObj;
                Game game = Game.fromJson(gameJson, language, langUUID);
                games.add(game);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return games;
    }

    // // Method to load all questions from a JSON file
    // public static List<Question> loadQuestions(String filePath) {
    //     List<Question> questions = new ArrayList<>();
    //     JSONParser parser = new JSONParser();
        
    //     try (FileReader reader = new FileReader(filePath)) {
    //         JSONObject jsonObject = (JSONObject) parser.parse(reader);
    //         JSONArray questionsArray = (JSONArray) jsonObject.get("questions");

    //         for (Object questionObj : questionsArray) {
    //             JSONObject questionJson = (JSONObject) questionObj;
    //             Question question = Question.fromJson(questionJson);
    //             questions.add(question);
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
        
    //     return questions;
    // }
}
