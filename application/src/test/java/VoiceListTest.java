import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.learner.game.narration.VoiceList;

import software.amazon.awssdk.regions.Region;

public class VoiceListTest {
    @Test
    void testShowVoices() {
        // Test that showVoices can be called without throwing exceptions
        assertDoesNotThrow(() -> VoiceList.showVoices(Region.EU_WEST_3));
    }
}
