

import static org.junit.jupiter.api.Assertions.assertEqualsssertEquals;
import org.junit.jupiter.api.Test;

public class QuestionTypeTest {

    @Test
    void testEnumValues() {
        QuestionType[] types = QuestionType.values();
        assertEquals(3, types.length, "Expected 3 question types.");
        assertEquals(QuestionType.FITB, types[0], "Expected first type to be FITB.");
        assertEquals(QuestionType.MULTIPLE_CHOICE, types[1], "Expected second type to be MULTIPLE_CHOICE.");
        // Continue for the other types...
    }
}
