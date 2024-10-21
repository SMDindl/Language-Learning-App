package com.languageLearner.data;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {

    private GameData gameData = GameData.getInstance();  // Singleton instance of GameData

    public DataLoader() {
        // No need for HashMaps here anymore, directly populate into GameData
    }

    /**
     * Process #1: Load all game data from the JSON file into the GameData class.
     */
    public void loadGameData() {
        try {
            // Process #1.1: Read the file and parse the JSON data
            FileReader reader = new FileReader(GAME_DATA_FILE);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader); // Parse entire JSON object

            // Process #1.2: Load data by languages
            loadLanguages(jsonObject);
        } catch (Exception e) {
            e.printStackTrace(); // Print the error in case of failure
        }
    }

    /**
     * Process #2: Load all users from the JSON file into the UserList.
     */
    public void loadUsers() {
        UserList userList = UserList.getInstance();  // Get singleton UserList instance
        userList.clearUsers(); // Clear current user list before loading new users

        try {
            // Process #2.1: Read the file and parse the JSON data
            FileReader reader = new FileReader(USER_FILE); // Use constant USER_FILE
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(reader); // Parse entire JSON object

            // Process #2.2: Get the array of users from the JSON object
            JSONArray usersArray = (JSONArray) jsonData.get(USERS);

            // Process #2.3: Loop through each user object
            for (Object userObj : usersArray) {
                JSONObject userJSON = (JSONObject) userObj;

                String uuidStr = (String) userJSON.get(USER_ID);
                UUID uuid;

                if (uuidStr == null || uuidStr.isEmpty()) {
                    System.err.println("Error: UUID is null or empty for user: " + userJSON.get(USERNAME));
                    continue; // Skip this user if the UUID is null
                } else {
                    // Process #2.4: Attempt to parse the UUID
                    try {
                        uuid = UUID.fromString(uuidStr);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Error: Invalid UUID format for user: " + userJSON.get(USERNAME));
                        continue; // Skip this user if the UUID is invalid
                    }
                }

                // Process #2.5: Extract other user fields
                String username = (String) userJSON.get(USERNAME);
                String email = (String) userJSON.get(EMAIL);
                String displayName = (String) userJSON.get(DISPLAY_NAME);
                String password = (String) userJSON.get(PASSWORD);

                // Process #2.6: Create a new User object
                User user = new User(email, username, displayName, password, UUID.fromString(uuidStr));

                // Process #2.7: Load progress trackers for the user
                JSONArray trackersArray = (JSONArray) userJSON.get(PROGRESS_TRACKERS);
                if (trackersArray != null) {
                    for (Object trackerObj : trackersArray) {
                        JSONObject trackerJSON = (JSONObject) trackerObj;

                        String language = (String) trackerJSON.get(LANGUAGE);
                        JSONArray completedGamesArray = (JSONArray) trackerJSON.get(COMPLETED_GAMES);

                        ArrayList<DataKey> completedGames = new ArrayList<>();
                        for (Object gameObj : completedGamesArray) {
                            String[] gameKeyParts = gameObj.toString().split("-");

                            if (gameKeyParts.length == 2) {
                                String difficulty = gameKeyParts[0]; // Extract difficulty
                                String gameType = gameKeyParts[1];  // Extract game type

                                DataKey gameKey = DataKey.getInstance(language, gameType, difficulty);
                                completedGames.add(gameKey);
                            } else {
                                System.err.println("Invalid game key format: " + gameObj.toString());
                            }
                        }

                        ProgressTracker tracker = new ProgressTracker(language, completedGames);
                        user.addProgressTracker(tracker);
                    }
                }

                // Process #2.8: Add the user to the UserList
                userList.addUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print the error in case of failure
        }
    }

    // Private helper methods for loading game data

    /**
     * Process #1.2: Loop through each language in the JSON object and load its data.
     */
    private void loadLanguages(JSONObject jsonObject) {
        for (Object languageKey : jsonObject.keySet()) {
            String language = (String) languageKey;
            JSONObject games = (JSONObject) jsonObject.get(language); // Get games for the specific language

            // Load each game type within this language
            loadGameTypes(games, language);
        }
    }

    /**
     * Process #1.3: Loop through each game type (e.g., alphabetGame, colorsGame) and load its data.
     */
    private void loadGameTypes(JSONObject games, String language) {
        for (Object gameTypeKey : games.keySet()) {
            String gameType = (String) gameTypeKey;
            JSONObject difficulties = (JSONObject) games.get(gameType); // Get the difficulties for the game type

            // Load each difficulty level (e.g., easy, medium, hard) within this game type
            loadDifficulties(difficulties, language, gameType);
        }
    }

    /**
     * Process #1.4: Load each difficulty's game data (e.g., easy, medium, hard).
     */
    private void loadDifficulties(JSONObject difficulties, String language, String gameType) {
        for (Object difficultyKey : difficulties.keySet()) {
            String difficulty = (String) difficultyKey;
            JSONObject gameDataJSON = (JSONObject) difficulties.get(difficulty); // Get game data for this difficulty

            DataKey dataKey = DataKey.getInstance(language, gameType, difficulty);

            ArrayList<Word> wordsList = new ArrayList<>();
            processWords(gameDataJSON, wordsList);
            gameData.addWords(dataKey, wordsList);

            ArrayList<Question> questionsList = new ArrayList<>();
            processQuestions(gameDataJSON, questionsList);
            gameData.addQuestions(dataKey, questionsList);

            ArrayList<Letter> lettersList = new ArrayList<>();
            processLetters(gameDataJSON, lettersList);
            gameData.addLetters(dataKey, lettersList);

            ArrayList<Story> storiesList = new ArrayList<>();
            processStories(gameDataJSON, storiesList);
            gameData.addStories(dataKey, storiesList);
        }
    }

    /**
     * Process #1.5: Process the word entries within the game data.
     */
    private void processWords(JSONObject gameDataJSON, ArrayList<Word> wordsList) {
        if (gameDataJSON.containsKey(WORDS)) {
            JSONArray wordsArray = (JSONArray) gameDataJSON.get(WORDS);

            for (Object wordObj : wordsArray) {
                JSONObject wordJSON = (JSONObject) wordObj;

                String wordText = (String) wordJSON.get(TEXT);
                String wordTranslation = (String) wordJSON.get(ENGLISH_TEXT);
                String exampleSentence = (String) wordJSON.get(EXAMPLE_SENTENCE);
                String sentenceTranslation = (String) wordJSON.get(ENGLISH_SENTENCE);

                wordsList.add(new Word(wordText, wordTranslation, exampleSentence, sentenceTranslation));
            }
        } else {
            System.out.println("No words found for the current game data.");
        }
    }

    /**
     * Process #1.6: Process the question entries within the game data.
     */
    private void processQuestions(JSONObject gameDataJSON, ArrayList<Question> questionsList) {
        if (gameDataJSON.containsKey(QUESTIONS)) {
            JSONArray questionsArray = (JSONArray) gameDataJSON.get(QUESTIONS);

            for (Object questionObj : questionsArray) {
                JSONObject questionJSON = (JSONObject) questionObj;

                String questionText = (String) questionJSON.get(TEXT);

                JSONArray choicesArray = (JSONArray) questionJSON.get(CHOICES);
                ArrayList<String> choices = new ArrayList<>();
                for (Object choice : choicesArray) {
                    choices.add((String) choice);
                }

                long correctChoiceIndexLong = (long) questionJSON.get(CORRECT_CHOICE_INDEX);
                int correctChoiceIndex = (int) correctChoiceIndexLong;

                questionsList.add(new Question(questionText, choices, correctChoiceIndex));
            }
        } else {
            System.out.println("No questions found for the current game data.");
        }
    }

    /**
     * Process #1.7: Process the letter entries within the game data.
     */
    private void processLetters(JSONObject gameDataJSON, ArrayList<Letter> lettersList) {
        if (gameDataJSON.containsKey(LETTERS)) {
            JSONArray lettersArray = (JSONArray) gameDataJSON.get(LETTERS);

            for (Object letterObj : lettersArray) {
                JSONObject letterJSON = (JSONObject) letterObj;

                String letterText = (String) letterJSON.get(TEXT);
                String pronunciation = (String) letterJSON.get(PRONUNCIATION);

                ArrayList<Word> exampleWordsList = new ArrayList<>();
                processWords(letterJSON, exampleWordsList); // Process the example words for this letter

                String image = letterJSON.containsKey(IMAGE) ? (String) letterJSON.get(IMAGE) : null;

                if (image != null) {
                    lettersList.add(new Letter(letterText, pronunciation, exampleWordsList, image));
                } else {
                    lettersList.add(new Letter(letterText, pronunciation, exampleWordsList));
                }
            }
        } else {
            System.out.println("No letters found for the current game data.");
        }
    }

    /**
     * Process #1.8: Process the story entries within the game data.
     */
    private void processStories(JSONObject gameDataJSON, ArrayList<Story> storiesList) {
        if (gameDataJSON.containsKey(STORIES)) {
            JSONArray storiesArray = (JSONArray) gameDataJSON.get(STORIES);

            for (Object storyObj : storiesArray) {
                JSONObject storyJSON = (JSONObject) storyObj;

                String title = (String) storyJSON.get(TITLE);
                String author = (String) storyJSON.get(AUTHOR);

                ArrayList<Page> pagesList = new ArrayList<>();
                processPages(storyJSON, pagesList); // Process pages for the story

                ArrayList<Word> teachWordsList = new ArrayList<>();
                processWords(storyJSON, teachWordsList); // Process words to teach within this story

                storiesList.add(new Story(title, author, pagesList, teachWordsList));
            }
        } else {
            System.out.println("No stories found for the current game data.");
        }
    }

    /**
     * Process #1.9: Process the page entries within a story.
     */
    private void processPages(JSONObject storyJSON, ArrayList<Page> pagesList) {
        if (storyJSON.containsKey(PAGES)) {
            JSONArray pagesArray = (JSONArray) storyJSON.get(PAGES);

            for (Object pageObj : pagesArray) {
                JSONObject pageJSON = (JSONObject) pageObj;

                String pageNumber = String.valueOf(pageJSON.get(PAGE_NUMBER));
                String text = (String) pageJSON.get(TEXT);
                String englishText = pageJSON.containsKey(ENGLISH_TEXT) ? (String) pageJSON.get(ENGLISH_TEXT) : null;
                String image = pageJSON.containsKey(IMAGE) ? (String) pageJSON.get(IMAGE) : null;

                pagesList.add(new Page(pageNumber, text, englishText, image));
            }
        } else {
            System.out.println("No pages found for the current story.");
        }
    }
}
