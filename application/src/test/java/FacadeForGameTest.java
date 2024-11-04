import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.learner.game.Difficulty;
import com.learner.game.FacadeForGame;
import com.learner.game.Game;
import com.learner.game.GameManager;
import com.learner.game.Language;
import com.learner.game.User;
import com.learner.game.innerdata.GameInfo;
import com.learner.game.innerdata.TextObject;
import com.learner.game.questions.Question;
import com.learner.game.questions.QuestionFactory;
import com.learner.game.questions.QuestionType;

public class FacadeForGameTest {

    private FacadeForGame facadeForGame;
    private GameManager gameManager = GameManager.getInstance();
    private Game testGame;
    private UUID languageUUID;
    private UUID gameUUID;
    private User testUser;

    @BeforeEach
    public void setUp() {
        gameManager.clearData();

        facadeForGame = new FacadeForGame();
        gameManager = GameManager.getInstance();

        // Create randomm language
        Language lang = new Language(UUID.randomUUID(), "Random Language");
        gameManager.initializeLanguage(lang);
        
        // Set up game and related data
        gameUUID = UUID.randomUUID();
        
        GameInfo gameInfo = new GameInfo("Description", "Objective", new ArrayList<>(), "Intro Concept", "Example Usage", "Game Tip", gameUUID);
        ArrayList<TextObject> textObjects = new ArrayList<>();
        ArrayList<Question> questions = new ArrayList<>();
        
        // Adding some text objects
        for (int i = 0; i < 3; i++) {
            TextObject textObject = new TextObject(
                "Text " + i,                 // text
                "English " + i,              // englishText
                "This is text " + i,              // linkedText
                "English Example " + i,      // englishLinkedText (missing parameter)
                "Helper " + i,               // helperText
                UUID.randomUUID(),           // textObject UUID
                gameUUID                     // game UUID
            );
            gameManager.addTextObjectUUID(gameUUID, textObject.getUUID());
            textObjects.add(textObject);
        }

        testGame = new Game(languageUUID, "Test Game", Difficulty.EASY, gameUUID, gameInfo, textObjects, questions);
        gameManager.addGame(testGame);

        // Adding FITB questions bc those don't rely on additional factors
        for (int i = 0; i < 3; i++) {
            Question question = QuestionFactory.createQuestion(QuestionType.FITB, textObjects.get(i).getUUID());
            questions.add(question);
        }

        // Set up a test user with a progress tracker
        testUser = new User("test@example.com", "testUser", "Test Display", "password");
        testUser.addProgressTracker(testUser.new ProgressTracker(languageUUID, "Test Language"));
    }

    @Test
    public void testSelectGame() {
        String result = facadeForGame.selectGame(gameUUID);
        assertEquals("Game 'Test Game' selected. Starting content navigation.", result);
    }

    @Test
    public void testShowCurrentTextObject() {
        facadeForGame.selectGame(gameUUID);
        String textContent = facadeForGame.showCurrentTextObject();
        assertTrue(textContent.contains("Text 0"));
    }

    @Test
    public void testNextTextObject() {
        facadeForGame.selectGame(gameUUID);
        facadeForGame.nextTextObject();
        String textContent = facadeForGame.showCurrentTextObject();
        assertTrue(textContent.contains("Text 1"));
    }

    @Test
    public void testPreviousTextObject() {
        facadeForGame.selectGame(gameUUID);
        facadeForGame.nextTextObject(); // Move to index 1
        facadeForGame.previousTextObject();
        String textContent = facadeForGame.showCurrentTextObject();
        assertTrue(textContent.contains("Text 0"));
    }

    @Test
    public void testNextTextObjectAtEnd() {
        facadeForGame.selectGame(gameUUID);
        facadeForGame.nextTextObject();
        facadeForGame.nextTextObject(); // Move to last object
        facadeForGame.nextTextObject(); // Try to move past end
        String textContent = facadeForGame.showCurrentTextObject();
        assertTrue(textContent.contains("Text 2"));
    }

    @Test
    public void testPreviousTextObjectAtBeginning() {
        facadeForGame.selectGame(gameUUID);
        facadeForGame.previousTextObject(); // Try to move before beginning
        String textContent = facadeForGame.showCurrentTextObject();
        assertTrue(textContent.contains("Text 0"));
    }

    @Test
    public void testStartQuiz() {
        facadeForGame.selectGame(gameUUID);
        String result = facadeForGame.startQuiz();
        assertEquals("Quiz started. Answer the following questions.", result);
    }

    @Test
    public void testGetNextQuizQuestion() {
        facadeForGame.selectGame(gameUUID);
        facadeForGame.startQuiz();
        String questionText = facadeForGame.getNextQuizQuestion();
        assertNotNull(questionText);
    }

    @Test
    public void testValidateQuizAnswer() {
        facadeForGame.selectGame(gameUUID);
        facadeForGame.startQuiz();
        String correctAnswer = "Text 0";  
        boolean isCorrect = facadeForGame.validateQuizAnswer(correctAnswer);
        assertTrue(isCorrect);
    }

    @Test
    public void testEndGameSession() {
        facadeForGame.selectGame(gameUUID);
        facadeForGame.startQuiz();
        String result = facadeForGame.endGameSession(testUser, languageUUID);
        assertEquals("Game session ended. Progress saved.", result);
        
        // Ensure progress is updated for the test user
        assertTrue(testUser.getProgressTracker(languageUUID).getCompletedGames().contains(gameUUID));
    }
}
