package com.languageLearner.game;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader {

    // Method to load all games from a JSON file
    public static ArrayList<Game> loadGames(String filePath) {
        ArrayList<Game> games = new ArrayList<>();
        JSONParser parser = new JSONParser();
        
        try (FileReader reader = new FileReader(filePath)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray gamesArray = (JSONArray) jsonObject.get("GAMES");

            for (Object gameObj : gamesArray) {
                JSONObject gameJson = (JSONObject) gameObj;
                Game game = Game.fromJson(gameJson);
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
