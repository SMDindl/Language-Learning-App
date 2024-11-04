import static org.junit.jupiter.api.Assertions.assertEquals;
import com.learner.game.innerdata.TextObject;
import com.learner.game.questions.FITBQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;

// Mock GameManager for testing purposes
class MockGameManager {
    private final TextObject textObject;

    public MockGameManager(TextObject textObject) {
        this.textObject = textObject;
    }

    public TextObject findTextObjectByUUID(UUID uuid) {
        // Returns the TextObject if UUID matches
        return textObject.getUUID().equals(uuid) ? textObject : null;
    }
}

class FITBQuestionTest {

    private FITBQuestion question;
    private TextObject textObject;

    @BeforeEach
    void setUp() {
        // Sample UUIDs
        UUID questionUUID = UUID.fromString("285bad41-5c1c-45b4-bc51-59f480bb1895");
        UUID languageUUID = UUID.fromString("1bafb0ae-3462-4ec3-9cc2-a98ff2898e72");
        UUID gameUUID = UUID.fromString("8ce4fefc-a539-4546-9d7e-0ac8778f8de5");

        // Initialize FITBQuestion with the UUID
        question = new FITBQuestion(questionUUID); // Ensure this constructor exists

        // Create a TextObject with all required arguments
        textObject = new TextObject(
            "pula",                       // text (answer)
            "Ang mansanas ay kulay pula.", // linkedText with answer
            "The apple is red.",           // englishLinkedText
            "Pula means 'red' in Filipino.", // helperText
            questionUUID,                  // TextObject UUID
            languageUUID,                  // Language UUID
            gameUUID                       // Game UUID
        );

        // Mock gameManager behavior
        question.setGameManager(new MockGameManager(textObject)); // Ensure this method exists
    }

    @Test
    void testGenerateQuestionReplacesAnswerWithBlank() {
        // Generate the question
        question.generateQuestion();

        // Check if question text replaces "pula" with "_____"
        assertEquals("Ang mansanas ay kulay _____.", question.getQuestionText());

        // Check if answer is set correctly
        assertEquals("pula", question.getAnswer());
    }
}
