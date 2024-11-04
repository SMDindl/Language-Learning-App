import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.learner.game.Difficulty;
import com.learner.game.Facade;

class FacadeTest {

    private Facade facade;

    @BeforeEach
    void getInstanceAndLoad() {
        facade = Facade.getInstance();
        facade.loadData("path/to/gameData.json", "path/to/userData.json"); // Mock or temporary paths for testing
    }

    @Test
    void testLoginUser() {
        facade.registerUser("test@example.com", "testUser", "Test Display", "test123");
        String result = facade.loginUser("test@example.com", "test123");
        assertEquals("Login successful.", result);
        assertTrue(facade.isUserLoggedIn());
    }

    @Test
    void testLogoutUser() {
        facade.registerUser("test@example.com", "testUser", "Test Display", "test123");
        facade.loginUser("test@example.com", "test123");
        assertTrue(facade.isUserLoggedIn());

        String logoutMessage = facade.logoutUser();
        assertEquals("Logout successful.", logoutMessage);
        assertFalse(facade.isUserLoggedIn());
    }

    @Test
    void testSelectLanguage() {
        facade.registerUser("user@example.com", "user", "User Display", "password");
        facade.loginUser("user@example.com", "password");

        String languageSelection = facade.selectLanguage(0); // Assuming the first language is valid
        assertTrue(languageSelection.startsWith("Language set to"));
    }

    @Test
    void testSelectDifficulty() {
        facade.registerUser("user@example.com", "user", "User Display", "password");
        facade.loginUser("user@example.com", "password");
        facade.selectLanguage(0);

        String difficultySelection = facade.selectDifficulty(Difficulty.EASY);
        assertEquals("Difficulty set to EASY.", difficultySelection);
    }

    @Test
    void testGetAvailableGames() {
        facade.registerUser("user@example.com", "user", "User Display", "password");
        facade.loginUser("user@example.com", "password");
        facade.selectLanguage(0);
        facade.selectDifficulty(Difficulty.EASY);

        ArrayList<String> games = facade.getAvailableGames();
        assertFalse(games.isEmpty());
    }

    @Test
    void testSelectGame() {
        facade.registerUser("user@example.com", "user", "User Display", "password");
        facade.loginUser("user@example.com", "password");
        facade.selectLanguage(0);
        facade.selectDifficulty(Difficulty.EASY);

        String selectGameMessage = facade.selectGame(0); // Assuming the first game index is valid
        assertTrue(selectGameMessage.startsWith("Game '"));
    }
}
