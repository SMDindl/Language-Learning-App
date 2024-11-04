import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.learner.game.User;
import com.learner.game.UserList;
import com.learner.game.loadwrite.DataWriter;
import com.learner.game.questions.MatchingQuestion;

public class DataWriterTest {

    private UserList userList;

    @BeforeEach
    public void setUp() {
        userList = UserList.getInstance();
        userList.clearUsers();
    }

    @Test
    public void testSingleUserMinimalData() {
        User user = createUser("minimal@example.com", "minimalUser", "Minimal Display", "123");
        userList.addUser(user);

        verifyDataWriterOutput("testSingleUserMinimalData", 1, "minimal@example.com", "minimalUser", "Minimal Display", "123");
    }

    @Test
    public void testMultipleUsersFullData() {
        User user1 = createUserWithProgress("user1@example.com", "user1", "User One", "pass1", "English");
        User user2 = createUserWithProgress("user2@example.com", "user2", "User Two", "pass2", "French");

        userList.addUser(user1);
        userList.addUser(user2);

        verifyDataWriterOutput("testMultipleUsersFullData", 2);
    }

    @Test
    public void testEmptyUserList() {
        verifyDataWriterOutput("testEmptyUserList", 0);
    }

    @Test
    public void testLongStringsSpecialCharacters() {
        User user = createUser("long@example.com", "longUser_!@#", "Long Display Name with Special Characters", "longpassword@98389853899583985389598985985398983598#");
        userList.addUser(user);

        verifyDataWriterOutput("testLongStringsSpecialCharacters", 1, "long@example.com", "longUser_!@#", "Long Display Name with Special Characters", "longpassword@98389853899583985389598985985398983598#");
    }

    @Test
    public void testMaxProgressTrackersAndQuestions() {
        User user = createUserWithMaxData("max@example.com", "maxUser", "Max Display", "maxpass", "English", 10);
        userList.addUser(user);

        verifyDataWriterOutput("testMaxProgressTrackersAndQuestions", 1);
    }

    // Helper method for testing data output
    private void verifyDataWriterOutput(String testName, int expectedUserCount, String... expectedValues) {
        try {
            // Create a temporary file for testing
            Path tempFilePath = Files.createTempFile(testName, ".json");
            DataWriter.writeUserData(tempFilePath.toString());

            // Read and parse the file contents
            String jsonString = Files.readString(tempFilePath);
            JSONArray usersArray = (JSONArray) new JSONParser().parse(jsonString);

            // Check the number of users in JSON matches the expected count
            assertEquals(expectedUserCount, usersArray.size(), "User count mismatch.");

            // Verify specific values if provided
            if (expectedValues.length > 0) {
                JSONObject userJson = (JSONObject) usersArray.get(0);
                assertEquals(expectedValues[0], userJson.get("email"));
                assertEquals(expectedValues[1], userJson.get("username"));
                assertEquals(expectedValues[2], userJson.get("displayname"));
                assertEquals(expectedValues[3], userJson.get("password"));
            }

            // Additional validation for full data
            if (expectedUserCount > 0) {
                for (Object userObj : usersArray) {
                    JSONObject userJson = (JSONObject) userObj;
                    assertTrue(userJson.containsKey("progressTrackers"), "Missing progressTrackers key");
                    JSONArray trackers = (JSONArray) userJson.get("progressTrackers");

                    // Verify nested data within progressTrackers
                    for (Object trackerObj : trackers) {
                        JSONObject trackerJson = (JSONObject) trackerObj;
                        assertTrue(trackerJson.containsKey("completedGames"), "Missing completedGames in tracker");
                        assertTrue(trackerJson.containsKey("missedQuestions"), "Missing missedQuestions in tracker");
                    }
                }
            }

            // Clean up the temporary file after the test
            Files.delete(tempFilePath);

        } catch (IOException | org.json.simple.parser.ParseException e) {
            fail("Exception thrown during " + testName + ": " + e.getMessage());
        }
    }

    // Helper methods to create test data
    private User createUser(String email, String username, String displayName, String password) {
        return new User(email, username, displayName, password);
    }

    private User createUserWithProgress(String email, String username, String displayName, String password, String language) {
        User user = createUser(email, username, displayName, password);
        UUID languageUUID = UUID.randomUUID();
        User.ProgressTracker tracker = user.new ProgressTracker(languageUUID, language);
        user.addProgressTracker(tracker);
        return user;
    }

    private User createUserWithMaxData(String email, String username, String displayName, String password, String language, int maxQuestions) {
        User user = createUserWithProgress(email, username, displayName, password, language);
        User.ProgressTracker tracker = user.getProgressTracker(user.getProgressTrackers().iterator().next().getUUID());

        for (int i = 0; i < maxQuestions; i++) {
            tracker.addCompletedGame(UUID.randomUUID());
            tracker.addMissedQuestion(new MatchingQuestion(UUID.randomUUID()));
        }
        return user;
    }
}
