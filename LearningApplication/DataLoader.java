import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {

    private GameData gameData = GameData.getInstance();

    private HashMap<DataKey, List<Word>> wordsMap;
    private HashMap<DataKey, List<Question>> questionsMap;
    private HashMap<DataKey, List<Story>> storiesMap;
    private HashMap<DataKey, List<Letter>> lettersMap;

    public DataLoader() {
        wordsMap = new HashMap<>();
        questionsMap = new HashMap<>();
        storiesMap = new HashMap<>();
        lettersMap = new HashMap<>();
    }

    
    /**
     * loadGame data (currently only for loading words)
     */
    public void loadGameData() {

        try {
            FileReader reader = new FileReader(GAME_DATA_FILE); 
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader); // Parse the entire JSON object
            
            // Loop through each language in the JSON object
            loadLanguages(jsonObject);
        } catch (Exception e) {
            e.printStackTrace(); 
        }

        // Populate the GameData instance with the loaded words
        gameData.populateData(wordsMap, null, null, null);
    }

    // Private method to load languages (loops through each language)
    private void loadLanguages(JSONObject jsonObject) {
        for (Object languageKey : jsonObject.keySet()) {
            String language = (String) languageKey;
            JSONObject games = (JSONObject) jsonObject.get(language); // Get the games for the specific language

            // Loop through each game type
            loadGameTypes(games, language);
        }
    }

    // Private method to load game types (loops through each gameType)
    private void loadGameTypes(JSONObject games, String language) {
        for (Object gameTypeKey : games.keySet()) {
            String gameType = (String) gameTypeKey;
            JSONObject difficulties = (JSONObject) games.get(gameType); // Get the difficulties for the game type

            // Loop through each difficulty
            loadDifficulties(difficulties, language, gameType);
        }
    }

    // Private method to load difficulties
    private void loadDifficulties(JSONObject difficulties, String language, String gameType) {
        for (Object difficultyKey : difficulties.keySet()) {
            String difficulty = (String) difficultyKey;
            JSONObject gameData = (JSONObject) difficulties.get(difficulty); // Get the game data for the specific difficulty

            // Create DataKey for this word entry
            DataKey dataKey = DataKey.getInstance(language, gameType, difficulty);

            // Create wordList and process words
            List<Word> wordsList = new ArrayList<>(); // Create a new list for this DataKey
            processWords(gameData, wordsList);

            // Create questionsList and process questions

            // Add the filled lists into the wordsMaps
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

    /**
     * STUB WE NEED QUESTION
     * @param gameData
     * @param questionsList
     */
    // Private method to process questions
    private void processQuestions(JSONObject gameData, List<Question> questionsList) {
        // Check if there are questions and process them
        if (gameData.containsKey(DataConstants.QUESTIONS)) {
            JSONArray questionsArray = (JSONArray) gameData.get(DataConstants.QUESTIONS); // Get the questions array

            // Loop through each question object in the array
            for (int i = 0; i < questionsArray.size(); i++) {
                JSONObject questionJSON = (JSONObject) questionsArray.get(i);

                // Extract question text
                String questionText = (String) questionJSON.get(DataConstants.TEXT);

                // Extract choices (stored as an array of strings)
                JSONArray choicesArray = (JSONArray) questionJSON.get(DataConstants.CHOICES);
                List<String> choices = new ArrayList<>();
                for (Object choice : choicesArray) {
                    choices.add((String) choice); // Add each choice to the list
                }

                // Extract the correct choice index (JSON uses long for numbers, so convert to int)
                long correctChoiceIndexLong = (long) questionJSON.get(DataConstants.CORRECT_CHOICE_INDEX);
                int correctChoiceIndex = (int) correctChoiceIndexLong;
// STUB
                // Create and add the Question object to the list
                // questionsList.add(new Question(questionText, choices, correctChoiceIndex));
            }
        } else {
            System.out.println("No questions found for the current game data.");
        }
    }

    // Private method to process letters
    private void processLetters(JSONObject gameData, List<Letter> lettersList) {
        // Check if there are letters and process them
        if (gameData.containsKey(DataConstants.LETTERS)) {
            JSONArray lettersArray = (JSONArray) gameData.get(DataConstants.LETTERS); // Get the letters array

            // Loop through each letter object in the array
            for (int i = 0; i < lettersArray.size(); i++) {
                JSONObject letterJSON = (JSONObject) lettersArray.get(i);

                // Extract letter text (the letter itself)
                String letterText = (String) letterJSON.get(DataConstants.TEXT);

                // Extract pronunciation
                String pronunciation = (String) letterJSON.get(DataConstants.PRONUNCIATION);

                // Extract image (if available)
                String image = letterJSON.containsKey(DataConstants.IMAGE) ? (String) letterJSON.get(DataConstants.IMAGE) : null;
// STUB
                // Create and add the Letter object to the list
                // lettersList.add(new Letter(letterText, pronunciation, image));
            }
        } else {
            System.out.println("No letters found for the current game data.");
        }
    }

    // Private method to process stories
    private void processStories(JSONObject gameData, List<Story> storiesList) {
        // Check if there are stories and process them
        if (gameData.containsKey(DataConstants.STORIES)) {
            JSONArray storiesArray = (JSONArray) gameData.get(DataConstants.STORIES); // Get the stories array

            // Loop through each story object in the array
            for (int i = 0; i < storiesArray.size(); i++) {
                JSONObject storyJSON = (JSONObject) storiesArray.get(i);

                // Extract title and author
                String title = (String) storyJSON.get(DataConstants.TITLE);
                String author = (String) storyJSON.get(DataConstants.AUTHOR);

                // Extract pages
                List<Page> pagesList = new ArrayList<>(); // List to hold pages
                processPages(storyJSON, pagesList); // Process the pages for this story

                // Create a new Story object using the constructor
                Story story = new Story(title, pagesList, author);

                // Add the Story object to the stories list
                storiesList.add(story);
            }
        } else {
            System.out.println("No stories found for the current game data.");
        }
    }

    // Private method to process pages for a story
    // Private method to process pages for a story
    private void processPages(JSONObject storyJSON, List<Page> pagesList) {
        // Check if there are pages and process them
        if (storyJSON.containsKey(DataConstants.PAGES)) {
            JSONArray pagesArray = (JSONArray) storyJSON.get(DataConstants.PAGES); // Get the pages array

            // Loop through each page object in the array
            for (int i = 0; i < pagesArray.size(); i++) {
                JSONObject pageJSON = (JSONObject) pagesArray.get(i);

                // Extract page number, text, englishText, and image
                String pageNumber = String.valueOf(pageJSON.get(DataConstants.PAGE_NUMBER)); // Convert to String
                String text = (String) pageJSON.get(DataConstants.TEXT);
                String englishText = pageJSON.containsKey(DataConstants.ENGLISH_TEXT) ? (String) pageJSON.get(DataConstants.ENGLISH_TEXT) : null;
                String image = pageJSON.containsKey(DataConstants.IMAGE) ? (String) pageJSON.get(DataConstants.IMAGE) : null;

                // Create a new Page object and add it to the list
                pagesList.add(new Page(pageNumber, text, englishText, image));
            }
        } else {
            System.out.println("No pages found for the current story.");
        }
    }

}
