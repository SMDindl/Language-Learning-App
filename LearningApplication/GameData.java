
import java.util.HashMap;
import java.util.List;

public class GameData {
    // Attributes
    private static GameData instance;
    private HashMap<DataKey, List<Word>> wordsMap;
    private HashMap<DataKey, List<Question>> questionsMap;
    private HashMap<DataKey, List<Story>> storiesMap;
    private HashMap<DataKey, List<Letter>> lettersMap;

    // Instanciate hashMaps, private constructor (singleton)
    private GameData() {
        wordsMap = new HashMap<>();
        questionsMap = new HashMap<>();
        storiesMap = new HashMap<>();
        lettersMap = new HashMap<>();
    }

    // Singleton instance retrieval
    public static GameData getInstance() {
        if (instance == null) {
            instance = new GameData();
        }
        return instance;
    }

    // Method to retrieve words for a specific DataKey
    public List<Word> getWords(DataKey dataKey) {
        return wordsMap.get(dataKey);
    }

    // Retrieve questions data for a specific DataKey
    public List<Question> getQuestions(DataKey dataKey) {
        return questionsMap.get(dataKey);
    }

    // Retrieve letters data for a specific DataKey
    public List<Letter> getLetters(DataKey dataKey) {
        return lettersMap.get(dataKey);
    }

    // Retrieve stories data for a specific DataKey
    public List<Story> getStories(DataKey dataKey) {
        return storiesMap.get(dataKey);
    }

    // Method to populate game data
    public void populateData(HashMap<DataKey, List<Word>> words, 
                             HashMap<DataKey, List<Question>> questions,
                             HashMap<DataKey, List<Story>> stories, 
                             HashMap<DataKey, List<Letter>> letters) {
        this.wordsMap = words;
        this.questionsMap = questions;
        this.storiesMap = stories;
        this.lettersMap = letters;
    }
}

