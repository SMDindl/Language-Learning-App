package com.languageLearner.data;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter {

    private static final String USER_FILE = "data/json/UsersTest.json";

    public void writeAllUsers() {
        UserList userList = UserList.getInstance();
        JSONObject jsonData = new JSONObject();
        JSONArray usersArray = new JSONArray();

        for (User user : userList.getUsers()) {
            JSONObject newUser = new JSONObject();
            newUser.put("uuid", user.getUuid().toString());
            newUser.put("username", user.getUsername());
            newUser.put("email", user.getEmail());
            newUser.put("displayName", user.getDisplayName());
            newUser.put("password", user.getPassword()); // Consider security implications for storing passwords

            // Create a map to store unique trackers by language
            Map<String, ProgressTracker> uniqueTrackers = new HashMap<>();
            for (ProgressTracker tracker : user.getProgressTrackers()) {
                uniqueTrackers.put(tracker.getLanguage(), tracker); // Only keep one tracker per language
            }

            // Add each unique tracker to the JSON array
            JSONArray trackersArray = new JSONArray();
            for (ProgressTracker tracker : uniqueTrackers.values()) {
                JSONObject trackerObj = new JSONObject();
                trackerObj.put("language", tracker.getLanguage());

                // Store completed games
                JSONArray completedGamesArray = new JSONArray();
                for (DataKey game : tracker.getCompletedGames()) {
                    completedGamesArray.add(game.toString());
                }
                trackerObj.put("completedGames", completedGamesArray);

                // Store missed questions
                JSONArray missedQuestionsArray = new JSONArray();
                for (Question question : tracker.getMissedQuestions()) {
                    JSONObject missedQuestion = new JSONObject();
                    missedQuestion.put("questionUUID", question.getId().toString());

                    // Only include wordUUIDs for matching questions
                    if (question.getType().equals("matching")) {
                        JSONArray wordUUIDs = new JSONArray();
                        for (UUID wordId : question.getWordUUIDs()) {
                            wordUUIDs.add(wordId.toString());
                        }
                        missedQuestion.put("wordUUIDs", wordUUIDs);
                    }

                    missedQuestionsArray.add(missedQuestion);
                }
                trackerObj.put("missedQuestions", missedQuestionsArray);
                trackersArray.add(trackerObj);
            }

            newUser.put("progressTrackers", trackersArray);
            usersArray.add(newUser);
        }

        jsonData.put("users", usersArray);
        try (FileWriter file = new FileWriter(USER_FILE)) {
            file.write(prettyPrintJSON(jsonData.toJSONString()));
            file.flush();
            System.out.println("All users written to " + USER_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper method to pretty-print JSON with proper indentation and new lines.
     */
    private String prettyPrintJSON(String jsonString) {
        StringBuilder prettyPrintedJson = new StringBuilder();
        int indentLevel = 0;
        boolean inQuotes = false;

        for (char charFromJson : jsonString.toCharArray()) {
            switch (charFromJson) {
                case '"':
                    inQuotes = !inQuotes;
                    prettyPrintedJson.append(charFromJson);
                    break;
                case '{':
                case '[':
                    prettyPrintedJson.append(charFromJson);
                    if (!inQuotes) {
                        prettyPrintedJson.append("\n");
                        indentLevel++;
                        prettyPrintedJson.append(indentString(indentLevel));
                    }
                    break;
                case '}':
                case ']':
                    if (!inQuotes) {
                        prettyPrintedJson.append("\n");
                        indentLevel--;
                        prettyPrintedJson.append(indentString(indentLevel));
                    }
                    prettyPrintedJson.append(charFromJson);
                    break;
                case ',':
                    prettyPrintedJson.append(charFromJson);
                    if (!inQuotes) {
                        prettyPrintedJson.append("\n");
                        prettyPrintedJson.append(indentString(indentLevel));
                    }
                    break;
                case ':':
                    prettyPrintedJson.append(charFromJson);
                    if (!inQuotes) {
                        prettyPrintedJson.append(" ");
                    }
                    break;
                default:
                    prettyPrintedJson.append(charFromJson);
                    break;
            }
        }

        return prettyPrintedJson.toString();
    }

    /**
     * Helper method to generate an indent string based on the current indentation level.
     */
    private String indentString(int indentLevel) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < indentLevel; i++) {
            indent.append("    "); // Four spaces for each level of indentation
        }
        return indent.toString();
    }
}
