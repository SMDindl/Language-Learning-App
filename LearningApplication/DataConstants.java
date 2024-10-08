
import java.util.Arrays;
import java.util.HashSet;

/**
 * The DataConstants for the Learning Application
 */
public class DataConstants {

    // JSON file names
    protected static final String USER_FILE = "data\\json\\Users.json";
    protected static final String GAME_DATA_FILE = "data\\json\\GameData.json";

    // Users
    protected static final String USERS = "users";

    // User instance information (list contained in UserList)
    protected static final String USER_ID = "id";
    protected static final String USERNAME = "userName";
    protected static final String DISPLAY_NAME = "displayName";
    protected static final String EMAIL = "email";
    protected static final String PASSWORD = "password";
    protected static final String PROGRESS_TRACKERS = "progressTrackers"; // array, of languages + completedGames per language

    // Progress Trackers information 
    protected static final String LANGUAGE = "language"; 
    protected static final String COMPLETED_GAMES = "completedGames"; // array, which is a list of string data keys

    // GameData class information (lists contained in GameData)
    
    // Language
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

    // Data Lists (arrays of data elements)
    protected static final String QUESTIONS = "questions"; 
    protected static final String STORIES = "stories"; 
    protected static final String WORDS = "words"; 
    protected static final String LETTERS = "letters"; 
    protected static final String PAGES = "pages"; 
    protected static final String NUMBERS = "numbers"; 

    // Data elements
    protected static final String TEXT = "text";                                // Associated with words, questions, stories, letters
    protected static final String ENGLISH_TEXT = "englishText";                 // Associated with text
    protected static final String EXAMPLE_SENTENCE = "exampleSentence";         // Associated with words
    protected static final String ENGLISH_SENTENCE = "englishSentence";         // Associated with data that uses exampleSentence (words)
    protected static final String DIGIT = "digit";                              // Associated with numbers 
    protected static final String PRONUNCIATION = "pronunciation";              // Associated with data that uses text (words, questions, stories, letters)
    protected static final String IMAGE = "image";                              // Associated with stories, letters, questions
    protected static final String TITLE = "title";                              // Associated with stories
    protected static final String AUTHOR = "author";                            // Associated with stories
    protected static final String PAGE_NUMBER = "pageNumber";                   // Associated with stories
    protected static final String CORRECT_CHOICE_INDEX = "correctChoiceIndex";  // Associated with questions
    protected static final String CHOICES = "choices";                          // Associated with questions, this is an array of strings

    // Valid values per type
    protected static final HashSet<String> VALID_LANGUAGES = new HashSet<>(Arrays.asList(FILIPINO));
    protected static final HashSet<String> VALID_GAME_TYPES = new HashSet<>(Arrays.asList(COLORS_GAME, NUMBERS_GAME, STORIES_GAME, ALPHABET_GAME));
    protected static final HashSet<String> VALID_DIFFICULTIES = new HashSet<>(Arrays.asList(EASY, MEDIUM, HARD));

}