package com.languageLearner.data;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class UserLoader extends DataConstants {

    public void loadUsers() {
        UserList userList = UserList.getInstance();
        userList.clearUsers(); // Clear current user list before loading new users

        try {
            // Step 1: Read the file and parse the JSON
            FileReader reader = new FileReader(USER_FILE); // Use constant USER_FILE
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(reader);

            // // Step 2: Debugging - Print the entire JSON data
            // System.out.println("Entire JSON Data: " + jsonData.toJSONString());

            JSONArray usersArray = (JSONArray) jsonData.get(USERS); // Use constant USERS

            // Step 3: Loop through each user object
            for (Object userObj : usersArray) {
                JSONObject userJSON = (JSONObject) userObj;

                // Debugging: Print the entire user object
                System.out.println("User Object: " + userJSON.toJSONString());

                // Debugging: Print the UUID field to ensure it is not null
                System.out.println("Reading user UUID: " + userJSON.get(USER_ID));
                System.out.println();

                String uuidStr = (String) userJSON.get(USER_ID);
                UUID uuid;

                if (uuidStr == null || uuidStr.isEmpty()) {
                    System.err.println("Error: UUID is null or empty for user: " + userJSON.get(USERNAME));
                    continue; // Skip this user if the UUID is null
                } else {
                    // Attempt to parse the UUID
                    try {
                        uuid = UUID.fromString(uuidStr);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Error: Invalid UUID format for user: " + userJSON.get(USERNAME));
                        continue; // Skip this user if the UUID is invalid
                    }
                }

                String username = (String) userJSON.get(USERNAME);
                String email = (String) userJSON.get(EMAIL);
                String displayName = (String) userJSON.get(DISPLAY_NAME);
                String password = (String) userJSON.get(PASSWORD);

                // Create a new User object
                User user = new User(email, username, displayName, password, UUID.fromString(uuidStr));

                // Extract and load progress trackers
                JSONArray trackersArray = (JSONArray) userJSON.get(PROGRESS_TRACKERS); // Use constant PROGRESS_TRACKERS
                if (trackersArray != null) {
                    for (Object trackerObj : trackersArray) {
                        JSONObject trackerJSON = (JSONObject) trackerObj;

                        String language = (String) trackerJSON.get(LANGUAGE); // Use constant LANGUAGE
                        JSONArray completedGamesArray = (JSONArray) trackerJSON.get(COMPLETED_GAMES); // Use constant COMPLETED_GAMES

                        ArrayList<DataKey> completedGames = new ArrayList<>();
                        for (Object gameObj : completedGamesArray) {
                            // Assuming gameObj.toString() contains something like "easy-colorsGame"
                            String[] gameKeyParts = gameObj.toString().split("-");

                            if (gameKeyParts.length == 2) {
                                String difficulty = gameKeyParts[0]; // Extract difficulty
                                String gameType = gameKeyParts[1];  // Extract game type

                                // Create the DataKey using the extracted components
                                DataKey gameKey = DataKey.getInstance(language, gameType, difficulty);

                                // Add to completed games
                                completedGames.add(gameKey);
                            } else {
                                System.err.println("Invalid game key format: " + gameObj.toString());
                            }
                        }

                        ProgressTracker tracker = new ProgressTracker(language, completedGames);
                        user.addProgressTracker(tracker);
                    }
                }

                // Add the user to the user list
                userList.addUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
