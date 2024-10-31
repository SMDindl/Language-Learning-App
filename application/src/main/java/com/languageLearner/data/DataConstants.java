package com.languageLearner.data;

import java.util.Arrays;
import java.util.HashSet;

/**
 * The DataConstants for the Learning Application.
 */
public class DataConstants {

    // JSON file names
    protected static final String USER_FILE = "data/json/UsersTest.json";
    protected static final String GAME_DATA_FILE = "data/json/GameData.json";

    // Users
    protected static final String USERS = "users";
    protected static final String USER_ID = "uuid"; // Using USER_ID for user UUIDs
    protected static final String USERNAME = "username";
    protected static final String DISPLAY_NAME = "displayName";
    protected static final String EMAIL = "email";
    protected static final String PASSWORD = "password";
    protected static final String PROGRESS_TRACKERS = "progressTrackers";

    // Progress Trackers information 
    protected static final String LANGUAGE = "language"; 
    protected static final String COMPLETED_GAMES = "completedGames";
    protected static final String MISSED_QUESTIONS = "missedQuestions"; // Add constant for missed questions
    protected static final String QUESTION_UUID = "questionUUID"; // Add constant for question UUID
    protected static final String TYPE = "type"; // Add constant for question type
    protected static final String WORD_UUIDS = "wordUUIDs"; // Add constant for word UUIDs in matching questions

    // GameData class information
    protected static final String FILIPINO = "filipino";

    // Difficulties
    protected static final String EASY = "easy"; 
    protected static final String MEDIUM = "medium";
    protected static final String HARD = "hard";

    // Games
    protected static final String COLORS_GAME = "colorsGame";
    protected static final String STORIES_GAME = "storiesGame";
    protected static final String ALPHABET_GAME = "alphabetGame";
    protected static final String NUMBERS_GAME = "numbersGame";

    // Data Lists
    protected static final String QUESTIONS = "questions"; 
    protected static final String STORIES = "stories"; 
    protected static final String WORDS = "words"; 
    protected static final String LETTERS = "letters"; 
    protected static final String PAGES = "pages"; 

    // Data elements
    protected static final String TEXT = "text";
    protected static final String ENGLISH_TEXT = "englishText";
    protected static final String EXAMPLE_SENTENCE = "exampleSentence";
    protected static final String ENGLISH_SENTENCE = "englishSentence";
    protected static final String NUMBER = "number";
    protected static final String PRONUNCIATION = "pronunciation";
    protected static final String IMAGE = "image";
    protected static final String TITLE = "title";
    protected static final String AUTHOR = "author";
    protected static final String PAGE_NUMBER = "pageNumber";
    protected static final String CORRECT_CHOICE_INDEX = "correctChoiceIndex";
    protected static final String CORRECT_ANSWER = "correctAnswer";
    protected static final String CHOICES = "choices";
    protected static final String CONTEXT = "context"; // New constant for question context

    // Valid values per type
    protected static final HashSet<String> VALID_LANGUAGES = new HashSet<>(Arrays.asList(FILIPINO));
    protected static final HashSet<String> VALID_GAME_TYPES = new HashSet<>(Arrays.asList(COLORS_GAME, NUMBERS_GAME, STORIES_GAME, ALPHABET_GAME));
    protected static final HashSet<String> VALID_DIFFICULTIES = new HashSet<>(Arrays.asList(EASY, MEDIUM, HARD));
}