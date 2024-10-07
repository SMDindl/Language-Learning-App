import java.util.HashMap;
import java.util.List;

public class GameData {
    // Attributes
    private static GameData instance;
    private HashMap<DataKey, List<Word>> wordsMap;
    private HashMap<DataKey, List<Question>> questionsMap;
    private HashMap<DataKey, List<Story>> storiesMap;
    private HashMap<DataKey, List<Letter>> lettersMap;

    // Methods
    public static GameData getInstance() {
        return null;
    }

    public GameData getGameData() {
        return null;
    }

    public List<Word> getWords(DataKey dataKey) {
        return null;
    }

    public List<Question> getQuestions(DataKey dataKey) {
        // Implementation stub
        return null;
    }

    public List<Letter> getLetters(DataKey dataKey) {
        return null;
    }

    public List<Story> getStories(DataKey dataKey) {
        return null;
    }

    public void populateData() {
    }

    private void populateWords() {
    }

    private void populateQuestions() {
    }

    private void populateStories() {
    }

    private void populateLetters() {
    }
}
