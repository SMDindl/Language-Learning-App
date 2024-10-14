import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataWriter {

    private static final String USER_FILE_PATH = "data/json/users.json";

    public void writeUserData(String userName, String displayName, String email, String password) {
        List<String> usersData = loadUsersData(USER_FILE_PATH);
        
        // Create a new user JSON string
        String newUser = createUserJson(userName, displayName, email, password);
        
        // If the file is empty, initialize the users array
        if (usersData.isEmpty()) {
            usersData.add("{\"users\":[");
        } else {
            // Append new user with a comma
            usersData.set(0, usersData.get(0).replace("]", ","));
        }
        
        // Add the new user JSON
        usersData.add(newUser);
        
        // Finalize the JSON structure
        writeToFile(USER_FILE_PATH, usersData);
    }

    private List<String> loadUsersData(String filePath) {
        List<String> usersData = new ArrayList<>();
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            if (!content.isEmpty()) {
                usersData.add(content.trim());
            }
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + filePath);
            e.printStackTrace();
        }
        return usersData;
    }
           // format of the Writer
    private String createUserJson(String userName, String displayName, String email, String password) {
        String uuid = UUID.randomUUID().toString();
        return String.format("{\"uuid\":\"%s\",\"username\":\"%s\",\"displayName\":\"%s\",\"email\":\"%s\",\"password\":\"%s\",\"progressTrackers\":[]}",
                             uuid, userName, displayName, email, password);
    }

    private void writeToFile(String fileName, List<String> usersData) {
        try (FileWriter file = new FileWriter(fileName, false)) {
            // If there are multiple users, concatenate them correctly
            if (usersData.size() > 1) {
                StringBuilder finalOutput = new StringBuilder(usersData.get(0));
                for (int i = 1; i < usersData.size(); i++) {
                    finalOutput.append(usersData.get(i));
                }
                finalOutput.append("]}"); // Close the users array
                file.write(finalOutput.toString());
            } else {
                // If only one user, close the array correctly
                file.write(usersData.get(0) + "]}");
            }
            System.out.println("Successfully wrote user data to: " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + fileName);
            e.printStackTrace();
        }
    }
}
