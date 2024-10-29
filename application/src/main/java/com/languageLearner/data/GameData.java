package com.languageLearner.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Singleton class responsible for managing game data, including words, questions,
 * stories, and letters, for different games based on language, difficulty, and context.
 */
public class GameData {

    private static GameData instance;
    private HashMap<DataKey, ArrayList<Word>> wordsMap;
    private HashMap<DataKey, ArrayList<Question>> questionsMap;
    private HashMap<DataKey, ArrayList<Story>> storiesMap;
    private HashMap<DataKey, ArrayList<Letter>> lettersMap;

    // UUID Maps (easy link to find question based on just their id)
    private HashMap<UUID, Question> questionsIDMap = new HashMap<>();       // 
    private HashMap<UUID, Word> wordsIDMap = new HashMap<>();               // 

    // Question type constants
    public static final String TYPE_MULTIPLE_CHOICE = "multiple_choice";
    public static final String TYPE_TRUE_FALSE = "true_false";
    public static final String TYPE_FILL_IN_THE_BLANK = "FITB";
    public static final String TYPE_MATCHING = "matching";

    // Singleton private constructor
    private GameData() {
        wordsMap = new HashMap<>();
        questionsMap = new HashMap<>();
        storiesMap = new HashMap<>();
        lettersMap = new HashMap<>();
    }

    /**
     * Retrieves the singleton instance of GameData.
     * 
     * @return the singleton instance of GameData.
     */
    public static GameData getInstance() {
        if (instance == null) {
            instance = new GameData();
        }
        return instance;
    }

    // Methods to add data directly
    public void addWords(DataKey dataKey, ArrayList<Word> words) {
        wordsMap.put(dataKey, words);
    }

    public void addQuestions(DataKey dataKey, ArrayList<Question> questions) {
        questionsMap.put(dataKey, questions);
    }

    public void addStories(DataKey dataKey, ArrayList<Story> stories) {
        storiesMap.put(dataKey, stories);
    }

    public void addLetters(DataKey dataKey, ArrayList<Letter> letters) {
        lettersMap.put(dataKey, letters);
    }

    // Retrieve words for a specific DataKey
    public ArrayList<Word> getWords(DataKey dataKey) {
        return wordsMap.get(dataKey);
    }

    // Retrieve questions for a specific DataKey, generating FITB and matching questions if needed
    public ArrayList<Question> getQuestions(DataKey dataKey) {
        ArrayList<Question> questions = questionsMap.get(dataKey);

        if (questions == null) {
            questions = new ArrayList<>();
            questionsMap.put(dataKey, questions);
        }

        // Generate FITB and matching questions from words if needed
        generateFITBQuestionsFromWords(dataKey, questions);
        generateMatchingQuestionFromWords(dataKey, questions);

        return questions;
    }

    // Retrieve questions for a specific DataKey and optional context, generating FITB and matching questions if needed
    public ArrayList<Question> getQuestions(DataKey dataKey, String context) {
        ArrayList<Question> questions = questionsMap.get(dataKey);

        if (questions == null) {
            questions = new ArrayList<>();
            questionsMap.put(dataKey, questions);
        }

        // Generate FITB and matching questions from words if they are needed
        generateFITBQuestionsFromWords(dataKey, questions);
        generateMatchingQuestionFromWords(dataKey, questions);

        // If context is provided, filter questions by the specific context
        if (context != null && !context.isEmpty()) {
            ArrayList<Question> contextQuestions = new ArrayList<>();
            for (Question question : questions) {
                if (context.equals(question.getContext())) {
                    contextQuestions.add(question);
                }
            }
            return contextQuestions;
        }

        return questions;  // Return all questions if no specific context is provided
    }

    // Retrieve letters for a specific DataKey
    public ArrayList<Letter> getLetters(DataKey dataKey) {
        return lettersMap.get(dataKey);
    }

    // Retrieve stories for a specific DataKey
    public ArrayList<Story> getStories(DataKey dataKey) {
        return storiesMap.get(dataKey);
    }

    /**
     * Retrieves the available games for a given language and difficulty.
     *
     * @param dataKey The DataKey representing the language and difficulty level.
     * @return A set of available game names.
     */
    public Set<String> getAvailableGamesForDifficulty(DataKey dataKey) {
        Set<String> availableGames = new HashSet<>();

        if (wordsMap.containsKey(dataKey)) availableGames.add("ColorsGame");
        if (questionsMap.containsKey(dataKey)) availableGames.add("AlphabetGame");
        if (storiesMap.containsKey(dataKey)) availableGames.add("StoriesGame");
        if (lettersMap.containsKey(dataKey)) availableGames.add("NumbersGame");

        return availableGames;
    }

    /**
     * Populates the maps for words, questions, stories, and letters.
     * 
     * @param words A map of words by DataKey.
     * @param questions A map of questions by DataKey.
     * @param stories A map of stories by DataKey.
     * @param letters A map of letters by DataKey.
     */
    public void populateData(HashMap<DataKey, ArrayList<Word>> words, 
                             HashMap<DataKey, ArrayList<Question>> questions,
                             HashMap<DataKey, ArrayList<Story>> stories, 
                             HashMap<DataKey, ArrayList<Letter>> letters) {
        this.wordsMap = words;
        this.questionsMap = questions;
        this.storiesMap = stories;
        this.lettersMap = letters;

        // Populate the questionsIDMap
        for (ArrayList<Question> questionList : questions.values()) {
            for (Question question : questionList) {
                questionsIDMap.put(question.getId(), question); 
            }
        }
        // Populate the wordsIDMap with UUIDs and Words
        for (ArrayList<Word> wordList : words.values()) {
            for (Word word : wordList) {
                wordsIDMap.put(word.getId(), word); // Map UUID to Word
            }
        }
    }

    // QUESTION HANDLING

    /**
     * Generates fill-in-the-blank (FITB) questions from words if needed.
     *
     * @param dataKey The DataKey representing the language and difficulty.
     * @param questions The list of questions to add FITB questions to.
     */
    private void generateFITBQuestionsFromWords(DataKey dataKey, ArrayList<Question> questions) {
        ArrayList<Word> words = wordsMap.get(dataKey);
        if (words == null) return;

        for (Word word : words) {
            if (word.getExampleSentence() != null) {
                // Create a new FITB question using the word and its example sentence
                Question fitbQuestion = new Question(TYPE_FILL_IN_THE_BLANK, word, null);
                questions.add(fitbQuestion);
            }
        }
    }

    /**
     * Generates a matching question from words if needed.
     *
     * @param dataKey The DataKey representing the language and difficulty.
     * @param questions The list of questions to add the matching question to.
     */
    private void generateMatchingQuestionFromWords(DataKey dataKey, ArrayList<Question> questions) {
        ArrayList<Word> words = wordsMap.get(dataKey);
        if (words == null || words.size() < 2) return; // Need at least two words for matching

        // Shuffle translations for matching
        List<String> wordTexts = new ArrayList<>();
        List<String> translations = new ArrayList<>();
        for (Word word : words) {
            wordTexts.add(word.getWordText());
            translations.add(word.getWordTranslation());
        }
        Collections.shuffle(translations); // Randomize translations for a challenging match

        // Create a matching question with shuffled translations
        Question matchingQuestion = new Question(TYPE_MATCHING, words, null);
        matchingQuestion.setOptions(wordTexts, translations); // Assuming this method to set shuffled options
        
        questions.add(matchingQuestion);
    }

    /**
     * Asks a specified number of questions of a given type.
     *
     * @param dataKey The DataKey representing the language, game type, and difficulty level.
     * @param numQuestions The number of questions to ask.
     * @param questionType The type of question to ask (e.g., "multiple_choice", "true_false", "FITB", "matching").
     */
    public void askQuestions(DataKey dataKey, int numQuestions, String questionType) {
        ArrayList<Question> questions = getQuestions(dataKey);

        if (questions == null || questions.isEmpty()) {
            System.out.println("No questions available for DataKey: " + dataKey);
            return;
        }

        int count = 0;
        ProgressTracker tracker = ProgressTracker.getInstance();

        for (Question question : questions) {
            if (question.getType().equalsIgnoreCase(questionType)) {
                System.out.println("Question ID: " + question.getId());
                question.askQuestion();

                // Show simulated answer for testing
                Object simulatedAnswer = getUserAnswer(questionType);
                System.out.println("Simulated Answer: " + simulatedAnswer);

                // Validate answer based on the type
                boolean isCorrect = question.validateAnswer(simulatedAnswer, tracker);
                question.provideFeedback(isCorrect);

                count++;
                if (count >= numQuestions) break;
            }
        }

        if (count == 0) {
            System.out.println("No questions matched the specified type.");
        }
    }

    /**
     * Asks a specified number of questions of a given type and context.
     *
     * @param dataKey The DataKey representing the language, game type, and difficulty level.
     * @param numQuestions The number of questions to ask.
     * @param questionType The type of question to ask (e.g., "multiple_choice", "true_false", "FITB", "matching").
     * @param context The specific context to filter questions (e.g., a story title or letter name). Can be null if no specific context is required.
     */
    public void askQuestions(DataKey dataKey, int numQuestions, String questionType, String context) {
        ArrayList<Question> questions = getQuestions(dataKey, context);
        if (questions == null || questions.isEmpty()) {
            System.out.println("No questions available for DataKey: " + dataKey);
            return;
        }

        int count = 0;
        ProgressTracker tracker = ProgressTracker.getInstance();

        for (Question question : questions) {
            // Filter by question type and context
            if (question.getType().equalsIgnoreCase(questionType) &&
                (context == null || context.equals(question.getContext()))) {
                
                System.out.println("Question ID: " + question.getId());
                question.askQuestion();

                // Simulate getting a user answer
                Object userAnswer = getUserAnswer(questionType);
                boolean isCorrect = question.validateAnswer(userAnswer, tracker);

                // Provide feedback
                question.provideFeedback(isCorrect);

                count++;
                if (count >= numQuestions) break;
            }
        }

        if (count == 0) {
            System.out.println("No questions matched the specified type and context.");
        }
    }

    /**
     * Simulates user input for testing purposes.
     *
     * @param questionType The type of question to simulate an answer for.
     * @return A simulated answer, which could be a choice index or a boolean value.
     */
    private Object getUserAnswer(String questionType) {
        switch (questionType) {
            case TYPE_MULTIPLE_CHOICE:
                return 1; // Simulate selecting the first choice
            case TYPE_TRUE_FALSE:
                return true; // Simulate answering "true"
            case TYPE_FILL_IN_THE_BLANK:
                return "sampleAnswer"; // Simulate a fill-in-the-blank answer
            case TYPE_MATCHING:
                return "1-A, 2-B"; // Simulate a formatted matching response
            default:
                return null;
        }
    }

    // PRINT METHODS (for debugging)

    /**
     * Prints a list of all questions for a specified DataKey.
     *
     * @param dataKey The DataKey representing the language, game type, and difficulty level.
     */
    public void printQuestionList(DataKey dataKey) {
        ArrayList<Question> questions = getQuestions(dataKey);
        if (questions == null || questions.isEmpty()) {
            System.out.println("No questions available for DataKey: " + dataKey);
            return;
        }

        System.out.println("=== Question List for DataKey: " + dataKey + " ===");
        for (Question question : questions) {
            System.out.println("Question ID: " + question.getId());
            System.out.println("Type: " + question.getType());
            System.out.println("Text: " + question.getText());
            System.out.println("Context: " + (question.getContext() != null ? question.getContext() : "N/A"));

            // Print options if it's a multiple-choice or matching question
            if (question.getOptions() != null) {
                System.out.println("Options: " + question.getOptions());
                System.out.println("Correct Answer Index: " + question.getCorrectAnswerIndex());
            } else if (question.getCorrectAnswer() != null) {
                System.out.println("Correct Answer: " + question.getCorrectAnswer());
            }

            System.out.println();  // Blank line between questions for readability
        }
        System.out.println("=== End of Question List ===");
    }

    /**
     * Prints out all game data in a structured format for debugging purposes.
     */
    public void printGameData() {
        System.out.println("=== Game Data ===");

        // Print Words
        System.out.println("\n--- Words ---");
        wordsMap.forEach((dataKey, wordsList) -> {
            System.out.println("DataKey: " + dataKey);
            for (Word word : wordsList) {
                System.out.println("  Word ID: " + word.getId());
                System.out.println("  Text: " + word.getWordText());
                System.out.println("  Translation: " + word.getWordTranslation());
                if (word.getExampleSentence() != null) {
                    System.out.println("  Example Sentence: " + word.getExampleSentence());
                    System.out.println("  Sentence Translation: " + word.getSentenceTranslation());
                }
                System.out.println();
            }
        });

        // Print Questions
        System.out.println("\n--- Questions ---");
        questionsMap.forEach((dataKey, questionsList) -> {
            System.out.println("DataKey: " + dataKey);
            for (Question question : questionsList) {
                System.out.println("  Question ID: " + question.getId());
                System.out.println("  Type: " + question.getType());
                System.out.println("  Text: " + question.getText());
                System.out.println("  Context: " + question.getContext());
                if (question.getOptions() != null) {
                    System.out.println("  Options: " + question.getOptions());
                    System.out.println("  Correct Answer Index: " + question.getCorrectAnswerIndex());
                } else if (question.getCorrectAnswer() != null) {
                    System.out.println("  Correct Answer: " + question.getCorrectAnswer());
                }
                System.out.println();
            }
        });

        // Print Stories
        System.out.println("\n--- Stories ---");
        storiesMap.forEach((dataKey, storiesList) -> {
            System.out.println("DataKey: " + dataKey);
            for (Story story : storiesList) {
                System.out.println("  Title: " + story.getTitle());
                System.out.println("  Author: " + story.getAuthor());
                for (Page page : story.getPages()) {
                    System.out.println("    Page Number: " + page.getPageNumber());
                    System.out.println("    Text: " + page.getText());
                    System.out.println("    English Text: " + page.getEnglishText());
                }
                System.out.println();
            }
        });

        // Print Letters
        System.out.println("\n--- Letters ---");
        lettersMap.forEach((dataKey, lettersList) -> {
            System.out.println("DataKey: " + dataKey);
            for (Letter letter : lettersList) {
                System.out.println("  Text: " + letter.getText());
                System.out.println("  Pronunciation: " + letter.getPronunciation());
                System.out.println("  Associated Words: ");
                for (Word word : letter.getExampleWords()) {
                    System.out.println("    Word: " + word.getWordText() + " - " + word.getWordTranslation());
                }
                System.out.println();
            }
        });

        System.out.println("=== End of Game Data ===");
    }
}
