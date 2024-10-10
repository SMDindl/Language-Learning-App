
import java.util.ArrayList;
import java.util.HashMap;

public class GameData {
    // Attributes
    private static GameData instance;
    private HashMap<DataKey, ArrayList<Word>> wordsMap;
    private HashMap<DataKey, ArrayList<Question>> questionsMap;
    private HashMap<DataKey, ArrayList<Story>> storiesMap;
    private HashMap<DataKey, ArrayList<Letter>> lettersMap;

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
    public ArrayList<Word> getWords(DataKey dataKey) {
        return wordsMap.get(dataKey);
    }

    // Retrieve questions data for a specific DataKey
    public ArrayList<Question> getQuestions(DataKey dataKey) {
        return questionsMap.get(dataKey);
    }

    // Retrieve letters data for a specific DataKey
    public ArrayList<Letter> getLetters(DataKey dataKey) {
        return lettersMap.get(dataKey);
    }

    // Retrieve stories data for a specific DataKey
    public ArrayList<Story> getStories(DataKey dataKey) {
        return storiesMap.get(dataKey);
    }

    // Method to populate game data
    public void populateData(HashMap<DataKey, ArrayList<Word>> words, 
                             HashMap<DataKey, ArrayList<Question>> questions,
                             HashMap<DataKey, ArrayList<Story>> stories, 
                             HashMap<DataKey, ArrayList<Letter>> letters) {
        this.wordsMap = words;
        this.questionsMap = questions;
        this.storiesMap = stories;
        this.lettersMap = letters;
    }
}

