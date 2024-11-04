package com.learner.game.loadwrite;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.learner.game.User;
import com.learner.game.UserList;
import com.learner.game.questions.Question;

public class DataWriter {

     /**
     * Writes user data to a specified JSON file path
     * @param filePath the file path of the JSON file to save user data to
     */
    @SuppressWarnings({"unchecked", "CallToPrintStackTrace"})
    public static void writeUserData(String filePath) {
        UserList userList = UserList.getInstance();
        JSONArray usersArray = new JSONArray();

        for (User user : userList.getUsers()) {
            JSONObject userJson = new JSONObject();
            userJson.put("uuid", user.getUUID().toString());
            userJson.put("email", user.getEmail());
            userJson.put("username", user.getUsername());
            userJson.put("displayname", user.getDisplayName());
            userJson.put("password", user.getPassword());

            // Progress trackers
            JSONArray progressTrackersArray = new JSONArray();
            for (User.ProgressTracker tracker : user.getProgressTrackers()) {
                JSONObject trackerJson = new JSONObject();
                trackerJson.put("languageUUID", tracker.getUUID().toString());
                trackerJson.put("languageName", tracker.getLanguageName());

                // Completed games (store as UUIDs)
                JSONArray completedGamesArray = new JSONArray();
                for (UUID gameUUID : tracker.getCompletedGames()) {
                    JSONObject gameJson = new JSONObject();
                    gameJson.put("gameUUID", gameUUID.toString());
                    completedGamesArray.add(gameJson);
                }
                trackerJson.put("completedGames", completedGamesArray);

                // Missed questions
                JSONArray missedQuestionsArray = new JSONArray();
                for (Question question : tracker.getMissedQuestions()) {
                    JSONObject questionJson = new JSONObject();
                    questionJson.put("uuid", question.getUUID().toString());
                    questionJson.put("questionType", question.getQuestionType().name());
                    missedQuestionsArray.add(questionJson);
                }
                trackerJson.put("missedQuestions", missedQuestionsArray);

                progressTrackersArray.add(trackerJson);
            }
            userJson.put("progressTrackers", progressTrackersArray);

            usersArray.add(userJson);
        }

        // Use StringWriter and JSONWriter for pretty-printing
        try (FileWriter file = new FileWriter(filePath);
             StringWriter stringWriter = new StringWriter()) {

            stringWriter.write(usersArray.toJSONString());

            // Write the formatted JSON string to the file
            file.write(formatJson(stringWriter.toString()));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper method to format JSON string for pretty printing
     * @param jsonString the JSON string to format
     * @return the formatted JSON string
     */
    private static String formatJson(String jsonString) {
        StringBuilder prettyJsonBuilder = new StringBuilder();
        String indent = "";
        boolean inQuote = false;

        for (char charFromJson : jsonString.toCharArray()) {
            switch (charFromJson) {
                case '"' -> {
                    inQuote = !inQuote;
                    prettyJsonBuilder.append(charFromJson);
                }
                case '{', '[' -> {
                    prettyJsonBuilder.append(charFromJson);
                    if (!inQuote) {
                        prettyJsonBuilder.append("\n").append(indent).append("  ");
                        indent += "  ";
                    }
                }
                case '}', ']' -> {
                    if (!inQuote) {
                        indent = indent.substring(0, indent.length() - 2);
                        prettyJsonBuilder.append("\n").append(indent);
                    }
                    prettyJsonBuilder.append(charFromJson);
                }
                case ',' -> {
                    prettyJsonBuilder.append(charFromJson);
                    if (!inQuote) {
                        prettyJsonBuilder.append("\n").append(indent);
                    }
                }
                default -> prettyJsonBuilder.append(charFromJson);
            }
        }
        return prettyJsonBuilder.toString();
    }
}