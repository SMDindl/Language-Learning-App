/**
 * The ColorsGame class represents a game where users can learn and quiz
 * themselves on color vocabulary in a specific language. It provides methods to 
 * study color words and take quizzes.
 */
package com.languageLearner.app;

import java.util.ArrayList;
import java.util.Scanner;

import com.languageLearner.data.DataKey;
import com.languageLearner.data.GameData;
import com.languageLearner.data.Question;
import com.languageLearner.data.Word;

public class ColorsGame {

    /** The game data instance used to retrieve colors and questions. */
    private GameData gameData;

    /** The key used to retrieve specific data from game data. */ 
    private DataKey dataKey;

    /**
     * Constructs a ColorsGame instance with the specified data key to retrieve
     * color-specific data.
     * 
     * @param dataKey the data key associated with the desired language data
     */
    public ColorsGame(DataKey dataKey) {
        this.gameData = GameData.getInstance();
        this.dataKey = dataKey;
    }

    /**
     * Starts the game, allowing the user to select a color to study or return to 
     * the main game selection. Initiates a quiz after studying.
     */
    public void startGame() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("What would you like to do?: \n1. Take a colors quiz \n2. Return to game selection");
        int option = keyboard.nextInt();
        if (option != 1) {
            System.out.println("Returning to game selection...");
            return;
        }

        //User picks the color to work on
        Word selection = pickColor();
        teachColor(selection);
        System.out.println("When you're ready for the quiz, hit the ENTER key.");
        //keyboard.next();

        askQuestion();
    }

    /**
     * Prompts the user to select a color word to study and returns the selected word.
     * 
     * @return the selected color word
     */
    public Word pickColor() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("\nWhich letter would you like to study?: \n");
        ArrayList<Word> colorList = gameData.getWords(dataKey);
        for(int i = 0; i < colorList.size(); i++) {
            //Prints out all the colors
            System.out.println((i+1) + ". " + colorList.get(i).getWordText());
        }
        Word selection = colorList.get(keyboard.nextInt() - 1);
        return selection;
    }

    /**
     * Displays the selected color word and its translation to the user.
     * 
     * @param color the color word to be taught
     */
    public void teachColor(Word color) {
        ArrayList<Word> newColors = gameData.getWords(dataKey);
        for(int i = 0; i < newColors.size(); i++) {
            //Might be better to store it as a different list, one that has the word and then the information being taught
            //For now, just storing it as a list of Word to print out the word and then the translation
            System.out.println(newColors.get(i).getWordText());
            System.out.println(newColors.get(i).getWordTranslation());
        }
    }

    /**
     * Asks the user a series of questions related to colors and provides feedback 
     * based on their answers.
     */
    public void askQuestion() {
        Scanner keyboard = new Scanner(System.in);
        ArrayList<Question> questionList = gameData.getQuestions(dataKey);
        for(int i = 0; i < questionList.size(); i++) {
            System.out.println(questionList.get(i).displayQuestion());
            provideFeedback(validateAnswer(keyboard.nextLine(), questionList.get(i)));
        }
    }

    /**
     * Validates the user's answer by comparing it with the correct answer.
     * 
     * @param answer the user's input answer
     * @param question the question object containing the correct answer
     * @return true if the answer is correct, false otherwise
     */
    public boolean validateAnswer(String answer, Question question) {
        return answer.equals(question.getCorrectAnswer());
     }

     /**
      * Provides feedback to the user based on whether their answer was correct or not.
      *
      * @param isCorrect true if the user's answer is correct, false otherwise
      */
    public void provideFeedback(boolean isCorrect) {
        if(isCorrect) 
            System.out.println("Well done!");
        else
            System.out.println("Better luck next time");
    }
}