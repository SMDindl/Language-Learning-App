package com.languageLearner.data;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter {

    private static final String USER_FILE = "data/json/UsersTest.json";

    /**
     * Writes all users from the UserList to the JSON file with proper formatting.
     */
    public void writeAllUsers() {
        UserList userList = UserList.getInstance();
        JSONObject jsonData = new JSONObject();
        JSONArray usersArray = new JSONArray();

        // Loop through each user in the UserList and create their JSON objects
        for (User user : userList.getUsers()) {
            JSONObject newUser = new JSONObject();
            newUser.put("uuid", user.getUuid().toString());
            newUser.put("username", user.getUsername());
            newUser.put("email", user.getEmail());
            newUser.put("displayName", user.getDisplayName());
            newUser.put("password", user.getPassword());

            // Now add progress trackers (this will appear last in the JSON)
            JSONArray trackersArray = new JSONArray();
            for (ProgressTracker tracker : user.getProgressTrackers()) {
                JSONObject trackerObj = new JSONObject();
                trackerObj.put("language", tracker.getLanguage());

                JSONArray completedGamesArray = new JSONArray();
                for (DataKey game : tracker.getCompletedGames()) {
                    completedGamesArray.add(game.toString());
                }
                trackerObj.put("completedGames", completedGamesArray);
                trackersArray.add(trackerObj);
            }

            // Ensure progressTrackers is added last
            newUser.put("progressTrackers", trackersArray);

            // Add the user object to the users array
            usersArray.add(newUser);
        }

        jsonData.put("users", usersArray);

        // Write the entire UserList to file with proper formatting
        try (FileWriter file = new FileWriter(USER_FILE)) {
            file.write(prettyPrintJSON(jsonData.toJSONString()));
            file.flush(); // Ensure all data is written before closing
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
                    // Toggle the quotes flag to handle text inside strings
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
