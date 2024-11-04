import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.learner.game.narration.Narrator;

public class NarrateTestTest {

    @Test
    void testNarratePlaySound() {
        // Test that playSound can be called without throwing exceptions
        assertDoesNotThrow(() -> Narrator.playSound("Kumusta ka na?"));
    }

    @Test
    void testNarratePlaySoundWithNull() {
        // Test that playSound with null throws NullPointerException
        assertThrows(NullPointerException.class, () -> Narrator.playSound(null));
    }

    @Test
    void testNarratePlaySoundWithEmptyString() {
        // Test that playSound with an empty string does not throw exceptions
        assertDoesNotThrow(() -> Narrator.playSound(""));
    }
}
