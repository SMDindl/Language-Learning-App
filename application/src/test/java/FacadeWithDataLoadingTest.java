import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.learner.game.Difficulty;
import com.learner.game.Facade;
import com.learner.game.GameManager;
import com.learner.game.UserList;
import com.learner.game.loadwrite.DataConstants;

public class FacadeWithDataLoadingTest {

    private Facade facade;

    @BeforeEach
    public void setUp() {
        // Load data using the DataLoader with specified paths
        GameManager.getInstance().clearData();
        UserList.getInstance().clearUsers();
        
        facade = Facade.getInstance();
        facade.loadData(DataConstants.GAME_DATA_FILE_JUNIT, DataConstants.USER_FILE_JUNIT);
    }

    @Test
    public void testLoginAndLogoutFlow() {
        // Attempt to login with a known user from the data
        String loginResult = facade.loginUser("sdindl@email.com", "Password2024!");
        assertEquals("Login successful.", loginResult);

        // Verify that the user is logged in
        assertTrue(facade.isUserLoggedIn(), "User should be logged in.");

        // Logout and verify
        String logoutResult = facade.logoutUser();
        assertEquals("Logout successful.", logoutResult);
        assertFalse(facade.isUserLoggedIn(), "User should be logged out.");
    }

    @Test
    public void testLanguageSelection() {
        // Fetch available languages after loading data
        ArrayList<String> languages = facade.getAvailableLanguages();
        assertNotNull(languages, "Languages should not be null.");
        assertFalse(languages.isEmpty(), "There should be available languages.");

        // Select the first language
        String selectionResult = facade.selectLanguage(0);
        assertTrue(selectionResult.startsWith("Language set to"), "Language selection should be successful.");
    }

    @Test
    public void testGameSelectionAndNavigation() {
        // a language and difficulty should be set in the data
        facade.selectLanguage(0);
        facade.selectDifficulty(Difficulty.EASY);

        ArrayList<String> availableGames = facade.getAvailableGames();
        assertNotNull(availableGames);
        assertFalse(availableGames.isEmpty(), "There should be available games.");

        // Select the first game
        String gameSelectionResult = facade.selectGame(0);
        assertTrue(gameSelectionResult.startsWith("Game '"), "Game selection should be successful.");

        // Test navigation through text objects
        String textObject = facade.showCurrentTextObject();
        assertNotNull(textObject, "Initial text object should be available.");

        String nextTextObject = facade.nextTextObject();
        assertNotNull(nextTextObject, "Next text object should be available.");

        String prevTextObject = facade.previousTextObject();
        assertEquals(textObject, prevTextObject, "Previous text object should match the initial one.");
    }

    @Test
    public void testQuizFlow() {
        facade.selectLanguage(0);
        facade.selectDifficulty(Difficulty.EASY);
        facade.selectGame(0);

        String startQuizResult = facade.startQuiz();
        assertEquals("Quiz started. Answer the following questions.", startQuizResult);

        String question = facade.getNextQuizQuestion();
        assertNotNull(question, "First quiz question should be available.");

        // Simulate an answer (you can modify this based on your expected question types)
        boolean answerResult = facade.validateQuizAnswer("someAnswer");
        assertFalse(answerResult, "Answer validation should return false for incorrect answer.");
    }
}
