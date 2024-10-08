import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {

    private GameData gameData = GameData.getInstance();
    
    /**
     * loadGame data (currently only for loading words)
     */
    public void loadGameData() {
        HashMap<DataKey, List<Word>> wordsMap = new HashMap<>(); // Map to store words by DataKey

        try {
            FileReader reader = new FileReader(GAME_DATA_FILE); 
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader); // Parse the entire JSON object
            
            // Loop through each language in the JSON object
            loadLanguages(jsonObject, wordsMap);
        } catch (Exception e) {
            e.printStackTrace(); 
        }

        // Populate the GameData instance with the loaded words
        gameData.populateData(wordsMap, null, null, null);
    }

    // Private method to load languages
    private void loadLanguages(JSONObject jsonObject, HashMap<DataKey, List<Word>> wordsMap) {
        for (Object languageKey : jsonObject.keySet()) {
            String language = (String) languageKey;
            JSONObject games = (JSONObject) jsonObject.get(language); // Get the games for the specific language

            // Loop through each game type
            loadGameTypes(games, language, wordsMap);
        }
    }

    // Private method to load game types
    private void loadGameTypes(JSONObject games, String language, HashMap<DataKey, List<Word>> wordsMap) {
        for (Object gameTypeKey : games.keySet()) {
            String gameType = (String) gameTypeKey;
            JSONObject difficulties = (JSONObject) games.get(gameType); // Get the difficulties for the game type

            // Loop through each difficulty
            loadDifficulties(difficulties, language, gameType, wordsMap);
        }
    }

    // Private method to load difficulties
    private void loadDifficulties(JSONObject difficulties, String language, String gameType, HashMap<DataKey, List<Word>> wordsMap) {
        for (Object difficultyKey : difficulties.keySet()) {
            String difficulty = (String) difficultyKey;
            JSONObject gameData = (JSONObject) difficulties.get(difficulty); // Get the game data for the specific difficulty

            // Create DataKey for this word entry
            DataKey dataKey = DataKey.getInstance(language, gameType, difficulty);
            List<Word> wordsList = new ArrayList<>(); // Create a new list for this DataKey

            // Process words
            processWords(gameData, wordsList);

            // Add the filled list into the wordsMap
            wordsMap.put(dataKey, wordsList); 
            System.out.println(dataKey); // DataKey for debugging
        }
    }

    // Private method to process words
    private void processWords(JSONObject gameData, List<Word> wordsList) {
        // Check if there are words and process them
        if (gameData.containsKey("words")) {
            JSONArray wordsArray = (JSONArray) gameData.get("words"); // Get the words array
            
            // Loop through each word object in the array
            for (int i = 0; i < wordsArray.size(); i++) {
                JSONObject wordJSON = (JSONObject) wordsArray.get(i);

                // Extract word
                String wordText = (String) wordJSON.get("text");
                String wordTranslation = (String) wordJSON.get("englishText");
                String exampleSentence = (String) wordJSON.get("exampleSentence");
                String sentenceTranslation = (String) wordJSON.get("englishSentence");

                // Create and add the Word object to the list
                wordsList.add(new Word(wordText, wordTranslation, exampleSentence, sentenceTranslation));
            }
        } else {
            System.out.println("No words found for the current game data.");
        }
    }
}
