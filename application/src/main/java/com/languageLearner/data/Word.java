package com.languageLearner.data;

import java.util.UUID;

/**
 * Represents a word in the language learning app with attributes like text, translation, 
 * an example sentence, and optionally a digit for number words.
 */
public class Word {
    
    private String wordText;
    private String wordTranslation;
    private String exampleSentence;
    private String sentenceTranslation;
    private String digit;  // Added to store the numeric value for number words
    private UUID id;

    // Constructor with digit, example sentence, and sentence translation
    public Word(String digit, String wordText, String wordTranslation, String exampleSentence, String sentenceTranslation, UUID id) {
        this.digit = digit;
        this.wordText = wordText;
        this.wordTranslation = wordTranslation;
        this.exampleSentence = exampleSentence;
        this.sentenceTranslation = sentenceTranslation;
        this.id = id;
    }

    // Constructor with digit but without example sentence or sentence translation
    public Word(String digit, String wordText, String wordTranslation, UUID id) {
        this.digit = digit;
        this.wordText = wordText;
        this.wordTranslation = wordTranslation;
        this.exampleSentence = null;
        this.sentenceTranslation = null;
        this.id = id;
    }

    // Constructor without digit
    public Word(String wordText, String wordTranslation, String exampleSentence, String sentenceTranslation, UUID id) {
        this.digit = null;
        this.wordText = wordText;
        this.wordTranslation = wordTranslation;
        this.exampleSentence = exampleSentence;
        this.sentenceTranslation = sentenceTranslation;
        this.id = id;
    }

    // Constructor without digit, example sentence, or sentence translation
    public Word(String wordText, String wordTranslation, UUID id) {
        this.digit = null;
        this.wordText = wordText;
        this.wordTranslation = wordTranslation;
        this.exampleSentence = null;
        this.sentenceTranslation = null;
        this.id = id;
    }

    // Getters
    public String getWordText() {
        return wordText;
    }

    public String getWordTranslation() {
        return wordTranslation;
    }

    public String getExampleSentence() {
        return exampleSentence; 
    }

    public String getSentenceTranslation() {
        return sentenceTranslation; 
    }

    public String getDigit() {
        return digit;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Word: " + wordText +
               ", Translation: " + wordTranslation +
               ", Digit: " + (digit != null ? digit : "N/A") +
               ", Example: " + (exampleSentence != null ? exampleSentence : "N/A") +
               " (Translation: " + (sentenceTranslation != null ? sentenceTranslation : "N/A") + ")";
    }
}
