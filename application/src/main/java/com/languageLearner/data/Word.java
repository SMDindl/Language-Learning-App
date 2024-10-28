package com.languageLearner.data;

import java.util.UUID;

/**
 * 
 */
public class Word {
    
    private String wordText;
    private String wordTranslation;
    private String exampleSentence;
    private String sentenceTranslation;
    private UUID id;

    // Constructor
    public Word(String wordText, String wordTranslation, String exampleSentence, String sentenceTranslation) {
        this.wordText = wordText;
        this.wordTranslation = wordTranslation;
        this.exampleSentence = exampleSentence;
        this.sentenceTranslation = sentenceTranslation;
    }

    public Word(String wordText, String wordTranslation) {
        this.wordText = wordText;
        this.wordTranslation = wordTranslation;
        this.exampleSentence = null;
        this.sentenceTranslation = null;
    }

    // Methods
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

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Word: " + wordText +
               ", Translation: " + wordTranslation +
               ", Example: " + exampleSentence +
               " (Translation: " + sentenceTranslation + ")";
    }

}
