import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

// Ensure to import your classes correctly, adjust the package name if necessary.
public class FITBQuestionTest {
    
    private FITBQuestion fitbQuestion;
    private GameManager gameManager; // Assuming you have a GameManager class

    @Before
    public void setUp() {
        // Initialize UUID for the question
        UUID uuid = UUID.randomUUID();
        
        // Mock the GameManager
        gameManager = mock(GameManager.class);
        
        // Create the FITBQuestion instance with the mocked GameManager
        fitbQuestion = new FITBQuestion(uuid);
        fitbQuestion.setGameManager(gameManager); // You may need to add a setter in FITBQuestion for GameManager
    }

    @Test
    public void testGenerateQuestionCreatesCorrectQuestionText() {
        // Setup mock TextObject
        String linkedText = "What is the capital of France?";
        String actualText = "Paris";
        
        TextObject mockTextObject = mock(TextObject.class);
        when(mockTextObject.getLinkedText()).thenReturn(linkedText);
        when(mockTextObject.getText()).thenReturn(actualText);
        
        // Setup the mock behavior for gameManager
        when(gameManager.findTextObjectByUUID(fitbQuestion.getUUID())).thenReturn(mockTextObject);
        
        // Generate the question
        fitbQuestion.generateQuestion();
        
        // Verify the generated question text
        String expectedQuestionText = "What is the capital of France?";
        expectedQuestionText = expectedQuestionText.replace(actualText, "_____");
        
        assertEquals(expectedQuestionText, fitbQuestion.getQuestionText());
        assertEquals(actualText, fitbQuestion.getAnswer());
    }
    
    @Test
    public void testValidateAnswerReturnsTrueForCorrectAnswer() {
        // Setup mock TextObject
        String linkedText = "What is the capital of France?";
        String actualText = "Paris";

        TextObject mockTextObject = mock(TextObject.class);
        when(mockTextObject.getLinkedText()).thenReturn(linkedText);
        when(mockTextObject.getText()).thenReturn(actualText);
        
        // Setup the mock behavior for gameManager
        when(gameManager.findTextObjectByUUID(fitbQuestion.getUUID())).thenReturn(mockTextObject);
        
        // Generate the question
        fitbQuestion.generateQuestion();

        // Test valid answer
        boolean isValid = fitbQuestion.validateAnswer("Paris");
        assertTrue(isValid);
    }

    @Test
    public void testValidateAnswerReturnsFalseForIncorrectAnswer() {
        // Setup mock TextObject
        String linkedText = "What is the capital of France?";
        String actualText = "Paris";

        TextObject mockTextObject = mock(TextObject.class);
        when(mockTextObject.getLinkedText()).thenReturn(linkedText);
        when(mockTextObject.getText()).thenReturn(actualText);
        
        // Setup the mock behavior for gameManager
        when(gameManager.findTextObjectByUUID(fitbQuestion.getUUID())).thenReturn(mockTextObject);
        
        // Generate the question
        fitbQuestion.generateQuestion();

        // Test invalid answer
        boolean isValid = fitbQuestion.validateAnswer("London");
        assertFalse(isValid);
    }
}
