/**
 * The AlphabetGame class represents a game that allows users to study
 * and quiz themselves on letters from a specific language. It offers methods
 * for selecting a letter to study, teaching the letter and quizzing the user.
 */
package com.languageLearner.app;

import java.util.ArrayList;
import java.util.Scanner;

import com.languageLearner.data.DataKey;
import com.languageLearner.data.GameData;
import com.languageLearner.data.Letter;
import com.languageLearner.data.Question;

public class AlphabetGame {

    /** The game data used to retrieve letters and questions. */
    private GameData gameData;

    /** The key used to retrieve specific data from game data. */
    private DataKey dataKey;

    /**
     * Constructs an AlphabetGame instance with the specified data key to retrieve
     * language-specific data.
     * 
     * @param dataKey the data key associated with the desired language data
     */
    public AlphabetGame(DataKey dataKey) {
        this.gameData = GameData.getInstance();
        this.dataKey = dataKey;
    }

    /**
     * Starts the game by providing the user with options to study letters or exit
     * to the main menu. The game proceeds to quiz the user after they study a
     * letter.
     */
    public void startGame() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("What would you like to do?: \n1. Take a letters quiz \n2. Return to game selection");
        int option = keyboard.nextInt();
        if (option != 1) {
            System.out.println("Returning to game selection...");
            return;
        }

        //User picks the letter to work on
        Letter selection = pickLetter();
        teachLetter(selection);
        System.out.println("When you're ready for the quiz, hit the ENTER key.");
        keyboard.nextLine(); //Clears the Scanner, otherwise it doesn't wait for the user input
        keyboard.nextLine(); //Waits for the user to hit the ENTER key
        //Move to the quiz after studying the letter

        askQuestion();
    }

    /**
     * Prompts the user to select a letter from the available list, then returns the
     * selected letter.
     * 
     * @return the selected Letter object that user chose to study
     */
    public Letter pickLetter() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("\nType the number of the letter you would like to study?: \n");
        ArrayList<Letter> letterList = gameData.getLetters(this.dataKey);
        for(int i = 0; i < letterList.size(); i++) {
            //Prints out all the letters
            System.out.println((i+1) + ". " + letterList.get(i).getLetter());
        }
        Letter selection = letterList.get(keyboard.nextInt() - 1);
        return selection;
    }

    /**
     * Displays information about the specified letter, including its pronunciation
     * and example words.
     * 
     * @param letter the Letter object to be taught
     */
    public void teachLetter(Letter letter) {
        System.out.println(letter.getPronunciation());
        System.out.println(letter.getExampleWords());
    }

    /**
     * Asks the user a series of questions about the letters they studied and
     * provides feedback on their answers.
     */
    public void askQuestion() {
        Scanner keyboard = new Scanner(System.in);
        DataKey dataKey = DataKey.getInstance();
        ArrayList<Question> questionList = gameData.getQuestions(dataKey);
        for(int i = 0; i < questionList.size(); i++) {
            System.out.println(questionList.get(i).displayQuestion());
            provideFeedback(validateAnswer(keyboard.nextLine(), questionList.get(i)));
        }
    }

    /**
     * Validates the user's answer by comparing it with the correct answer of the
     * question.
     * 
     * @param answer the user's answer input
     * @param question the Question object containing the correct answer
     * @return true if the answer is correct, false otherwise
     */
    public boolean validateAnswer(String answer, Question question) {
        return answer.equals(question.getCorrectAnswer());
     }

     /**
      * Provides feedback to the user based on whether their answer was correct.
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