package com.languageLearner.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.JSONObject;

/**
 * Represents a question
 * Questions will be added to a 
 */
public class Question {

    // uuids
    private UUID uuid;              // All questions will have their own respective uuid
    private UUID gameUUID;          // All questions will have a game UUID that they are attached to
    private UUID languageUUID;      // All questions will have a language UUID that they are attached to

    // Multiple choice / true-false values
    private String question;
    private ArrayList<String> Choices;

    // FITB values
    private String sentence;
    private String answer;

    // Matching value
    private HashMap<String, String> match; 

    // Sequencing value



    // Multiple Choice(includes true/false so this is two question types)
    // this is loaded from the gamesQuestions json
    public Question(String question, ArrayList<String> Choices, UUID uuid) {
        languageUUID = LanguageManager.getInstance().getAllLanguage()getGame(gameUUID).getLanguageUUID();
    }

    // Fill in the blank - this is created from a single textObject
    // created from the gamesData json textObjects
    public Question(String sentence, String answer, UUID uuid) {
        
    }

    // Matching - this is created from mutiple textObjects
    // created from the gamesData json textObjects
    public Question(HashMap<String, String> match, UUID uuid) {
        
    }

    // Sequencing - this is created from mutiple textObjects
    // created from the gamesData json textObjects
    public Question(ArrayList<String> sequencing, UUID uuid) {
        
    }

    public UUID getUUID() {
        return uuid;
    }

    public UUID getGameUUID() {
        return gameUUID;
    }

    public UUID getLanguageUUID() {
        return languageUUID;
    }


    // Loading in questions from the json
    public static void fromJson(JSONObject gameJson) {

    }

}
