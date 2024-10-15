package com.languageLearner.data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class DataWriter {

    private static final String USER_FILE = "data/json/UsersTest.json";

    /**
     * Writes all users from the UserList to the JSON file.
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

            // Add progress trackers
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
            newUser.put("progressTrackers", trackersArray);

            // Add the user object to the users array
            usersArray.add(newUser);
        }
        jsonData.put("users", usersArray);

        // Write the entire UserList to file
        try (FileWriter file = new FileWriter(USER_FILE)) {
            file.write(jsonData.toJSONString());
            System.out.println("All users written to " + USER_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
