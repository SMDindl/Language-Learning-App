import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

public class DataWriter {

    // Method to write user data to the JSON file
    public void writeUserData(String userName, String displayName, String email, String password) {
        JSONObject user = new JSONObject();
        user.put(DataConstants.USER_USER_NAME, userName);
        user.put(DataConstants.DISPLAY_NAME, displayName);
        user.put(DataConstants.EMAIL, email);
        user.put(DataConstants.PASSWORD, password);
        user.put(DataConstants.PROGRESS_TRACKERS, new JSONArray()); // Initialize with an empty array

        writeToFile(DataConstants.USER_FILE_NAME, user);
    }

    // Method to write game data (e.g., questions)
    public void writeGameData(String gameName, String questionText, String[] answers, int correctAnswerIndex) {
        JSONObject gameData = new JSONObject();
        gameData.put(DataConstants.QUESTIONS, new JSONArray());

        JSONObject question = new JSONObject();
        question.put(DataConstants.QUESTION_TEXT, questionText);
        question.put(DataConstants.ANSWERS, new JSONArray(answers));
        question.put(DataConstants.CORRECT_ANSWER_INDEX, correctAnswerIndex);

        gameData.getJSONArray(DataConstants.QUESTIONS).put(question);

        // Writing to a specific game data file
        String gameFileName = "json\\" + gameName + ".json";
        writeToFile(gameFileName, gameData);
    }

    // General method for writing data to a file
    private void writeToFile(String fileName, JSONObject data) {
        try (FileWriter file = new FileWriter(fileName, true)) {
            file.write(data.toString());
            file.write(System.lineSeparator()); // New line for separation
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to write letter data
    public void writeLetterData(String letterText, String pronunciation, String[] exampleWords) {
        JSONObject letter = new JSONObject();
        letter.put(DataConstants.LETTER_TEXT, letterText);
        letter.put(DataConstants.PRONUNCATION, pronunciation);
        letter.put(DataConstants.EXAMPLE_WORDS, new JSONArray(exampleWords));

        String letterFileName = "json\\letters.json"; // Assuming a single file for letters
        writeToFile(letterFileName, letter);
    }

    // Method to write story data
    public void writeStoryData(String title, String titleTranslation, String author, String[] pages) {
        JSONObject story = new JSONObject();
        story.put(DataConstants.TITLE, title);
        story.put(DataConstants.TITLE_TRANSLATION, titleTranslation);
        story.put(DataConstants.AUTHOR, author);
        story.put(DataConstants.PAGES, new JSONArray(pages));

        String storyFileName = "json\\stories.json"; // Assuming a single file for stories
        writeToFile(storyFileName, story);
    }
}

