package com.learner.game.loadwrite;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.learner.game.Difficulty;
import com.learner.game.Game;
import com.learner.game.GameManager;
import com.learner.game.Language;
import com.learner.game.innerdata.GameInfo;
import com.learner.game.innerdata.TextObject;
import com.learner.game.questions.MultipleChoiceQuestion;
import com.learner.game.questions.Question;

public class DataLoader {

    private static final GameManager gameManager = GameManager.getInstance();

    private final static String GAME_DATA_FILE = "json\\gamesData.json";

    @SuppressWarnings("CallToPrintStackTrace")
    public static void loadGameData() {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(GAME_DATA_FILE)) {
            JSONObject jsonData = (JSONObject) parser.parse(reader);
            JSONArray languagesArray = (JSONArray) jsonData.get("LANGUAGES");

            for (Object languageObj : languagesArray) {
                JSONObject languageJson = (JSONObject) languageObj;
                UUID languageUUID = UUID.fromString((String) languageJson.get("UUID"));
                String languageName = (String) languageJson.get("LANG");

                Language language = new Language(languageUUID, languageName);
                gameManager.initializeLanguage(language);

                JSONArray gamesArray = (JSONArray) languageJson.get("GAMES");
                for (Object gameObj : gamesArray) {
                    JSONObject gameJson = (JSONObject) gameObj;
                    UUID gameUUID = UUID.fromString((String) gameJson.get("UUID"));
                    String gameTitle = (String) gameJson.get("GAME");
                    Difficulty difficulty = Difficulty.valueOf(((String) gameJson.get("DIFF")).toUpperCase());

                    GameInfo gameInfo = GameInfo.fromJson((JSONObject) gameJson.get("INFO"), gameUUID);
                    ArrayList<TextObject> textObjects = parseTextObjects((JSONArray) gameJson.get("TEXT"), gameUUID);
                    ArrayList<Question> questions =  parseQuestions((JSONArray) gameJson.get("QUESTIONS"), gameUUID, languageUUID);

                    Game game = new Game(languageUUID, gameTitle, difficulty, gameUUID, gameInfo, textObjects, questions);
                    gameManager.addGame(game);

                }
            }
        } catch (IOException | ParseException e) {
            System.out.println("failed loading gamedata");
            e.printStackTrace();
        }
    }

    private static ArrayList<TextObject> parseTextObjects(JSONArray textArray, UUID gameUUID) {
        ArrayList<TextObject> textObjects = new ArrayList<>();
        for (Object textObj : textArray) {
            JSONObject textJson = (JSONObject) textObj;
            TextObject textObject = TextObject.fromJson(textJson, gameUUID);
            textObjects.add(textObject);
            gameManager.addTextObjectUUID(gameUUID, textObject.getUUID());
        }
        return textObjects;
    }

    // private static ArrayList<MultipleChoiceQuestion> parseQuestions(JSONArray questionsArray, UUID gameUUID, UUID languageUUID) {
    private static ArrayList<Question> parseQuestions(JSONArray questionsArray, UUID gameUUID, UUID languageUUID) {
        ArrayList<Question> questions = new ArrayList<>();
        for (Object questionObj : questionsArray) {
            JSONObject questionJson = (JSONObject) questionObj;
            UUID questionUUID = UUID.fromString((String) questionJson.get("UUID"));
            String questionText = (String) questionJson.get("question");

            JSONArray choicesJson = (JSONArray) questionJson.get("choices");
            ArrayList<String> options = new ArrayList<>();
            for (Object choice : choicesJson) {
                options.add((String) choice);
            }

            // Create the MultipleChoiceQuestion and add it to the list
            MultipleChoiceQuestion question = new MultipleChoiceQuestion(questionUUID, gameUUID, languageUUID, questionText, options);
            questions.add(question);
        }
        return questions;  
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("\u001B[33m").append("DATA LOADER TO STRING:\n\n").append("\u001B[0m"); // prints in yellow
        sb.append(gameManager.toString());
        sb.append("\u001B[33m").append("END OF DATA LOADER TO STRING\n\n").append("\u001B[0m"); // prints in yellow
        
        return sb.toString();
    }
    

    /**
     * DataLoader class tester
     * @param args
     */
    public static void main(String[] args) {
        
        loadGameData(); 

        // UUID id = UUID.fromString("1bafb0ae-3462-4ec3-9cc2-a98ff2898e72");

        // System.out.println(loader.toString());

        // System.out.println(gameManager.getEasyGames(id));

        System.out.println(gameManager.toString());

        // System.out.println(gameManager.getLanguageMap());

    }
}
