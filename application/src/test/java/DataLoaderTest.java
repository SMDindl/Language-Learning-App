import com.learner.game.UserList;
import com.learner.game.loadwrite.DataLoader;
import com.learner.game.*;
import com.learner.game.User;
import com.learner.game.GameManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

class DataLoaderTest {

    @BeforeEach
    void setUp() {
        // Clear any existing data in singleton instances for fresh tests
        UserList.getInstance().getUsers().clear();
        GameManager.getInstance().getAllGames().clear();
    }

    @Test
    void testLoadUserData() {
        DataLoader.loadUserData(); // Assuming it reads from test/resources/userData.json

        UserList userList = UserList.getInstance();
        assertEquals(1, userList.getUsers().size(), "User list should contain 1 user");

        User user = userList.getUsers().get(0);
        assertEquals("testuser@example.com", user.getEmail());
        assertEquals("testuser", user.getUsername());
        assertEquals("Test User", user.getDisplayName());
        assertEquals("testpassword", user.getPassword());

        // Check progress trackers
        assertEquals(1, user.getProgressTrackers().size(), "User should have 1 progress tracker");
        User.ProgressTracker tracker = user.getProgressTrackers().iterator().next();
        assertEquals("filipino", tracker.getLanguageName());
        assertEquals(1, tracker.getCompletedGames().size());
        assertEquals(1, tracker.getMissedQuestions().size());
    }

    @Test
    void testLoadGameData() {
        DataLoader.loadGameData(); // Assuming it reads from test/resources/gameData.json

        GameManager gameManager = GameManager.getInstance();
        assertEquals(1, gameManager.getAllGames().size(), "Game manager should contain 1 game");

        Game game = gameManager.findGameByUUID(UUID.fromString("d7b7b9ae-2a23-4312-8b72-7d5f9b7d8856"));
        assertNotNull(game, "Game should be loaded and not null");
        assertEquals("Sample Game", game.getGameTitle());
        assertEquals(Difficulty.EASY, game.getDifficulty());
    }
}
