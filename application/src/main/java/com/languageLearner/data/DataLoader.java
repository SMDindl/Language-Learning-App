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
    private DataKey dataKey = DataKey.getInstance();

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

    private void loadMissedQuestions(ProgressTracker tracker, JSONObject trackerJSON) {
        JSONArray missedQuestionsArray = (JSONArray) trackerJSON.get("missedQuestions");
        if (missedQuestionsArray != null) {
            for (Object missedObj : missedQuestionsArray) {
                JSONObject missedJSON = (JSONObject) missedObj;
                String questionUUID = (String) missedJSON.get(USER_ID);
                if (questionUUID != null) {
                    tracker.addMissedQuestion(UUID.fromString(questionUUID));
                }
            }
        }
    }

    private UUID parseUUID(String uuidStr) {
        try {
            return UUID.fromString(uuidStr);
        } catch (IllegalArgumentException | NullPointerException e) {
            System.err.println("Invalid or null UUID: " + uuidStr);
            return null;
        }
    }

    private void loadLanguages(JSONObject jsonObject) {
        for (Object languageKey : jsonObject.keySet()) {
            String language = (String) languageKey;
            // dataKey.setLanguage(language);
            JSONObject games = (JSONObject) jsonObject.get(language);
            loadGameTypes(games, language);
        }
    }

    private void loadGameTypes(JSONObject games, String language) {
        for (Object gameTypeKey : games.keySet()) {
            String gameType = (String) gameTypeKey;
            // dataKey.setGameType(gameType);
            JSONObject difficulties = (JSONObject) games.get(gameType);
            loadDifficulties(difficulties, language, gameType);
        }
    }

    private void loadDifficulties(JSONObject difficulties, String language, String gameType) {
        for (Object difficultyKey : difficulties.keySet()) {
            String difficulty = (String) difficultyKey;
            // dataKey.setDifficulty(difficulty);
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

                if (questionJSON.containsKey(CHOICES)) {
                    int correctAnswerIndex = Math.toIntExact((long) questionJSON.get(CORRECT_CHOICE_INDEX));
                    ArrayList<String> choices = new ArrayList<>();
                    JSONArray choicesArray = (JSONArray) questionJSON.get(CHOICES);
                    for (Object choice : choicesArray) {
                        choices.add((String) choice);
                    }
                    questionsList.add(new Question(
                        uuid,
                        "multiple_choice",
                        questionText,
                        choices,
                        correctAnswerIndex,
                        context
                    ));
                } else if (questionJSON.containsKey(CORRECT_ANSWER)) {
                    boolean correctAnswer = (boolean) questionJSON.get(CORRECT_ANSWER);
                    questionsList.add(new Question(
                        uuid,
                        "true_false",
                        questionText,
                        correctAnswer,
                        context
                    ));
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
