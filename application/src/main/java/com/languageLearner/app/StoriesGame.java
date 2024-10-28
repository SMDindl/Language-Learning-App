package com.languageLearner.app;

import java.util.ArrayList;
import java.util.Scanner;

import com.languageLearner.data.DataKey;
import com.languageLearner.data.GameData;
import com.languageLearner.data.Page;
import com.languageLearner.data.Question;
import com.languageLearner.data.Story;
import com.languageLearner.data.Word;
import com.languageLearner.narration.Narrator;

public class StoriesGame {
    
    private GameData gameData;
    private DataKey dataKey;

    public StoriesGame() {
        this.gameData = GameData.getInstance();
        this.dataKey = DataKey.getInstance();  // Singleton instance of DataKey for StoriesGame
    }

    // Starts the story game with an option to return to the game selection
    public void startGame() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("What would you like to do?: \n1. Pick a story to read \n2. Return to game selection");
        int option = keyboard.nextInt();

        if (option != 1) {
            System.out.println("Returning to game selection...");
            return;
        }

        // User selects the story to read
        Story selectedStory = pickStory();
        teachWords(selectedStory);
        readStory(selectedStory);

        System.out.println("When you're ready for the quiz, hit the ENTER key");
        keyboard.nextLine();  // Clears the Scanner
        keyboard.nextLine();  // Waits for ENTER key

        System.out.println("\nStory completed. Starting the quiz...\n");
        askQuestions(selectedStory.getTitle());  // Pass the story title as context
    }

    // Allows user to select a story
    public Story pickStory() {
        ArrayList<Story> storyList = gameData.getStories(dataKey);
        System.out.println("\nPick a story to read:");
        for (int i = 0; i < storyList.size(); i++) {
            System.out.println((i + 1) + ". " + storyList.get(i).getTitle());
        }

        Scanner keyboard = new Scanner(System.in);
        int choice = keyboard.nextInt();
        return storyList.get(choice - 1);  // Select story based on user's choice
    }

    // Teaches words before the story starts
    public void teachWords(Story story) {
        ArrayList<Word> newWords = story.getTeachWords();
        System.out.println("\nWords to learn before reading the story:");
        for (Word word : newWords) {
            System.out.println(word.getWordText() + " - " + word.getWordTranslation());
        }
    }

    // Reads the story, page by page
    public void readStory(Story story) {
        ArrayList<Page> storyPages = story.getPages();
        for (Page page : storyPages) {
            System.out.println("\n--- Page " + page.getPageNumber() + " ---");
            System.out.println(page.getText());
            System.out.println("Translation: " + page.getEnglishText());
        }
    }

    // Asks questions based on the chosen story
    public void askQuestions(String storyTitle) {
        Scanner keyboard = new Scanner(System.in);

        // Retrieve questions specifically for this story's context
        ArrayList<Question> questionList = gameData.getQuestions(dataKey, storyTitle);

        for (Question question : questionList) {
            question.askQuestion();  // Display question text and options if applicable
            Narrator.playSoundMiguel(question.getText());

            // Handle different question types
            switch (question.getType()) {
                case GameData.TYPE_MULTIPLE_CHOICE:
                    System.out.println("Enter the number of your answer:");
                    int userChoice = keyboard.nextInt() - 1;
                    question.provideFeedback(question.validateAnswer(userChoice));
                    break;

                case GameData.TYPE_TRUE_FALSE:
                    System.out.println("Enter true or false:");
                    boolean userAnswer = keyboard.nextBoolean();
                    question.provideFeedback(question.validateAnswer(userAnswer));
                    break;

                default:
                    System.out.println("Unsupported question type.");
                    break;
            }
        }

        if (questionList.isEmpty()) {
            System.out.println("No questions available for this story.");
        }
    }
}
