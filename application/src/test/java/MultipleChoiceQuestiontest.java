import org.junit.Before;
import org.junit.Test;
import com.learner.game.questions.MultipleChoiceQuestion;
import java.util.ArrayList;
import java.util.UUID;
import static org.junit.Assert.*;
//got to work
public class MultipleChoiceQuestionTest {
    
    private MultipleChoiceQuestion question;

    @Before
    public void setUp() {
        // Setup a sample MultipleChoiceQuestion from the JSON data
        String questionText = "What is the Filipino word for red?";
        ArrayList<String> choices = new ArrayList<>();
        choices.add("pula");
        choices.add("berde");
        choices.add("asul");

        UUID questionUUID = UUID.fromString("e4e1d515-7baf-4569-8c14-7c663b6e49f5");
        UUID gameUUID = UUID.fromString("8ce4fefc-a539-4546-9d7e-0ac8778f8de5");
        UUID languageUUID = UUID.fromString("1bafb0ae-3462-4ec3-9cc2-a98ff2898e72");

        // Create an instance of MultipleChoiceQuestion
        question = new MultipleChoiceQuestion(questionUUID, gameUUID, languageUUID, questionText, choices);
    }

    @Test
    public void testAskQuestion() {
        // Here you might want to check if the method works correctly
        // In a real scenario, you'd capture the output or verify it
        question.askQuestion();
    }

    @Test
    public void testValidateCorrectAnswer() {
        String correctAnswer = "pula";
        assertTrue("The answer should be correct.", question.validateAnswer(correctAnswer));
    }

    @Test
    public void testValidateIncorrectAnswer() {
        String incorrectAnswer = "berde";
        assertFalse("The answer should be incorrect.", question.validateAnswer(incorrectAnswer));
    }

    @Test
    public void testGetQuestionText() {
        assertEquals("The question text should match.", "What is the Filipino word for red?", question.getQuestionText());
    }

    @Test
    public void testGetChoices() {
        assertEquals("There should be three choices.", 3, question.getOptions().size());
    }
}
