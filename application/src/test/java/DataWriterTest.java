import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.learner.game.User;
import com.learner.game.UserList;
import com.learner.game.loadwrite.DataWriter;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileReader;

class DataWriterTest {

    private static final String TEST_OUTPUT_FILE = "test-output/userData.json";

    @BeforeEach
    void setUp() {
        // Ensure UserList is cleared before each test
        UserList.getInstance().getUsers().clear();
    }

    @Test
    void testWriteUserData() {
        // Set up sample data
        UserList userList = UserList.getInstance();
        User user = new User("testuser@example.com", "testuser", "Test User", "testpassword");
        userList.addUser(user);

        // Write user data to JSON
        DataWriter.writeUserData(TEST_OUTPUT_FILE);

        // Verify written file
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(TEST_OUTPUT_FILE)) {
            JSONObject jsonData = (JSONObject) parser.parse(reader);
            JSONArray usersArray = (JSONArray) jsonData.get("users");

            assertEquals(1, usersArray.size(), "Output JSON should contain 1 user");

            JSONObject userJson = (JSONObject) usersArray.get(0);
            assertEquals("testuser@example.com", userJson.get("email"));
            assertEquals("testuser", userJson.get("username"));
            assertEquals("Test User", userJson.get("displayname"));
            assertEquals("testpassword", userJson.get("password"));
        } catch (Exception e) {
            fail("Exception thrown while verifying output file: " + e.getMessage());
        }
    }
}
