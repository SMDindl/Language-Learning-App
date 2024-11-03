import java.util.UUID;

// import com.learner.game.Game;
import com.learner.game.GameManager;
import com.learner.game.Language;

/**
 * Test w/o data loading
 */
public class TestGameManagerFlow {

    public static void main(String[] args) {

        // Get the GameManger instance
        GameManager gameManager = GameManager.getInstance();

        // GameManager should have many things
        // 1) HashMap of LanguageUUID + Languages 
        Language filipino = new Language(UUID.randomUUID(), "filipino");
        Language spanish = new Language(UUID.randomUUID(), "spanish");

        // Add languages to the gamemanager 
        gameManager.initializeLanguage(filipino);
        gameManager.initializeLanguage(spanish);

        // When we have a game to add
        // Game filipinoEasyGame;
        // Game filipinoMediumGame;
        // Game filipinoHardGame;
        // Game spanishEasyGame;
        // Game spanishMediumGame;
        // Game spanishHardGame;

        System.out.println();

    }
    
}
