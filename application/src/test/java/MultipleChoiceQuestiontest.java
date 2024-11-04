import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class MultipleChoiceQuestionTest {

    private MultipleChoiceQuestion question;

    @BeforeEach
    void setUp() {
        // Sample UUIDs
        UUID questionUUID = UUID.fromString("e4e1d515-7baf-4569-8c14-7c663b6e49f5");
        UUID languageUUID = UUID.fromString("1bafb0ae-3462-4ec3-9cc2-a98ff2898e72");
        UUID gameUUID = UUID.fromString("8ce4fefc-a539-4546-9d7e-0ac8778f8de5");

        // Initialize MultipleChoiceQuestion with the UUID and the expected question type
        question = new MultipleChoiceQuestion(questionUUID, QuestionType.MULTIPLE_CHOICE);
        question.setGameUUID(gameUUID);
        question.setLanguageUUID(languageUUID);
        question.setChoices(new String[]{"pula", "berde", "asul"}); // Assuming you have a method to set choices
        question.setCorrectAnswer("pula"); // Set the correct answer
    }

    @Test
    void testGenerateQuestion() {
        // Here, you might want to generate the question text if your class has such a method
        question.generateQuestion();
        assertNotNull(question.getQuestionText()); // Check that the question text is generated
    }

    @Test
    void testValidateAnswerCorrect() {
        boolean isValid = question.validateAnswer("pula"); // Validate with correct answer
        assertTrue(isValid); // Ensure the validation returns true
    }

    @Test
    void testValidateAnswerIncorrect() {
        boolean isValid = question.validateAnswer("berde"); // Validate with incorrect answer
        assertFalse(isValid); // Ensure the validation returns false
    }
}
