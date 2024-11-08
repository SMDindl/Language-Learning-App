import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import com.learner.game.innerdata.TextObject; // Ensure this import is correct
import com.learner.game.questions.FITBQuestion;
import com.learner.game.mocks.MockGameManager; // Use this if you have a mock

public class FITBQuestionTest {

    private FITBQuestion question;
    private MockGameManager gameManager; // Ensure this is defined and implemented

    @BeforeEach
    void setUp() {
        UUID questionUUID = UUID.fromString("e4e1d515-7baf-4569-8c14-7c663b6e49f5");
        gameManager = new MockGameManager(); // Initialize your mock game manager
        question = new FITBQuestion(questionUUID); // Create a new FITBQuestion instance
        question.setGameManager(gameManager); // Set the game manager

        // Set up a mock TextObject
        TextObject mockTextObject = new TextObject("The sky is blue", questionUUID);
        gameManager.addTextObject(mockTextObject); // Add the mock TextObject to the game manager

        question.generateQuestion(); // Call to generate the question and set the answer
    }

    @Test
    void testValidateAnswer_CorrectAnswer() {
        String userAnswer = "blue";
        assertTrue(question.validateAnswer(userAnswer), "The answer should be valid.");
    }

    // Additional tests...
}
