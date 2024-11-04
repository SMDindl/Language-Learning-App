import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.learner.game.loadwrite.DataConstants;

public class DataConstantsTest {

    @Test
    public void testGameDataFilePath() {
        String expectedGameDataFilePath = "application\\src\\main\\data\\gamesData.json";
        assertEquals(expectedGameDataFilePath, DataConstants.GAME_DATA_FILE, "Mismatch in GAME_DATA_FILE path.");
    }

    @Test
    public void testGameDataFilePathJUnit() {
        String expectedJUnitFilePath = "application\\src\\test\\resources\\gamesData.json";
        assertEquals(expectedJUnitFilePath, DataConstants.GAME_DATA_FILE_JUNIT, "Mismatch in GAME_DATA_FILE_JUNIT path.");
    }

    @Test
    public void testUserFilePath() {
        String expectedUserFilePath = "application\\src\\main\\data\\users.json";
        assertEquals(expectedUserFilePath, DataConstants.USER_FILE, "Mismatch in USER_FILE path.");
    }

    @Test
    public void testUserFilePathJUnit() {
        String expectedJUnitUserPath = "application\\src\\test\\resources\\users.json";
        assertEquals(expectedJUnitUserPath, DataConstants.USER_FILE_JUNIT, "Mismatch in USER_FILE_JUNIT path.");
    }

    @Test
    public void testMainJsonKeys() {
        assertEquals("LANGUAGES", DataConstants.LANGUAGES, "Incorrect value for LANGUAGES key.");
        assertEquals("LANG", DataConstants.LANG, "Incorrect value for LANG key.");
        assertEquals("UUID", DataConstants.UUID, "Incorrect value for UUID key.");
    }

    @Test
    public void testInfoKeys() {
        assertEquals("INFO", DataConstants.INFO, "Incorrect value for INFO key.");
        assertEquals("description", DataConstants.DESCRIPTION, "Incorrect value for DESCRIPTION key.");
        assertEquals("objective", DataConstants.OBJECTIVE, "Incorrect value for OBJECTIVE key.");
        assertEquals("instructions", DataConstants.INSTRUCTIONS, "Incorrect value for INSTRUCTIONS key.");
        assertEquals("prep", DataConstants.PREP, "Incorrect value for PREP key.");
    }

    @Test
    public void testPrepKeys() {
        assertEquals("introConcept", DataConstants.INTRO_CONCEPT, "Incorrect value for INTRO_CONCEPT key.");
        assertEquals("exampleUsage", DataConstants.EXAMPLE_USAGE, "Incorrect value for EXAMPLE_USAGE key.");
        assertEquals("gameTip", DataConstants.GAME_TIP, "Incorrect value for GAME_TIP key.");
    }

    @Test
    public void testGameArraysKeys() {
        assertEquals("GAMES", DataConstants.GAMES, "Incorrect value for GAMES key.");
        assertEquals("QUESTIONS", DataConstants.QUESTIONS, "Incorrect value for QUESTIONS key.");
        assertEquals("TEXT", DataConstants.TEXT, "Incorrect value for TEXT key.");
    }

    @Test
    public void testTextObjectKeys() {
        assertEquals("text", DataConstants.TEXT_OBJ, "Incorrect value for TEXT_OBJ key.");
        assertEquals("englishText", DataConstants.ENGLISH_TEXT, "Incorrect value for ENGLISH_TEXT key.");
        assertEquals("linkedText", DataConstants.LINKED_TEXT, "Incorrect value for LINKED_TEXT key.");
        assertEquals("englishLinkedText", DataConstants.ENGLISH_LINKED_TEXT, "Incorrect value for ENGLISH_LINKED_TEXT key.");
        assertEquals("helperText", DataConstants.HELPER_TEXT, "Incorrect value for HELPER_TEXT key.");
    }

    @Test
    public void testQuestionKeys() {
        assertEquals("questionText", DataConstants.QUESTION_TEXT, "Incorrect value for QUESTION_TEXT key.");
        assertEquals("choices", DataConstants.CHOICES, "Incorrect value for CHOICES key.");
    }

    @Test
    public void testSequencingGames() {
        ArrayList<UUID> sequencingGames = DataConstants.SEQUENCING_GAMES;
        assertNotNull(sequencingGames, "SEQUENCING_GAMES should not be null.");
        
        // Add specific UUIDs to test for if necessary, e.g., assertTrue(sequencingGames.contains(someExpectedUUID));
        assertTrue(sequencingGames.isEmpty(), "SEQUENCING_GAMES should be empty by for now.");
    }
}
