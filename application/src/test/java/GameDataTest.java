// import com.languageLearner.app.LanguageLearningApplication;
// import com.languageLearner.data.DataConstants;
// import com.languageLearner.data.DataKey;
// import com.languageLearner.data.GameData;

// public class GameDataTest extends DataConstants {

//     public static void main(String[] args) {
//         LanguageLearningApplication app = LanguageLearningApplication.getInstance();
//         GameData gameData = GameData.getInstance();

//         app.load();

//         // Retrieve or create DataKey instances for testing
//         DataKey easyColorsKey = DataKey.getInstance("filipino", DataConstants.COLORS_GAME, DataConstants.EASY);
//         DataKey mediumColorsKey = DataKey.getInstance("filipino", DataConstants.COLORS_GAME, DataConstants.MEDIUM);
//         DataKey hardColorsKey = DataKey.getInstance("filipino", DataConstants.COLORS_GAME, DataConstants.HARD);
//         DataKey easyStoriesKey = DataKey.getInstance("filipino", DataConstants.STORIES_GAME, DataConstants.EASY);
//         DataKey mediumStoriesKey = DataKey.getInstance("filipino", DataConstants.STORIES_GAME, DataConstants.MEDIUM);
//         DataKey hardStoriesKey = DataKey.getInstance("filipino", DataConstants.STORIES_GAME, DataConstants.HARD);

//         // Load game data for testing
//         gameData.printGameData();  // Print all loaded data to verify

//         // Test asking a set number of questions of various types for specific DataKeys
//         System.out.println("\n--- Asking Questions for Colors Game (Easy, Multiple Choice) ---");
//         gameData.askQuestions(easyColorsKey, 3, GameData.TYPE_MULTIPLE_CHOICE);

//         System.out.println("\n--- Asking Questions for Colors Game (Medium, Fill-in-the-Blank) ---");
//         gameData.askQuestions(mediumColorsKey, 3, GameData.TYPE_FILL_IN_THE_BLANK);

//         System.out.println("\n--- Asking Questions for Colors Game (Hard, True/False) ---");
//         gameData.askQuestions(hardColorsKey, 3, GameData.TYPE_TRUE_FALSE);

//         System.out.println("\n--- Asking Questions for Stories Game (Easy, Multiple Choice) ---");
//         gameData.askQuestions(easyStoriesKey, 3, GameData.TYPE_MULTIPLE_CHOICE);

//         System.out.println("\n--- Asking Questions for Stories Game (Medium, Matching) ---");
//         gameData.askQuestions(mediumStoriesKey, 3, GameData.TYPE_MATCHING);

//         System.out.println("\n--- Asking Questions for Stories Game (Hard, Fill-in-the-Blank) ---");
//         gameData.askQuestions(hardStoriesKey, 3, GameData.TYPE_FILL_IN_THE_BLANK);

//         // // Test printing all questions for a specific DataKey
//         // System.out.println("\n--- Printing All Questions for Colors Game (Easy) ---");
//         // gameData.printQuestionList(easyColorsKey);

//         // System.out.println("\n--- Printing All Questions for Stories Game (Medium) ---");
//         // gameData.printQuestionList(mediumStoriesKey);


//     }
// }
