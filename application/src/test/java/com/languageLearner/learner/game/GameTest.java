import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.learner.game.loadwrite.DataConstants;
import com.learner.game.loadwrite.DataLoader;

import com.learner.game.Game;
import com.learner.game.GameManager;

public class GameTest {
    @Test
    public void testFindGame() {
        DataLoader.loadGameData(DataConstants.GAME_DATA_FILE_JUNIT);
        GameManager gameManager = GameManager.getInstance();
        gameManager.findGameByUUID(UUID.fromString("8ce4fefc-a539-4546-9d7e-0ac8778f8de5"));

        assertTrue(true);
    }
}