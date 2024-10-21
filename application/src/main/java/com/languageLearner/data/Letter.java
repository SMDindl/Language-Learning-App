package com.languageLearner.data;

import java.util.ArrayList;

public class Letter {
    // Attributes
    private String letter;
    private String pronunciation;
    private ArrayList<String> exampleWords;

    // Constructor
    public Letter(String letter, String pronunciation, ArrayList<Word> exampleWords) {
        // Stub: Initialize attributes
    }

    // Methods
    public String getLetter() {
        return letter;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public ArrayList<String> getExampleWords() {
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
