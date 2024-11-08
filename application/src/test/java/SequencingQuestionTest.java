import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class SequencingQuestionTest {

    private SequencingQuestion question;

    @BeforeEach
    void setUp() {
        UUID questionUUID = UUID.fromString("e4e1d515-7baf-4569-8c14-7c663b6e49f5");
        question = new SequencingQuestion(questionUUID, QuestionType.SEQUENCING);
        
        // Sample question data
        question.setQuestionText("Arrange the colors in the correct order: red, green, blue.");
        question.setChoices(new String[] {"red", "green", "blue"});
        question.setCorrectSequence(new String[] {"red", "green", "blue"});
    }

    @Test
    void testGenerateQuestion() {
        question.generateQuestion();
        assertNotNull(question.getQuestionText(), "Question text should be generated.");
        assertEquals("Arrange the colors in the correct order: red, green, blue.", question.getQuestionText());
    }

    @Test
    void testValidateCorrectAnswer() {
        String userAnswer = "red, green, blue";
        assertTrue(question.validateAnswer(userAnswer), "Answer should be valid.");
    }

    @Test
    void testValidateIncorrectAnswer() {
        String userAnswer = "green, blue, red";
        assertFalse(question.validateAnswer(userAnswer), "Answer should be invalid.");
    }

    @Test
    void testCorrectSequence() {
        // Check the correct sequence validation
        String[] userSequence = {"red", "green", "blue"};
        assertTrue(question.validateSequence(userSequence), "User sequence should match the correct sequence.");
    }

    @Test
    void testIncorrectSequence() {
        // Check incorrect sequence validation
        String[] userSequence = {"blue", "red", "green"};
        assertFalse(question.validateSequence(userSequence), "User sequence should not match the correct sequence.");
    }


}
