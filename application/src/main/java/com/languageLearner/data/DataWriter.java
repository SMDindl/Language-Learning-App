package com.languageLearner.data;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataWriter {

    private static final String USER_FILE_PATH = "data/json/Users.json";

    /**
     * Method to write a new user to the JSON file. 
     * The method loads existing users, adds a new user, and writes the updated data back to the file.
     * @param userName The username of the user.
     * @param displayName The display name of the user.
     * @param email The email of the user.
     * @param password The password of the user.
     */
    public void writeUserData(String userName, String displayName, String email, String password) {
        // Load existing users from the JSON file
        JSONObject jsonData = loadJSONData(USER_FILE_PATH);
        JSONArray usersArray = (JSONArray) jsonData.get("users");
        if (usersArray == null) {
            usersArray = new JSONArray();
        }

        // Create a new user object
        JSONObject newUser = new JSONObject();
        newUser.put("username", userName);
        newUser.put("displayName", displayName);
        newUser.put("email", email);
        newUser.put("password", password);
        newUser.put("progressTrackers", new JSONArray());

        // Add the new user to the array
        usersArray.add(newUser);

        // Update the JSON data with the new user
        jsonData.put("users", usersArray);

        // Write the updated data back to the file
        writeToFile(USER_FILE_PATH, jsonData);
    }

    /**
     * Helper method to load the existing JSON data from the file.
     * If the file does not exist or cannot be read, an empty JSON object is returned.
     * @param filePath The path to the JSON file.
     * @return The JSONObject containing the data loaded from the file.
     */
    private JSONObject loadJSONData(String filePath) {
        JSONParser parser = new JSONParser();
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            return (JSONObject) parser.parse(content); // Use JSONParser to parse the string
        } catch (IOException | ParseException e) {
            System.err.println("Error reading JSON file: " + filePath);
            e.printStackTrace();
            return new JSONObject(); // Return an empty JSON object if the file is not found or unreadable
        }
    }

    /**
     * General method for writing a JSONObject to a file.
     * This method will overwrite the contents of the file with the provided JSON data.
     * @param fileName The name of the file to write to.
     * @param data The JSONObject data to write to the file.
     */
    private void writeToFile(String fileName, JSONObject data) {
        try (FileWriter file = new FileWriter(fileName, false)) { // 'false' to overwrite the file
            file.write(data.toJSONString()); // Use toJSONString() instead of toString() for JSON-simple
            file.write(System.lineSeparator()); // Add a new line for readability
            System.out.println("Successfully wrote user data to: " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + fileName);
            e.printStackTrace();
        }
    }
}
