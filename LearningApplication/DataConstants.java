
/**
 * 
 */
public class DataConstants {

    // Users
    protected static final String Users = "users";

    // User instance information (list contained in UserList)
    protected static final String USER_FILE_NAME = "json\\Users.json";
	protected static final String USER_ID = "id";
	protected static final String USER_USER_NAME = "userName";
	protected static final String DISPLAY_NAME = "displayName";
	protected static final String EMAIL = "email";
	protected static final String PASSWORD = "password";
    protected static final String PROGRESS_TRACKERS = "progressTrackers"; // array

    // Progress Trackers information (list contained in User)
    protected static final String LANGUAGE = "language"; 
    protected static final String COMPLETED_GAMES = "completedGames"; // array

    // GameData class information (lists contained in GameData)
    // GameKey info
    // Language(s)
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

    // Data Types
    // Question 
    protected static final String QUESTIONS = "questions"; // array

    // Questions instance information (list contained in GameData)
    protected static final String QUESTION_TEXT = "questionText";
    protected static final String QUESTION_TEXT_TRANSLATION = "questionTextTranslation";
    protected static final String QUESTION_IMAGE = "questionImage";
    protected static final String CORRECT_ANSWER_INDEX = "correctAnswer";
    protected static final String ANSWERS = "answers"; // array
    protected static final String CORRECT_ANSWER_EXPLANATION = "correctAnswerExplaination";

    // Letters
    protected static final String LETTERS = "letters"; // array

    // Letter instance information (list contained in GameData)
    protected static final String LETTER_TEXT = "letterText";
    protected static final String PRONUNCATION = "pronuncation";
    protected static final String EXAMPLE_WORDS = "exampleWords"; // array

    // Words
    protected static final String WORDS = "words"; // array

    // Word instance information (list contained in GameData)
    protected static final String WORD_TEXT = "wordText";
    protected static final String WORD_TRANSLATION = "wordTranslation";
    protected static final String EXAMPLE_SENTENCE = "exampleSentence"; 
    protected static final String SENTENCE_TRANSLATION = "sentenceTranslation";

    // Stories
    protected static final String STORIES = "stories"; // array

    // Story instance information (list contained in GameData)
    protected static final String TITLE = "title";
    protected static final String TITLE_TRANSLATION = "titleTranslation";
    protected static final String AUTHOR = "author";
    protected static final String PAGES = "pages"; // array

    // Page
    protected static final String PAGE_NUMBER = "pageNumber";
    protected static final String PAGE_TEXT = "pageText";
    protected static final String TEXT_TRANSLATION = "textTranslation";
    protected static final String IMAGE = "image";

}
