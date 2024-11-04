import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.UUID;
import org.json.simple.JSONObject;
import com.learner.game.innerdata.GameInfo;

public class GameInfoTest {

    @Test
    public void testGameInfoCreation() {
        UUID gameUUID = UUID.randomUUID();
        ArrayList<String> instructions = new ArrayList<>();
        instructions.add("Instruction 1");
        instructions.add("Instruction 2");
        String description = "Game description";
        String objective = "Game objective";
        String introConcept = "Intro concept";
        String exampleUsage = "Example usage";
        String gameTip = "Game tip";

        GameInfo gameInfo = new GameInfo(description, objective, instructions, introConcept, exampleUsage, gameTip, gameUUID);

        // Verify that the GameInfo object is created successfully
        assertNotNull(gameInfo);
    }

    @Test
    public void testInstructionsToString() {
        UUID gameUUID = UUID.randomUUID();
        ArrayList<String> instructions = new ArrayList<>();
        instructions.add("Instruction 1");
        instructions.add("Instruction 2");
        String description = "Game description";
        String objective = "Game objective";
        String introConcept = "Intro concept";
        String exampleUsage = "Example usage";
        String gameTip = "Game tip";

        GameInfo gameInfo = new GameInfo(description, objective, instructions, introConcept, exampleUsage, gameTip, gameUUID);

        // Check if the instructionsToString method produces the expected output
        String expectedInstructions = "1) Instruction 1 \n2) Instruction 2 \n";
        assertEquals(expectedInstructions, gameInfo.instructionsToString());
    }

    @Test
    public void testFromJson() {
        UUID gameUUID = UUID.randomUUID();
        JSONObject infoJson = new JSONObject();
        infoJson.put("description", "Game description");
        infoJson.put("objective", "Game objective");
        ArrayList<String> instructions = new ArrayList<>();
        instructions.add("Instruction 1");
        instructions.add("Instruction 2");
        infoJson.put("instructions", instructions);

        JSONObject prepJson = new JSONObject();
        prepJson.put("introConcept", "Intro concept");
        prepJson.put("exampleUsage", "Example usage");
        prepJson.put("gameTip", "Game tip");
        infoJson.put("prep", prepJson);

        GameInfo gameInfo = GameInfo.fromJson(infoJson, gameUUID);

        // Verify that the GameInfo object created from JSON is not null
        assertNotNull(gameInfo);
    }

    @Test
    public void testEmptyInstructions() {
        UUID gameUUID = UUID.randomUUID();
        GameInfo gameInfo = new GameInfo("desc", "obj", new ArrayList<>(), "intro", "usage", "tip", gameUUID);
        assertEquals("", gameInfo.instructionsToString());
    }

    @Test
    public void testMultipleInstructions() {
        UUID gameUUID = UUID.randomUUID();
        ArrayList<String> instructions = new ArrayList<>();
        instructions.add("Step 1");
        instructions.add("Step 2");
        instructions.add("Step 3");
        GameInfo gameInfo = new GameInfo("desc", "obj", instructions, "intro", "usage", "tip", gameUUID);
        String expectedOutput = "1) Step 1 \n2) Step 2 \n3) Step 3 \n";
        assertEquals(expectedOutput, gameInfo.instructionsToString());
    }

    @Test
    public void testUniqueGameUUIDs() {
        UUID gameUUID1 = UUID.randomUUID();
        UUID gameUUID2 = UUID.randomUUID();
        assertNotEquals(gameUUID1, gameUUID2);
    }
}
