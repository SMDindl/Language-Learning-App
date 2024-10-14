package com.languageLearner.data;

import java.util.ArrayList;

public class Story {
    // Attributes
    private String title;
    private ArrayList<Page> pages;
    private String author;
    private int position;
    private ArrayList<Word> teachWords;

    // Constructor
<<<<<<< HEAD:LearningApplication/Story.java
    public Story(String title, ArrayList<Page> pages, String author, ArrayList<Word> teachWords) {
=======
    public Story(String title, String author, ArrayList<Page> pages, ArrayList<Word> teachWords) {
>>>>>>> main:application/src/main/java/com/languageLearner/data/Story.java
        this.title = title;
        this.pages = pages;
        this.author = author;
        this.teachWords = teachWords;
        this.position = 0;
    }

    // Methods
    public String getTitle() {
        return title; // Placeholder
    }

    public ArrayList<Page> getPages() {
        return pages; // Placeholder
    }

    public String getAuthor() {
        return author; // Placeholder
    }

    public int getPosition() {
        return position; // Placeholder
    }

    public ArrayList<Word> getTeachWords() {
        return teachWords;
    }
}
