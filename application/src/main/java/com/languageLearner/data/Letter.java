package com.languageLearner.data;

import java.util.ArrayList;
import java.util.UUID;

public class Letter {
    
    private String text;
    private String pronunciation;
    private ArrayList<Word> exampleWords;
    private UUID uuid;

    /**
     * public Letter(String text, String pronunciation, ArrayList<Word> exampleWords, UUID uuid) {
     * @param text
     * @param pronunciation
     * @param exampleWords
     * @param uuid
     */
    public Letter(String text, String pronunciation, ArrayList<Word> exampleWords, UUID uuid) {
        this.text = text;
        this.pronunciation = pronunciation;
        this.exampleWords = exampleWords;
        this.uuid = uuid;
    }

    // Getters and setters
    public String getText() {
        return text;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public ArrayList<Word> getExampleWords() {
        return exampleWords;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "Letter{" +
                "text='" + text + '\'' +
                ", pronunciation='" + pronunciation + '\'' +
                ", exampleWords=" + exampleWords +
                ", uuid=" + uuid +
                '}';
    }
}
