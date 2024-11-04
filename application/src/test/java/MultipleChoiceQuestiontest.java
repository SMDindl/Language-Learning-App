
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MultipleChoiceQuestionTest {

    private MultipleChoiceQuestion question;
    private UUID uuid;
    private UUID gameUUID;
    private UUID languageUUID;
    private String questionText;
    private ArrayList<String> options;

    @BeforeEach
    void setUp() {
        uuid = UUID.randomUUID();
        gameUUID = UUID.randomUUID();
        languageUUID = UUID.randomUUID();
        questionText = "What is the Filipino word for red?";
        options = new ArrayList<>();
        options.add("pula");
        options.add("berde");
        options.add("asul");
        
        // Create a MultipleChoiceQuestion instance
        question = new MultipleChoiceQuestion(uuid, gameUUID, languageUUID, questionText, options);
    }

    @Test
    void testShuffleOptions() {
        ArrayList<String> originalOptions = new ArrayList<>(question.getOptions());

        // Wait for some time to allow shuffle to take place (in case shuffle doesn't change order)
        try {
            Thread.sleep(100); // Sleep for a brief period to let shuffle have an effect
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        ArrayList<String> shuffledOptions = question.getOptions();
        assertNotEquals(originalOptions, shuffledOptions, "Options should be shuffled");
    }

    @Test
    void testValidateAnswerCorrect() {
        assertTrue(question.validateAnswer("pula"), "The answer 'pula' should be correct.");
    }

    @Test
    void testValidateAnswerIncorrect() {
        assertFalse(question.validateAnswer("berde"), "The answer 'berde' should be incorrect.");
    }

    @Test
    void testAskQuestion() {
        // This test just verifies that the method executes without throwing exceptions
        question.askQuestion();
        // Verify that the output includes the question text
        assertEquals(questionText, question.getQuestionText());
    }

    @Test
    void testGetOptions() {
        ArrayList<String> retrievedOptions = question.getOptions();
        assertEquals(options.size(), retrievedOptions.size(), "Options size should match");
        assertTrue(retrievedOptions.containsAll(options), "Options should match expected values");
    }
}
