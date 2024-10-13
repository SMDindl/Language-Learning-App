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

    // Additional method to display letter details
    public String displayLetterDetails() {
        return "Letter: " + letter + "\nPronunciation: " + pronunciation + "\nExample Words: " + exampleWords;
    }
}
