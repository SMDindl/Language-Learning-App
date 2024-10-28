/**
 * The StoriesGame class represents a game where users can read stories in a
 * specific language, learn vocabulary words, and take quizzes related to the stories.
 * This class offers methods for selecting a story, teaching key vocbulary, reading
 * pages of the story, and administering quizzes.
 */
package com.languageLearner.app;

import java.util.ArrayList;
import java.util.Scanner;

import com.languageLearner.data.DataKey;
import com.languageLearner.data.GameData;
import com.languageLearner.data.Page;
import com.languageLearner.data.Question;
import com.languageLearner.data.Story;
import com.languageLearner.data.Word;


public class StoriesGame {
    
    /** The game data instance used to retrieve stories, words, ans questions. */
    private GameData gameData;

    /** The data key used to specify language or context for retrieving data. */
    private DataKey dataKey;

    /**
     * Constructs a StoriesGame instance with the specified data key to retrieve
     * 
     * @param dataKey the data key associated with the desired language data
     */
    public StoriesGame(DataKey dataKey) {
        this.gameData = GameData.getInstance();
        this.dataKey = dataKey;
    }

    // Starts the story game, with the option to return to the game selection
    public void startGame() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("What would you like to do?: \n1. Pick a story to read \n2. Return to game selection");
        int option = keyboard.nextInt();

        if (option != 1) {
            System.out.println("Returning to game selection...");
            return;
        }

        // Automatically proceed to pick the story
        Story selectedStory = pickStory();
        teachWords(selectedStory);
        readStory(selectedStory);
        System.out.println("When you're ready for the quiz, hit the ENTER key");
        keyboard.nextLine(); //Clears the Scanner, otherwise it doesn't wait for the user input
        keyboard.nextLine(); //Waits for the user to hit the ENTER key
        //Move to the quiz after reading the story
        System.out.println("\nStory completed. Starting the quiz...\n");
        askQuestion();
    }

    /**
     * Prompts the user to select a story to read and returns the selected story.
     * 
     * @return the selected Story object
     */
    public Story pickStory() {
        ArrayList<Story> storyList = gameData.getStories(dataKey);
        System.out.println("\nPick a story to read:");
        for (int i = 0; i < storyList.size(); i++) {
            System.out.println("\n" + (i + 1) + ". " + storyList.get(i).getTitle());
        }

        Scanner keyboard = new Scanner(System.in);
        int choice = keyboard.nextInt();
        return storyList.get(choice - 1);  // Selecting based on user's choice
    }

    /**
     * Displays the vocabulary words to learn before reading the story.
     * 
     * @param story the selected story containing words to teach
     */
    public void teachWords(Story story) {
        ArrayList<Word> newWords = story.getTeachWords();
        System.out.println("\nWords to learn before reading the story:");
        for (Word word : newWords) {
            System.out.println(word.getWordText() + " - " + word.getWordTranslation());
        }
    }

    /**
     * Reads the pages of the selected story and displays the text and its translation.
     * 
     * @param story the selected story to read
     */
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

    /**
     * Validates the user's answer against the correct answer for the question.
     * 
     * @param answer the user's answer
     * @param question the question to validate against
     * @return true if the answer is correct, false otherwise
     */
    public boolean validateAnswer(String answer, Question question) {
        return answer.equalsIgnoreCase(question.getCorrectAnswer());
    }

    /**
     * Provides feedback to the user based on whether their answer was correct or not.
     * 
     * @param isCorrect true if the user's answer is correct, false otherwise
     */
    public void provideFeedback(boolean isCorrect) {
        if (isCorrect) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect.");
        }
    }
}
