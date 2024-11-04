import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class SequencingQuestionTest {

    private SequencingQuestion question;

    @BeforeEach
    void setUp() {
        // Sample UUIDs
        UUID questionUUID = UUID.fromString("e4e1d515-7baf-4569-8c14-7c663b6e49f5");
        UUID languageUUID = UUID.fromString("1bafb0ae-3462-4ec3-9cc2-a98ff2898e72");
        UUID gameUUID = UUID.fromString("8ce4fefc-a539-4546-9d7e-0ac8778f8de5");

        // Initialize SequencingQuestion with the UUID and the expected question type
        question = new SequencingQuestion(questionUUID, QuestionType.SEQUENCING);
        question.setGameUUID(gameUUID);
        question.setLanguageUUID(languageUUID);
        
        // Sample question data
        question.setQuestionText("Arrange the colors in the correct order: red, green, blue.");
        question.setChoices(new String[] {"red", "green", "blue"});

        // Add expected answers for validation
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
    void testGetUUID() {
        assertEquals(UUID.fromString("e4e1d515-7baf-4569-8c14-7c663b6e49f5"), question.getUUID());
    }

    @Test
    void testGetGameUUID() {
        assertEquals(UUID.fromString("8ce4fefc-a539-4546-9d7e-0ac8778f8de5"), question.getGameUUID());
    }

    @Test
    void testGetLanguageUUID() {
        assertEquals(UUID.fromString("1bafb0ae-3462-4ec3-9cc2-a98ff2898e72"), question.getLanguageUUID());
    }

    @Test
    void testGetQuestionText() {
        assertEquals("Arrange the colors in the correct order: red, green, blue.", question.getQuestionText());
    }

    @Test
    void testGetQuestionType() {
        assertEquals(QuestionType.SEQUENCING, question.getQuestionType());
    }
}
