package com.languageLearner.data;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * Loads user and game data from JSON files into the application's data structure.
 * Handles parsing and populating relevant game data and user progress tracking.
 */
public class DataLoader extends DataConstants {

    private GameData gameData = GameData.getInstance();

    /** Constructor for DataLoader. */
    public DataLoader() {}

    /**
     * Loads game data from the JSON file specified by GAME_DATA_FILE.
     */
    public void loadGameData() {
        try (FileReader reader = new FileReader(GAME_DATA_FILE)) {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            loadLanguages(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads user data from the JSON file specified by USER_FILE and populates
     * it into the UserList instance.
     */
    public void loadUsers() {
        UserList userList = UserList.getInstance();
        userList.clearUsers();

        try (FileReader reader = new FileReader(USER_FILE)) {
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(reader);

            JSONArray usersArray = (JSONArray) jsonData.get(USERS);
            for (Object userObj : usersArray) {
                JSONObject userJSON = (JSONObject) userObj;

                UUID uuid = parseUUID((String) userJSON.get(USER_ID));
                if (uuid == null) continue;

                User user = new User(
                    (String) userJSON.get(EMAIL),
                    (String) userJSON.get(USERNAME),
                    (String) userJSON.get(DISPLAY_NAME),
                    (String) userJSON.get(PASSWORD),
                    uuid
                );

                JSONArray trackersArray = (JSONArray) userJSON.get(PROGRESS_TRACKERS);
                if (trackersArray != null) {
                    for (Object trackerObj : trackersArray) {
                        JSONObject trackerJSON = (JSONObject) trackerObj;
                        ProgressTracker tracker = new ProgressTracker((String) trackerJSON.get(LANGUAGE));

                        // Load completed games
                        JSONArray completedGamesArray = (JSONArray) trackerJSON.get(COMPLETED_GAMES);
                        if (completedGamesArray != null) {
                            for (Object gameObj : completedGamesArray) {
                                String[] gameKeyParts = gameObj.toString().split("-");
                                if (gameKeyParts.length == 3) {
                                    String lang = gameKeyParts[0];
                                    String difficulty = gameKeyParts[1];
                                    String gameType = gameKeyParts[2];

                                    DataKey gameKey = DataKey.getInstance(lang, gameType, difficulty);
                                    tracker.addCompletedGame(gameKey);
                                } else {
                                    System.err.println("Invalid game key format: " + gameObj.toString());
                                }
                            }
                        }

                        // Load missed questions
                        loadMissedQuestions(tracker, trackerJSON);

                        user.addProgressTracker(tracker);
                    }
                }
                userList.addUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper method to parse and add missed questions to the given tracker.
     *
     * @param tracker ProgressTracker instance where missed items are added.
     * @param trackerJSON JSON object containing tracker details.
     */
    private void loadMissedQuestions(ProgressTracker tracker, JSONObject trackerJSON) {
        JSONArray missedQuestionsArray = (JSONArray) trackerJSON.get("missedQuestions");
        if (missedQuestionsArray != null) {
            for (Object missedObj : missedQuestionsArray) {
                JSONObject missedJSON = (JSONObject) missedObj;
                String questionUUID = (String) missedJSON.get(USER_ID);
                if (questionUUID != null) {
                    tracker.addMissedQuestion(questionUUID);
                }
            }
        }
    }

    /**
     * Parses a UUID from a string.
     *
     * @param uuidStr String representation of a UUID.
     * @return UUID if parsed successfully, or null if invalid.
     */
    private UUID parseUUID(String uuidStr) {
        try {
            return UUID.fromString(uuidStr);
        } catch (IllegalArgumentException | NullPointerException e) {
            System.err.println("Invalid or null UUID: " + uuidStr);
            return null;
        }
    }

    /**
     * Loads languages and their associated data from the JSON object.
     *
     * @param jsonObject JSON object containing game data by language.
     */
    private void loadLanguages(JSONObject jsonObject) {
        for (Object languageKey : jsonObject.keySet()) {
            String language = (String) languageKey;
            JSONObject games = (JSONObject) jsonObject.get(language);

            loadGameTypes(games, language);
        }
    }

    /**
     * Loads game types within a specific language from the JSON object.
     *
     * @param games JSON object containing game types.
     * @param language Language for which game types are being loaded.
     */
    private void loadGameTypes(JSONObject games, String language) {
        for (Object gameTypeKey : games.keySet()) {
            String gameType = (String) gameTypeKey;
            JSONObject difficulties = (JSONObject) games.get(gameType);

            loadDifficulties(difficulties, language, gameType);
        }
    }

    /**
     * Loads difficulty levels within a game type from the JSON object.
     *
     * @param difficulties JSON object containing difficulties.
     * @param language Language for which difficulties are being loaded.
     * @param gameType Game type for which difficulties are being loaded.
     */
    private void loadDifficulties(JSONObject difficulties, String language, String gameType) {
        for (Object difficultyKey : difficulties.keySet()) {
            String difficulty = (String) difficultyKey;
            JSONObject gameDataJSON = (JSONObject) difficulties.get(difficulty);

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
     * Loads words
     * 
     * @param gameDataJSON
     * @param wordsList
     */
    private void processWords(JSONObject gameDataJSON, ArrayList<Word> wordsList) {
        if (gameDataJSON.containsKey(WORDS)) {
            JSONArray wordsArray = (JSONArray) gameDataJSON.get(WORDS);
    
            for (Object wordObj : wordsArray) {
                JSONObject wordJSON = (JSONObject) wordObj;
    
                // Extract common fields
                String wordText = (String) wordJSON.get(TEXT);
                String wordTranslation = (String) wordJSON.get(ENGLISH_TEXT);
                String exampleSentence = wordJSON.containsKey(EXAMPLE_SENTENCE) ? (String) wordJSON.get(EXAMPLE_SENTENCE) : null;
                String sentenceTranslation = wordJSON.containsKey(ENGLISH_SENTENCE) ? (String) wordJSON.get(ENGLISH_SENTENCE) : null;
    
                // Check for optional "number" field
                if (wordJSON.containsKey(DIGIT)) {
                    String number = (String) wordJSON.get(DIGIT);
                    // Use constructor with "number" field
                    wordsList.add(new Word(number, wordText, wordTranslation, exampleSentence, sentenceTranslation));
                } else {
                    // Use constructor without "number" field
                    wordsList.add(new Word(wordText, wordTranslation, exampleSentence, sentenceTranslation));
                }
            }
        }
    }
    

    /**
     * Processes question entries within the game data JSON object.
     *
     * @param gameDataJSON JSON object containing game data.
     * @param questionsList List to store processed Question objects.
     */
    private void processQuestions(JSONObject gameDataJSON, ArrayList<Question> questionsList) {
        if (gameDataJSON.containsKey(QUESTIONS)) {
            JSONArray questionsArray = (JSONArray) gameDataJSON.get(QUESTIONS);

            for (Object questionObj : questionsArray) {
                JSONObject questionJSON = (JSONObject) questionObj;

                String questionText = (String) questionJSON.get(TEXT);
                String context = questionJSON.containsKey(CONTEXT) ? (String) questionJSON.get(CONTEXT) : null;
                UUID uuid = questionJSON.containsKey(USER_ID) ? UUID.fromString((String) questionJSON.get(USER_ID)) : UUID.randomUUID();

                // Determine if the question is multiple-choice or true/false
                if (questionJSON.containsKey(CHOICES)) {
                    int correctAnswerIndex = Math.toIntExact((long) questionJSON.get(CORRECT_CHOICE_INDEX));
                    ArrayList<String> choices = new ArrayList<>();
                    JSONArray choicesArray = (JSONArray) questionJSON.get(CHOICES);
                    for (Object choice : choicesArray) {
                        choices.add((String) choice);
                    }

                    questionsList.add(new Question(
                        uuid.toString(),
                        "multiple_choice",
                        questionText,
                        choices,
                        correctAnswerIndex,
                        context
                    ));
                } else if (questionJSON.containsKey("correctAnswer")) {
                    boolean correctAnswer = (boolean) questionJSON.get("correctAnswer");

                    questionsList.add(new Question(
                        uuid.toString(),
                        "true_false",
                        questionText,
                        correctAnswer,
                        context
                    ));
                } else {
                    System.err.println("Unrecognized question format: " + questionJSON);
                }
            }
        }
    }

    /**
     * Processes letter entries within the game data JSON object.
     *
     * @param gameDataJSON JSON object containing game data.
     * @param lettersList List to store processed Letter objects.
     */
    private void processLetters(JSONObject gameDataJSON, ArrayList<Letter> lettersList) {
        if (gameDataJSON.containsKey(LETTERS)) {
            JSONArray lettersArray = (JSONArray) gameDataJSON.get(LETTERS);

            for (Object letterObj : lettersArray) {
                JSONObject letterJSON = (JSONObject) letterObj;

                // Process example words for each letter
                ArrayList<Word> exampleWordsList = new ArrayList<>();
                processWords(letterJSON, exampleWordsList);

                // Generate UUID for each letter
                UUID uuid = UUID.randomUUID();

                // Create a new Letter object with UUID and add it to lettersList
                String text = (String) letterJSON.get(TEXT);
                String pronunciation = (String) letterJSON.get(PRONUNCIATION);
                String image = (String) letterJSON.get(IMAGE);
                
                lettersList.add(new Letter(
                    text,
                    pronunciation,
                    exampleWordsList,
                    uuid  // Pass the generated UUID
                ));
            }
        }
    }


    /**
     * Processes story entries within the game data JSON object.
     *
     * @param gameDataJSON JSON object containing game data.
     * @param storiesList List to store processed Story objects.
     */
    private void processStories(JSONObject gameDataJSON, ArrayList<Story> storiesList) {
        if (gameDataJSON.containsKey(STORIES)) {
            JSONArray storiesArray = (JSONArray) gameDataJSON.get(STORIES);

            for (Object storyObj : storiesArray) {
                JSONObject storyJSON = (JSONObject) storyObj;

                ArrayList<Page> pagesList = new ArrayList<>();
                processPages(storyJSON, pagesList);

                ArrayList<Word> teachWordsList = new ArrayList<>();
                processWords(storyJSON, teachWordsList);

                storiesList.add(new Story(
                    (String) storyJSON.get(TITLE),
                    (String) storyJSON.get(AUTHOR),
                    pagesList,
                    teachWordsList
                ));
            }
        }
    }

    /**
     * Processes page entries within a story JSON object.
     *
     * @param storyJSON JSON object containing story data.
     * @param pagesList List to store processed Page objects.
     */
    private void processPages(JSONObject storyJSON, ArrayList<Page> pagesList) {
        if (storyJSON.containsKey(PAGES)) {
            JSONArray pagesArray = (JSONArray) storyJSON.get(PAGES);

            for (Object pageObj : pagesArray) {
                JSONObject pageJSON = (JSONObject) pageObj;

                pagesList.add(new Page(
                    String.valueOf(pageJSON.get(PAGE_NUMBER)),
                    (String) pageJSON.get(TEXT),
                    (String) pageJSON.get(ENGLISH_TEXT),
                    (String) pageJSON.get(IMAGE)
                ));
            }
        }
    }
}
