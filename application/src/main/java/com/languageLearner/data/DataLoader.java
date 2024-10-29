package com.languageLearner.data;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
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
    private HashMap<UUID, Question> questionsUUIDMap = new HashMap<>(); // To store questions by UUID
    private HashMap<UUID, Word> wordsUUIDMap = new HashMap<>(); // To store words by UUID

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
                String email = (String) userJSON.get(EMAIL);
                String username = (String) userJSON.get(USERNAME);
                String displayName = (String) userJSON.get(DISPLAY_NAME);
                String password = (String) userJSON.get(PASSWORD);
                String uuidStr = (String) userJSON.get(USER_ID);
                
                // Validation
                if (email == null || username == null || displayName == null || password == null || uuidStr == null) {
                    System.err.println("Skipping user with missing fields: " + userJSON);
                    continue; 
                }
                
                UUID uuid = UUID.fromString(uuidStr);
                User user = new User(email, username, displayName, password, uuid);
                JSONArray trackersArray = (JSONArray) userJSON.get(PROGRESS_TRACKERS);
                
                for (Object trackerObj : trackersArray) {
                    JSONObject trackerJSON = (JSONObject) trackerObj;
                    String language = (String) trackerJSON.get(LANGUAGE);
                    ProgressTracker tracker = ProgressTracker.getInstance();
                    tracker.setLanguage(language);
                    
                    // Load completed games
                    JSONArray completedGamesArray = (JSONArray) trackerJSON.get(COMPLETED_GAMES);
                    for (Object game : completedGamesArray) {
                        tracker.addCompletedGame(DataKey.fromString((String) game));
                    }
    
                    // Load missed questions
                    JSONArray missedQuestionsArray = (JSONArray) trackerJSON.get("missedQuestions");
                    for (Object missedObj : missedQuestionsArray) {
                        JSONObject missedJSON = (JSONObject) missedObj;
                        String questionUUID = (String) missedJSON.get("questionUUID");
    
                        // Check if it's a matching question
                        if (missedJSON.containsKey("wordUUIDs")) {
                            ArrayList<UUID> wordUUIDs = new ArrayList<>();
                            JSONArray wordUUIDsArray = (JSONArray) missedJSON.get("wordUUIDs");
                            for (Object wordUUIDObj : wordUUIDsArray) {
                                UUID wordUUID = UUID.fromString((String) wordUUIDObj);
                                // Retrieve the corresponding word from the map
                                Word word = wordsUUIDMap.get(wordUUID);
                                if (word != null) {
                                    wordUUIDs.add(wordUUID); // Store the UUIDs
                                }
                            }
                            // Create a new question for matching using the retrieved words
                            ArrayList<Word> words = new ArrayList<>();
                            for (UUID wordUUID : wordUUIDs) {
                               words.add(wordsUUIDMap.get(wordUUID));
                            }
                            Question matchingQuestion = new Question("matching_question", words);
                            tracker.addMissedQuestion(matchingQuestion);
                        } else {
                            // For other question types, check UUID maps
                            UUID qUUID = UUID.fromString(questionUUID);
                            
                            // Check in questionsUUIDMap first
                            Question question = questionsUUIDMap.get(qUUID);
                            if (question != null) {
                                tracker.addMissedQuestion(question);
                            } else {
                                // If not found, check in wordsUUIDMap for FITB
                                Word word = wordsUUIDMap.get(qUUID);
                                if (word != null) {
                                    // Create a new FITB question using the word UUID
                                    word.getWordText();
                                    Question fitbQuestion = new Question("FITB", word, null);
                                    tracker.addMissedQuestion(fitbQuestion);
                                }
                            }
                        }
                    }
    
                    user.addProgressTracker(tracker);
                }
    
                userList.addUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    private void loadLanguages(JSONObject jsonObject) {
        for (Object languageKey : jsonObject.keySet()) {
            String language = (String) languageKey;
            JSONObject games = (JSONObject) jsonObject.get(language);
            loadGameTypes(games, language);
        }
    }

    private void loadGameTypes(JSONObject games, String language) {
        for (Object gameTypeKey : games.keySet()) {
            String gameType = (String) gameTypeKey;
            JSONObject difficulties = (JSONObject) games.get(gameType);
            loadDifficulties(difficulties, language, gameType);
        }
    }

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
     * Processes word entries within the game data JSON object.
     *
     * @param gameDataJSON JSON object containing game data.
     * @param wordsList List to store processed Word objects.
     */
    private void processWords(JSONObject gameDataJSON, ArrayList<Word> wordsList) {
        if (gameDataJSON.containsKey(WORDS)) {
            JSONArray wordsArray = (JSONArray) gameDataJSON.get(WORDS);
    
            for (Object wordObj : wordsArray) {
                JSONObject wordJSON = (JSONObject) wordObj;
                UUID id = wordJSON.containsKey(USER_ID) ? UUID.fromString((String) wordJSON.get(USER_ID)) : UUID.randomUUID();
    
                String wordText = (String) wordJSON.get(TEXT);
                String wordTranslation = (String) wordJSON.get(ENGLISH_TEXT);
                String exampleSentence = (String) wordJSON.get(EXAMPLE_SENTENCE);
                String sentenceTranslation = (String) wordJSON.get(ENGLISH_SENTENCE);
                String digit = (String) wordJSON.get(NUMBER);  // Retrieve digit if available
    
                // Select appropriate Word constructor based on available fields
                Word word;
                if (digit != null && exampleSentence != null && sentenceTranslation != null) {
                    word = new Word(digit, wordText, wordTranslation, exampleSentence, sentenceTranslation, id);
                } else if (digit != null) {
                    word = new Word(digit, wordText, wordTranslation, id);
                } else if (exampleSentence != null && sentenceTranslation != null) {
                    word = new Word(wordText, wordTranslation, exampleSentence, sentenceTranslation, id);
                } else {
                    word = new Word(wordText, wordTranslation, id);
                }
    
                // Add constructed word to the words list
                wordsList.add(word);
                wordsUUIDMap.put(word.getId(), word); // Used for dataLoading missed questions
            }
        }
    }
    
    private void processQuestions(JSONObject gameDataJSON, ArrayList<Question> questionsList) {
        if (gameDataJSON.containsKey(QUESTIONS)) {
            JSONArray questionsArray = (JSONArray) gameDataJSON.get(QUESTIONS);

            for (Object questionObj : questionsArray) {
                JSONObject questionJSON = (JSONObject) questionObj;
                UUID uuid = questionJSON.containsKey(USER_ID) ? UUID.fromString((String) questionJSON.get(USER_ID)) : UUID.randomUUID();
                String questionText = (String) questionJSON.get(TEXT);
                String context = questionJSON.containsKey(CONTEXT) ? (String) questionJSON.get(CONTEXT) : null;

                if (questionJSON.containsKey(CHOICES)) { // Create mutiple choice question

                    int correctAnswerIndex = Math.toIntExact((long) questionJSON.get(CORRECT_CHOICE_INDEX));
                    ArrayList<String> choices = new ArrayList<>();
                    JSONArray choicesArray = (JSONArray) questionJSON.get(CHOICES);
                    for (Object choice : choicesArray) {
                        choices.add((String) choice);
                    }
                    Question question = new Question(
                        uuid,
                        "multiple_choice",
                        questionText,
                        choices,
                        correctAnswerIndex,
                        context
                    );
                    // Add to the list (which will create the GameData hashmap)
                    questionsList.add(question);

                    // Add to the mash (which is used to manage missed questions)
                    questionsUUIDMap.put(question.getId(), question); 
            
                } else if (questionJSON.containsKey(CORRECT_ANSWER)) {  // Create true/false question

                    boolean correctAnswer = (boolean) questionJSON.get(CORRECT_ANSWER);
                    Question question = new Question(
                        uuid,
                        "true_false",
                        questionText,
                        correctAnswer,
                        context
                    );
                    // Add to the list (which will create the GameData hashmap)
                    questionsList.add(question);

                    // Add to the mash (which is used to manage missed questions)
                    questionsUUIDMap.put(question.getId(), question); 
                }
            }
        }
    }

    private void processLetters(JSONObject gameDataJSON, ArrayList<Letter> lettersList) {
        if (gameDataJSON.containsKey(LETTERS)) {
            JSONArray lettersArray = (JSONArray) gameDataJSON.get(LETTERS);

            for (Object letterObj : lettersArray) {
                JSONObject letterJSON = (JSONObject) letterObj;
                UUID uuid = letterJSON.containsKey(USER_ID) ? UUID.fromString((String) letterJSON.get(USER_ID)) : UUID.randomUUID();

                ArrayList<Word> exampleWordsList = new ArrayList<>();
                processWords(letterJSON, exampleWordsList);

                lettersList.add(new Letter(
                    (String) letterJSON.get(TEXT),
                    (String) letterJSON.get(PRONUNCIATION),
                    exampleWordsList,
                    uuid
                ));
            }
        }
    }

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

    private void processPages(JSONObject storyJSON, ArrayList<Page> pagesList) {
        if (storyJSON.containsKey(PAGES)) {
            JSONArray pagesArray = (JSONArray) storyJSON.get(PAGES);

            for (Object pageObj : pagesArray) {
                JSONObject pageJSON = (JSONObject) pageObj;

                pagesList.add(new Page(
                    (String) pageJSON.get(PAGE_NUMBER),
                    (String) pageJSON.get(TEXT),
                    (String) pageJSON.get(ENGLISH_TEXT),
                    (String) pageJSON.get(IMAGE)
                ));
            }
        }
    }
}