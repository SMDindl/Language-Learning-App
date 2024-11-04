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
import com.learner.game.User;
import com.learner.game.UserList;
import com.learner.game.innerdata.GameInfo;
import com.learner.game.innerdata.TextObject;
import com.learner.game.questions.MultipleChoiceQuestion;
import com.learner.game.questions.Question;

public class DataLoader {

    private static final GameManager gameManager = GameManager.getInstance();
    private static final UserList userList = UserList.getInstance();

    @SuppressWarnings("CallToPrintStackTrace")
    public static void loadGameData(String gameDataFilePath) {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(gameDataFilePath)) {
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
            System.out.println("Failed to load game data");
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

            MultipleChoiceQuestion question = new MultipleChoiceQuestion(questionUUID, gameUUID, languageUUID, questionText, options);
            questions.add(question);
        }
        return questions;  
    }

    /**
     * Loads user data from a specified JSON file path
     * @param userFilePath the file path of the JSON file to load users from
     */
    @SuppressWarnings("CallToPrintStackTrace")
    public static void loadUserData(String userFilePath) {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(userFilePath)) {
            // Parse JSON data
            JSONObject jsonData = (JSONObject) parser.parse(reader);
            JSONArray usersArray = (JSONArray) jsonData.get("users");

            // Check if "users" key is present in JSON
            if (usersArray == null) {
                System.out.println("No users found in the JSON file.");
                return;
            }

            for (Object userObj : usersArray) {
                JSONObject userJson = (JSONObject) userObj;
                UUID uuid = UUID.fromString((String) userJson.get("uuid"));
                String email = (String) userJson.get("email");
                String username = (String) userJson.get("username");
                String displayName = (String) userJson.get("displayname");
                String password = (String) userJson.get("password");

                // Create a new User instance
                User user = new User(email, username, displayName, password, uuid);

                // Load progress trackers for each user
                JSONArray progressTrackersArray = (JSONArray) userJson.get("progressTrackers");
                if (progressTrackersArray != null) {
                    for (Object trackerObj : progressTrackersArray) {
                        JSONObject trackerJson = (JSONObject) trackerObj;
                        UUID languageUUID = UUID.fromString((String) trackerJson.get("languageUUID"));
                        String languageName = (String) trackerJson.get("languageName");

                        // Initialize a new ProgressTracker
                        User.ProgressTracker tracker = user.new ProgressTracker(languageUUID, languageName);

                        // Load completed games as UUIDs
                        JSONArray completedGamesArray = (JSONArray) trackerJson.get("completedGames");
                        if (completedGamesArray != null) {
                            for (Object gameObj : completedGamesArray) {
                                JSONObject gameJson = (JSONObject) gameObj;
                                UUID gameUUID = UUID.fromString((String) gameJson.get("gameUUID"));
                                tracker.addCompletedGame(gameUUID);
                            }
                        }

                        // Load missed questions
                        JSONArray missedQuestionsArray = (JSONArray) trackerJson.get("missedQuestions");
                        if (missedQuestionsArray != null) {
                            for (Object questionObj : missedQuestionsArray) {
                                JSONObject questionJson = (JSONObject) questionObj;
                                UUID questionUUID = UUID.fromString((String) questionJson.get("uuid"));
                                String questionType = (String) questionJson.get("questionType");

                                // Find the question in the GameManager
                                Question question = gameManager.findQuestionInGame(tracker.getUUID(), questionUUID);

                                // Ensure question exists and matches the type before adding to tracker
                                if (question != null && question.getQuestionType().name().equalsIgnoreCase(questionType)) {
                                    tracker.addMissedQuestion(question);
                                }
                            }
                        }

                        user.addProgressTracker(tracker);
                    }
                }
                userList.addUser(user); // Add the user to the UserList
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("\u001B[33m").append("DATA LOADER TO STRING:\n\n").append("\u001B[0m"); // prints in yellow
        sb.append(gameManager.toString());
        sb.append(userList.toString());
        sb.append("\u001B[33m").append("END OF DATA LOADER TO STRING\n\n").append("\u001B[0m"); // prints in yellow
        
        return sb.toString();
    }

    public static void main(String[] args) {
        loadGameData(DataConstants.GAME_DATA_FILE); 
        loadUserData(DataConstants.USER_FILE);

        System.out.println(gameManager.toString());
        System.out.println(userList.toString());

    }
}
