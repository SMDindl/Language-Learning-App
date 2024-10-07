
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {

    public void loadGameData() {
        HashMap<DataKey, List<Word>> wordsMap = new HashMap<>();
        HashMap<DataKey, List<Question>> questionsMap = new HashMap<>();
        HashMap<DataKey, List<Story>> storiesMap = new HashMap<>();
        HashMap<DataKey, List<Letter>> lettersMap = new HashMap<>();

        try {
            FileReader reader = new FileReader(GAME_DATA_FILE); // Ensure GAME_DATA_FILE is correctly defined
            JSONParser parser = new JSONParser();
            JSONArray dataJSON = (JSONArray) parser.parse(reader);

            // Loop through the JSON array
            for (Object obj : dataJSON) {
                JSONObject gameData = (JSONObject) obj;

                // Example of loading words
                JSONArray wordsArray = (JSONArray) gameData.get("words");
                for (Object wordObj : wordsArray) {
                    JSONObject wordJSON = (JSONObject) wordObj;
                    String wordText = (String) wordJSON.get("text");
                    String wordTranslation = (String) wordJSON.get("englishText");
                    String exampleSentence = (String) wordJSON.get("exampleSentence");
                    String sentenceTranslation = (String) wordJSON.get("englishSentence");

                    Word word = new Word(wordText, wordTranslation, exampleSentence, sentenceTranslation);
                    DataKey dataKey = DataKey.getInstance(); 
                    wordsMap.computeIfAbsent(dataKey, k -> new ArrayList<>()).add(word);
                }

                // Add similar logic to load questions, stories, and letters...

            }

            // Populate the game data
            populateData(wordsMap, questionsMap, storiesMap, lettersMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to populate data into the GameData instance
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
