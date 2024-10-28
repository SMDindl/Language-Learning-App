package com.languageLearner.app;

import java.util.ArrayList;
import java.util.Scanner;

import com.languageLearner.data.DataKey;
import com.languageLearner.data.GameData;
import com.languageLearner.data.Question;
import com.languageLearner.data.Word;

public class NumbersGame {
    private GameData gameData;
    private DataKey dataKey;

    public NumbersGame() {
        this.gameData = GameData.getInstance();
        this.dataKey = DataKey.getInstance();  // Configurable DataKey for NumbersGame
    }

    /**
     * Starts the Numbers Game, allowing the user to choose between taking a quiz or returning to the menu.
     */
    public void startGame() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("What would you like to do?: \n1. Take a numbers quiz \n2. Return to game selection");
        int option = keyboard.nextInt();
        if (option != 1) {
            System.out.println("Returning to game selection...");
            return;
        }

        teachNumbers();
        System.out.println("When you're ready for the quiz, hit the ENTER key.");
        keyboard.nextLine();  // Clear newline buffer
        keyboard.nextLine();  // Wait for ENTER key to proceed to the quiz

        askQuestions();
    }

    /**
     * Displays numbers and their translations, including digits if available in the `Word` data.
     */
    public void teachNumbers() {
        ArrayList<Word> numbersList = gameData.getWords(dataKey);
        System.out.println("\n--- Learning Numbers ---");

        for (Word number : numbersList) {
            String digitInfo = number.getDigit() != null ? " (" + number.getDigit() + ")" : "";
            System.out.println(number.getWordText() + " = " + number.getWordTranslation() + digitInfo);
        }
        System.out.println("\n--- End of Number List ---\n");
    }

    /**
     * Iterates through questions for NumbersGame and handles multiple question types.
     */
    public void askQuestions() {
        ArrayList<Question> questionList = gameData.getQuestions(dataKey);
        Scanner keyboard = new Scanner(System.in);

        for (Question question : questionList) {
            question.askQuestion();  // Display question text and options if applicable

            switch (question.getType()) {
                case GameData.TYPE_MULTIPLE_CHOICE:
                    System.out.println("Select the correct option (e.g., 1, 2):");
                    int userChoice = keyboard.nextInt() - 1;
                    question.provideFeedback(question.validateAnswer(userChoice));
                    break;

                case GameData.TYPE_TRUE_FALSE:
                    System.out.println("Enter true or false:");
                    boolean userAnswer = keyboard.nextBoolean();
                    question.provideFeedback(question.validateAnswer(userAnswer));
                    break;

                case GameData.TYPE_FILL_IN_THE_BLANK:
                    System.out.println("Fill in the blank:");
                    String userText = keyboard.next();
                    question.provideFeedback(question.validateAnswer(userText));
                    break;

                case GameData.TYPE_MATCHING:
                    System.out.println("Match the number with its translation (e.g., '1-uno, 2-dos'):");
                    String userMatchAnswer = keyboard.next();
                    question.provideFeedback(question.validateAnswer(userMatchAnswer));
                    break;

                default:
                    System.out.println("Unknown question type.");
                    break;
            }
        }
    }
}
