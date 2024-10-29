package com.languageLearner.app;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;

public class Question {

    // Multiple Choice(includes true/false so this is two question types)
    // this is loaded from the gamesQuestions json
    public Question(String question, ArrayList<String> Choices) {
        
    }

    // Fill in the blank - this is created from a single textObject
    // created from the gamesData json textObjects
    public Question(String sentence, String answer) {
        
    }

    // Matching - this is created from mutiple textObjects
    // created from the gamesData json textObjects
    public Question(HashMap<String, String> match) {
        
    }

    // Matching - this is created from mutiple textObjects
    // created from the gamesData json textObjects
    public Question(ArrayList<String> sequencing) {
        
    }

    public static void fromJson(JSONObject gameJson) {

    }









    
}
