package com.languageLearner.app;

import java.util.ArrayList;
import java.util.Scanner;

import com.languageLearner.data.DataKey;
import com.languageLearner.data.GameData;
import com.languageLearner.data.Letter;

public class AlphabetGame {
    private GameData gameData;
    private DataKey dataKey;

    public AlphabetGame() {
        this.gameData = GameData.getInstance();
        this.dataKey = DataKey.getInstance(); // Example DataKey for Alphabet Game
    }

    /**
     * Starts the Alphabet Game where the user can select a letter to study and then take a quiz.
     */
    public void startGame() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("What would you like to do?: \n1. Take a letters quiz \n2. Return to game selection");
        int option = keyboard.nextInt();
        if (option != 1) {
            System.out.println("Returning to game selection...");
            return;
        }

        // User picks the letter to work on
        Letter selection = pickLetter();
        teachLetter(selection);
        System.out.println("When you're ready for the quiz, hit the ENTER key.");
        keyboard.nextLine(); // Clears the Scanner buffer
        keyboard.nextLine(); // Waits for the user to hit the ENTER key

        // Move to the quiz after studying the letter
        askQuestions(selection.getText()); // Pass the letter as context for questions
    }

    /**
     * Prompts the user to pick a letter to study.
     *
     * @return the selected Letter object
     */
    public Letter pickLetter() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("\nType the number of the letter you would like to study: \n");
        ArrayList<Letter> letterList = gameData.getLetters(this.dataKey);
        for (int i = 0; i < letterList.size(); i++) {
            System.out.println((i + 1) + ". " + letterList.get(i).getText());
        }
        return letterList.get(keyboard.nextInt() - 1);
    }

    /**
     * Displays pronunciation and example words for the selected letter.
     *
     * @param letter the selected Letter object
     */
    public void teachLetter(Letter letter) {
        System.out.println("Pronunciation: " + letter.getPronunciation());
        System.out.println("Example words: " + letter.getExampleWords());
    }

    /**
     * Asks a series of multiple-choice and true/false questions specific to the selected letter.
     *
     * @param letterContext the letter being quizzed on, used as context
     */
    public void askQuestions(String letterContext) {
        // Ask multiple-choice questions related to the selected letter
        System.out.println("\n--- Multiple Choice Quiz ---");
        gameData.askQuestions(this.dataKey, 2, GameData.TYPE_MULTIPLE_CHOICE, letterContext);

        // Ask true/false questions related to the selected letter
        System.out.println("\n--- True/False Quiz ---");
        gameData.askQuestions(this.dataKey, 2, GameData.TYPE_TRUE_FALSE, letterContext);
    }
}
