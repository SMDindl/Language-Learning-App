import com.languageLearner.app.LanguageLearningApplication;
import com.languageLearner.app.StoriesGame;
import com.languageLearner.data.DataConstants;
import com.languageLearner.data.DataKey;
import com.languageLearner.data.DataLoader;

public class HardcodedUI extends DataConstants {

    public static void main(String[] args) {

        // Initialize the application facade and load the data
        LanguageLearningApplication app = LanguageLearningApplication.getInstance();
        app.load();

        // Hardcoded login for testing
        System.out.println("Hardcoded login for user: Steven");
        boolean loginSuccess = app.signin("sdindl", "Password2024");
        if (loginSuccess) {
            System.out.println("Login successful!");

            // Simulate game data loading
            DataLoader dataLoader = new DataLoader();
            dataLoader.loadGameData(); // Load the game data

            // Create a DataKey for the game (hardcoded for StoriesGame)
            String language = DataConstants.FILIPINO;
            String difficulty = DataConstants.EASY;
            String gameType = DataConstants.STORIES_GAME;
            DataKey dataKey = DataKey.getInstance(language, gameType, difficulty);

            // Start the StoriesGame with the DataKey
            StoriesGame storiesGame = new StoriesGame(dataKey);
            storiesGame.startGame();

            // Hardcoded progress tracking after the game/quiz
            System.out.println("Quiz completed! Progress tracker updated.");
        } else {
            System.out.println("Login failed.");
        }

    }
}
