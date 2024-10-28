/**
 * The NumbersGame class represents a game that allows users to learn and quiz
 * themselves on numbers from 0-9 in a specific language. It provides methods for selecting
 * a number to study, teaching the number, and taking quizzes.
 */
package com.languageLearner.app;

import java.util.ArrayList;
import java.util.Scanner;

import com.languageLearner.data.DataKey;
import com.languageLearner.data.GameData;
import com.languageLearner.data.Question;
import com.languageLearner.data.Word;

public class NumbersGame {

    /** The game data instance used to retrieve numbers and questions. */
    private GameData gameData;

    /** The data key used to specify language or context for retrieving data. */
    private DataKey dataKey;

    /**
     * Constructs a NumbersGame instance, intializing game data and data key for
     * retrieving language-specific information.
     */
    public NumbersGame() {
        this.gameData = GameData.getInstance();
        this.dataKey = DataKey.getInstance();
    }

    /**
     * Starts the game by allowing the user to select a number to stufy and
     * quizzing them afterward.
     */
    public void startGame() {
        System.out.println("What would you like to do?: \n1. Take a numbers quiz \n2. Return to game selection");
        int prompt = 1;
        if (prompt == 2) {
            //Implement this to go back to the game selection
        }

        //User picks the number to work on
        Word selection = pickNumber();
        teachNumber(selection);
        System.out.println("When you're ready for the quiz, hit the ENTER key.");
        //keyboard.next();

        askQuestion();
    }

    /**
     * Prompts the user to select a number to study and returns the selected number.
     * 
     * @return the selected number word
     */
    public Word pickNumber() {
        System.out.println("\nWhich number from 0-9 would you like to learn?: \n");
        ArrayList<Word> numbersList = gameData.getWords(dataKey);
        //User will be prompted to select a number
        Word selection = numbersList.get(0); // 0 is a placeholder, implement selection logic
        return selection;
    }

    /**
     * Displays the number and its translation to the user.
     * 
     * @param number the number word to be taught
     */
    public void teachNumber(Word number) {
        System.out.println(number.getWordText());
        System.out.println(number.getWordTranslation());
    }

    /**
     * Asks the user questions based on the selected numbers 
     * and provides feedback on their answers.
     */
    public void askQuestion() {
        Scanner keyboard = new Scanner(System.in);
        ArrayList<Question> questionList = gameData.getQuestions(dataKey);
        for (int i = 0; i < questionList.size(); i++) {
            System.out.println(questionList.get(i).displayQuestion());
            provideFeedback(validateAnswer(keyboard.nextLine(), questionList.get(i)));
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
        return answer.equals(question.getCorrectAnswer());
    }

    /**
     * Provides feedback to the user based on whether their answer was correct or not.
     * 
     * @param isCorrect true if the user's answer is correct, false otherwise
     */
    public void provideFeedback(boolean isCorrect) {
        if (isCorrect)
            System.out.println("Well done!");
        else
            System.out.println("Better luck next time");
    }
}