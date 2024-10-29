package com.languageLearner.data;

import java.util.ArrayList;
import java.util.UUID;

public class Letter {
    // Attributes
    private String letter;
    private String pronunciation;
    private ArrayList<Word> exampleWords;
    private UUID uuid;

    public Letter(String text, String pronunciation, ArrayList<Word> exampleWords, UUID uuid) {
        this.text = text;
        this.pronunciation = pronunciation;
        this.exampleWords = exampleWords;
        this.image = null; // No image provided
    }

    // Methods
    public String getLetter() {
        return letter;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public ArrayList<Word> getExampleWords() {
    public ArrayList<Word> getExampleWords() {
        return exampleWords;
    }

    public String displayExampleWords() {
        String exampleWordsString = "";
        for(int i = 0; i<exampleWords.size(); i++) {
            exampleWordsString += ((i+1) + ". " + exampleWords.get(i) + "\n");
        }
        return exampleWordsString;
    }

    // Additional method to display letter details
    public String displayLetterDetails() {
        return "Letter: " + letter + "\nPronunciation: " + pronunciation + "\nExample Words: \n" + displayExampleWords();
    }
}
