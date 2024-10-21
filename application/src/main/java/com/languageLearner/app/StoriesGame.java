package com.languageLearner.app;

import java.util.ArrayList;
import java.util.Scanner;
import com.languageLearner.data.*;


public class StoriesGame {
    
    private GameData gameData;
    private DataKey dataKey;

    public StoriesGame(DataKey dataKey) {
        this.gameData = GameData.getInstance();
        this.dataKey = dataKey;
    }

    // Starts the story game, with the option to return to the game selection
    public void startGame() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("What would you like to do?: \n1. Pick a story to read \n2. Return to game selection");
        int option = keyboard.nextInt();

        if (option == 2) {
            System.out.println("Returning to game selection...");
            return;
        }

        // Automatically proceed to pick the story
        Story selectedStory = pickStory();
        teachWords(selectedStory);
        readStory(selectedStory);

        // Automatically move to the quiz after reading the story
        System.out.println("\nStory completed. Starting the quiz...\n");
        askQuestion();
    }

    // Selects a story based on the DataKey
    public Story pickStory() {
        ArrayList<Story> storyList = gameData.getStories(dataKey);
        System.out.println("\nPick a story to read:");
        for (int i = 0; i < storyList.size(); i++) {
            System.out.println((i + 1) + ". " + storyList.get(i).getTitle());
        }

        Scanner keyboard = new Scanner(System.in);
        int choice = keyboard.nextInt();
        return storyList.get(choice - 1);  // Selecting based on user's choice
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

    // Automatically asks the quiz after reading the story
    public void askQuestion() {
        ArrayList<Question> questionList = gameData.getQuestions(dataKey);
        Scanner keyboard = new Scanner(System.in);

        for (Question question : questionList) {
            System.out.println(question.displayQuestion());
            String userAnswer = keyboard.nextLine();
            provideFeedback(validateAnswer(userAnswer, question));
        }
    }

    // Validates the user's answer
    public boolean validateAnswer(String answer, Question question) {
        return answer.equalsIgnoreCase(question.getCorrectAnswer());
    }

    // Provides feedback after each question
    public void provideFeedback(boolean isCorrect) {
        if (isCorrect) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect.");
        }
    }
}
