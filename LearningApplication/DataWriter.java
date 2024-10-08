import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Paths;
import java.util.List;

public class DataWriter {

    // Method to write user data to the JSON file
    public void writeUserData(String userName, String displayName, String email, String password) {
        JSONObject user = new JSONObject();
        user.put(DataConstants.USER_USER_NAME, userName);
        user.put(DataConstants.DISPLAY_NAME, displayName);
        user.put(DataConstants.EMAIL, email);
        user.put(DataConstants.PASSWORD, password);
        user.put(DataConstants.PROGRESS_TRACKERS, new JSONArray());

        writeToFile(DataConstants.USER_FILE_NAME, user);
    }

    // Method to write game data (matches the structure loaded in DataLoader)
    public void writeGameData(String language, String gameType, String difficulty, List<Word> words) {
        JSONObject gameData = new JSONObject();
        JSONObject difficultiesObj = new JSONObject();
        JSONObject gameTypeObj = new JSONObject();

        // Create a JSON array of words
        JSONArray wordsArray = new JSONArray();
        for (Word word : words) {
            JSONObject wordObj = new JSONObject();
            wordObj.put("text", word.getText());
            wordObj.put("englishText", word.getTranslation());
            wordObj.put("exampleSentence", word.getExampleSentence());
            wordObj.put("englishSentence", word.getSentenceTranslation());
            wordsArray.put(wordObj);
        }

        // Add the words array to gameData under the key "words"
        gameData.put("words", wordsArray);

        // Add game data under the difficulty
        difficultiesObj.put(difficulty, gameData);

        // Add difficulties under the game type
        gameTypeObj.put(gameType, difficultiesObj);

        // Add game type under the language
        JSONObject languageObj = new JSONObject();
        languageObj.put(language, gameTypeObj);

        // Write to file (appending to the existing file or creating a new one)
        String gameFileName = Paths.get("json", "gameData.json").toString();
        writeToFile(gameFileName, languageObj);
    }

     // General method for writing data to a file
     private void writeToFile(String fileName, JSONObject data) {
        try (FileWriter file = new FileWriter(fileName, true)) {
            file.write(data.toString());
            file.write(System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Error writing to file: " + fileName);
            e.printStackTrace();
        }
    }

    // Method to write letter data (similar to the structure loaded by DataLoader)
    public void writeLetterData(String letterText, String pronunciation, String[] exampleWords) {
        JSONObject letter = new JSONObject();
        letter.put(DataConstants.LETTER_TEXT, letterText);
        letter.put(DataConstants.PRONUNCATION, pronunciation);
        letter.put(DataConstants.EXAMPLE_WORDS, new JSONArray(exampleWords));

        String letterFileName = "json/letters.json";
        writeToFile(letterFileName, letter);
    }

    // Method to write story data (consistent with what DataLoader expects)
    public void writeStoryData(String title, String titleTranslation, String author, String[] pages) {
        JSONObject story = new JSONObject();
        story.put(DataConstants.TITLE, title);
        story.put(DataConstants.TITLE_TRANSLATION, titleTranslation);
        story.put(DataConstants.AUTHOR, author);
        story.put(DataConstants.PAGES, new JSONArray(pages));

        String storyFileName = "json/stories.json";
        writeToFile(storyFileName, story);
    }
}
