import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;

public class QuestionTest {

    private FITBQuestion question; // or whatever specific question type you're testing

    @BeforeEach
    public void setUp() {
        UUID uuid = UUID.randomUUID();
        question = new FITBQuestion(uuid, QuestionType.FILL_IN_THE_BLANK); // Assuming this constructor exists
    }

    @Test
    public void testGetUUID() {
        assertNotNull(question.getUUID());
        assertEquals(question.getUUID().toString(), question.getUUID().toString()); // Example assertion
    }

    // Add more test methods here
}
