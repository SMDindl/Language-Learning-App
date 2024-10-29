package com.languageLearner.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Question {

    private UUID id;
    private String type;
    private String text;
    private List<String> options;           // For multiple-choice and matching questions
    private Integer correctAnswerIndex;     // Index for multiple-choice questions
    private String correctAnswer;           // For true/false and FITB answers
    private String context;                 // Optional context (e.g., story title or letter name)
    private Word wordData;                  // For FITB questions involving word data
    private List<UUID> wordUUIDs;           // UUIDs of words for matching questions
    private User currentUser;

    // Constructor for multiple-choice questions
    public Question(UUID id, String type, String text, List<String> options, int correctAnswerIndex, String context) {
        this.id = id != null ? id : UUID.randomUUID();
        this.type = type;
        this.text = text;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
        this.context = context;
    }

    // Constructor for true/false questions
    public Question(UUID id, String type, String text, boolean correctAnswer, String context) {
        this.id = id != null ? id : UUID.randomUUID();
        this.type = type;
        this.text = text;
        this.correctAnswer = Boolean.toString(correctAnswer);
        this.context = context;
    }

    // Constructor for fill-in-the-blank (FITB) questions using `Word` data
    public Question(String type, Word wordData, String context) {
        this.id = wordData.getId(); // Use UUID from Word
        this.type = type;
        this.wordData = wordData;
        this.context = context;
        generateFITBQuestion(); // Generate the FITB question dynamically
    }

    /**
     * Constructor for matching questions generating matching question
     * @param type
     * @param wordsList
     * @param context
     */
    public Question(String type, ArrayList<Word> wordsList, String context) {
        this.id = UUID.randomUUID(); // Generate unique UUID for matching
        this.type = type;
        this.context = context;
        this.wordUUIDs = new ArrayList<>();
        for (Word word : wordsList) {
            this.wordUUIDs.add(word.getId()); 
        }
        generateMatchingQuestion(wordsList); // Generate the matching question dynamically
    }

    /**
     *  Constructor for loading matching questions from stored UUIDs
     * 
     * @param type
     * @param wordUUIDs
     */
    public Question(String type, ArrayList<Word> words) {
        this.id = UUID.randomUUID(); // Generate unique UUID for the matching question
        this.type = type;
        generateMatchingQuestion(words); // Generate the matching question dynamically
    }

    /**
     * Constructor for a matching question (DataWriting)
     * @param type
     * @param id
     */
    public Question(String type, UUID id) {
        this.type = type;
        this.id = id != null ? id : UUID.randomUUID();
        this.wordUUIDs = new ArrayList<>();
        // Set up other properties as needed
    }

    // Method to add a word UUID
    public void addWordUUID(UUID wordUUID) {
        this.wordUUIDs.add(wordUUID);
    }

    public void setWordUUIDs(UUID wordUUID) {
        
    }

    // Generates a FITB question by removing the word from the example sentence
    private void generateFITBQuestion() {
        this.text = wordData.getExampleSentence().replace(wordData.getWordText(), "_____");
        this.correctAnswer = wordData.getWordText();
    }

    // Generates a matching question by pairing text and translations from a list of `Word`
    private void generateMatchingQuestion(List<Word> wordsList) {
        this.options = new ArrayList<>();
        StringBuilder questionText = new StringBuilder("Match the words with their translations:\n");
        for (Word word : wordsList) {
            this.options.add(word.getWordText() + " - " + word.getWordTranslation());
            questionText.append(word.getWordText()).append(" : ").append(word.getWordTranslation()).append("\n");
        }
        this.text = questionText.toString();
    }

    /**
     * Sets options for the question. Used primarily for dynamically setting options in matching questions.
     *
     * @param wordTexts The words to be matched.
     * @param translations The translations or shuffled matching options.
     */
    public void setOptions(List<String> wordTexts, List<String> translations) {
        this.options = new ArrayList<>();
        StringBuilder questionText = new StringBuilder("Match the words with their translations:\n");
        for (int i = 0; i < wordTexts.size(); i++) {
            this.options.add(wordTexts.get(i) + " - " + translations.get(i));
            questionText.append(wordTexts.get(i)).append(" : ").append(translations.get(i)).append("\n");
        }
        this.text = questionText.toString();
    }

    /**
     * Validates the user's answer based on the question type.
     *
     * @param userAnswer the user's answer to the question
     * @return true if the answer is correct, false otherwise
     */
    public boolean validateAnswer(Object userAnswer, ProgressTracker tracker) {
        switch (type) {
            case "multiple_choice":
                if (userAnswer instanceof Integer && correctAnswerIndex.equals(userAnswer)) {
                    if (tracker != null) {
                        tracker.removeMissedQuestion(this); // Remove this question from missed questions
                    }
                    return true; // Answer is correct
                }
                break; // Don't forget to break to avoid falling through
    
            case "true_false":
            case "FITB":
                boolean isCorrect = correctAnswer.equalsIgnoreCase(userAnswer.toString());
                if (isCorrect && tracker != null) {
                    tracker.removeMissedQuestion(this);
                } else if (!isCorrect && tracker != null) {
                    tracker.addMissedQuestion(this);
                }
                return isCorrect;
    
            case "matching":
                boolean isMatchingCorrect = validateMatchingAnswer(userAnswer.toString());
                if (isMatchingCorrect && tracker != null) {
                    tracker.removeMissedQuestion(this);
                } else if (!isMatchingCorrect && tracker != null) {
                    tracker.addMissedQuestion(this);
                }
                return isMatchingCorrect;
    
            default:
                return false;
        }
        // If the answer was incorrect, add it to missed questions
        if (tracker != null) {
            tracker.addMissedQuestion(this);
        }
        return false; // Incorrect answer by default
    }
    

    /**
     * Validates a matching answer by comparing user input to correct pairs.
     *
     * @param userAnswer the user's answer as a comma-separated string of pairs
     * @return true if the answer matches all pairs, false otherwise
     */
    private boolean validateMatchingAnswer(String userAnswer) {
        HashSet<String> correctPairSet = new HashSet<>(options);
        HashSet<String> userPairSet = new HashSet<>(List.of(userAnswer.split(",")));
        return userPairSet.equals(correctPairSet);
    }

    /**
     * Provides feedback to the user based on the correctness of their answer.
     *
     * @param isCorrect whether the user's answer was correct
     */
    public void provideFeedback(boolean isCorrect) {
        if (isCorrect) {
            System.out.println("Correct!");
            
        } else {
            System.out.println("Incorrect.");
        }
    }

    /**
     * Displays the question text and options (if applicable) to the user.
     */
    public void askQuestion() {
        System.out.println(text);
        if ("multiple_choice".equals(type) && options != null) {
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ": " + options.get(i));
            }
        }
    }

    public boolean askMissedQuestion() {
        System.out.println(text);
        currentUser.getProgressTracker(DataKey.getInstance().getLanguage());

        Scanner keyboard = new Scanner(System.in);
        if ("multiple_choice".equals(type) && options != null) {
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ": " + options.get(i));
            }
            int answer = 0;
            answer = keyboard.nextInt()-1;
            provideFeedback(validateMissedQuestionAnswer(answer));
            return validateMissedQuestionAnswer(answer);
        }
        if ("true_false".equalsIgnoreCase(type)) {
            String answer = keyboard.nextLine();
            provideFeedback(validateMissedQuestionAnswer(answer));
            return validateMissedQuestionAnswer(answer);
            }
        if ("FITB".equalsIgnoreCase(type)) {
            String answer = keyboard.nextLine();
            provideFeedback(validateMissedQuestionAnswer(answer));
            return validateMissedQuestionAnswer(answer);
        }
        else {
            String answer = keyboard.nextLine();
            provideFeedback(validateMissedQuestionAnswer(answer, 0));
            return validateMissedQuestionAnswer(answer);
        }
            
    }

    public boolean validateMissedQuestionAnswer(int answer) {
        return answer == getCorrectAnswerIndex();
    }

    public boolean validateMissedQuestionAnswer(String TRUEORFALSE) {
        return TRUEORFALSE.equals(correctAnswer);
    }

    private boolean validateMissedQuestionAnswer(String userAnswer, int fakeInt) {
        HashSet<String> correctPairSet = new HashSet<>(options);
        HashSet<String> userPairSet = new HashSet<>(List.of(userAnswer.split(",")));
        return userPairSet.equals(correctPairSet);
    }


    // Getters for relevant attributes
    public UUID getId() { return id; }
    public String getType() { return type; }
    public String getText() { return text; }
    public List<String> getOptions() { return options; }
    public Integer getCorrectAnswerIndex() { return correctAnswerIndex; }
    public String getCorrectAnswer() { return correctAnswer; }
    public String getContext() { return context; }
    public List<UUID> getWordUUIDs() { return wordUUIDs; } 
}
