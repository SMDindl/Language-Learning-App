package com.languageLearner.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Question {

    private String id;
    private String type;
    private String text;
    private List<String> options;           // For multiple-choice and matching questions
    private Integer correctAnswerIndex;     // Index for multiple-choice questions
    private String correctAnswer;           // For true/false, FITB, and matching answers
    private String context;                 // Optional context (e.g., story title or letter name)
    private Word wordData;                  // For FITB questions involving word data

    // Constructor for multiple-choice questions
    public Question(String id, String type, String text, List<String> options, int correctAnswerIndex, String context) {
        this.id = id != null ? id : UUID.randomUUID().toString();
        this.type = type;
        this.text = text;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
        this.context = context;
    }

    // Constructor for true/false questions
    public Question(String id, String type, String text, boolean correctAnswer, String context) {
        this.id = id != null ? id : UUID.randomUUID().toString();
        this.type = type;
        this.text = text;
        this.correctAnswer = Boolean.toString(correctAnswer);
        this.context = context;
    }

    // Constructor for fill-in-the-blank (FITB) questions using `Word` data
    public Question(String type, Word wordData, String context) {
        this.id = wordData.getId().toString(); // Use UUID from Word
        this.type = type;
        this.wordData = wordData;
        this.context = context;
        generateFITBQuestion(); // Generate the FITB question dynamically
    }

    // Constructor for matching questions generated from multiple `Word` entries
    public Question(String type, List<Word> wordsList, String context) {
        this.id = UUID.randomUUID().toString(); // Generate unique UUID for matching
        this.type = type;
        this.context = context;
        generateMatchingQuestion(wordsList); // Generate the matching question dynamically
    }

    // Generates a FITB question by removing the word from the example sentence
    private void generateFITBQuestion() {
        this.text = wordData.getExampleSentence().replace(wordData.getWordText(), "_____");
        this.correctAnswer = wordData.getWordText();
    }

    // Generates a matching question by pairing text and translations from a list of `Word`
    private void generateMatchingQuestion(List<Word> wordsList) {
        StringBuilder questionText = new StringBuilder("Match the words with their translations:\n");
        this.options = new ArrayList<>();
        for (Word word : wordsList) {
            this.options.add(word.getWordText() + " - " + word.getWordTranslation());
            questionText.append(word.getWordText()).append(" : ").append(word.getWordTranslation()).append("\n");
        }
        this.text = questionText.toString();
    }

    /**
     * Validates the user's answer.
     *
     * @param userAnswer the user's answer to the question
     * @return true if the answer is correct, false otherwise
     */
    public boolean validateAnswer(Object userAnswer) {
        switch (type) {
            case "multiple_choice":
                return userAnswer instanceof Integer && correctAnswerIndex == (Integer) userAnswer;
            case "true_false":
            case "FITB":
                return correctAnswer.equalsIgnoreCase(userAnswer.toString());
            case "matching":
                return options.contains(userAnswer);
            default:
                return false;
        }
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
            System.out.println("Incorrect. Try again.");
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

    // Getters for relevant attributes
    public String getId() { return id; }
    public String getType() { return type; }
    public String getText() { return text; }
    public List<String> getOptions() { return options; }
    public Integer getCorrectAnswerIndex() { return correctAnswerIndex; }
    public String getCorrectAnswer() { return correctAnswer; }
    public String getContext() { return context; }
}
