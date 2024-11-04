import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import com.learner.game.questions.FITBQuestion; 
import com.learner.game.questions.QuestionType; 
public class FITBQuestionTest {

    private FITBQuestion question;

    @BeforeEach
    void setUp() {
        UUID questionUUID = UUID.fromString("e4e1d515-7baf-4569-8c14-7c663b6e49f5");
        question = new FITBQuestion(questionUUID.toString(), QuestionType.FITB);  // Assuming constructor expects String

        // Sample data setup
        question.setQuestionText("Fill in the blank: The sky is ___ (color).");
        question.setCorrectAnswer("blue");

        // Check if you have a proper setGameManager method
        // Assuming a MockGameManager is defined properly
        question.setGameManager(new MockGameManager());  // Ensure the method exists
    }

    // Additional test methods for functionality...
}
