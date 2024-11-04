import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import com.learner.game.innerdata.TextObject;

public class TextObjectTest {
    private TextObject textObject;

    @BeforeEach
    void setUp() {
        UUID uuid = UUID.randomUUID();
        UUID gameUUID = UUID.randomUUID();
        textObject = new TextObject("pula", "red", "Ang mansanas ay kulay pula.", "The apple is red.", "Pula means 'red' in Filipino.", uuid, gameUUID);
    }

    @Test
    void testGetters() {
        assertEquals("pula", textObject.getText(), "Text should be 'pula'.");
        assertEquals("red", textObject.getEnglishText(), "English text should be 'red'.");
        assertEquals("Ang mansanas ay kulay pula.", textObject.getLinkedText(), "Linked text should be 'Ang mansanas ay kulay pula.'");
        assertEquals("The apple is red.", textObject.getEnglishLinkedText(), "English linked text should be 'The apple is red.'");
        assertEquals("Pula means 'red' in Filipino.", textObject.getHelperText(), "Helper text should be 'Pula means 'red' in Filipino.'");
        assertNotNull(textObject.getUUID(), "UUID should not be null.");
        assertNotNull(textObject.getGameUUID(), "Game UUID should not be null.");
    }

    @Test
    void testToString() {
        String expected = "TEXT OBJ:\n" +
                          "Text: pula\n" +
                          "English Text: red\n" +
                          "Linked Text: Ang mansanas ay kulay pula.\n" +
                          "English Linked Text: The apple is red.\n" +
                          "Helper Text: Pula means 'red' in Filipino.\n" +
                          "UUID: " + textObject.getUUID() + "\n" +
                          "Game UUID: " + textObject.getGameUUID() + "\n" +
                          "-End of text obj-\n";

        assertEquals(expected, textObject.toString(), "toString should return the correct string representation.");
    }
}
