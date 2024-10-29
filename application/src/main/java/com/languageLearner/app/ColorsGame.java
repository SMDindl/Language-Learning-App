package com.languageLearner.app;

import java.util.ArrayList;
import java.util.Scanner;

import com.languageLearner.data.DataKey;
import com.languageLearner.data.GameData;
import com.languageLearner.data.Word;

public class ColorsGame {
    private GameData gameData;
    private DataKey dataKey;

    /**
     * Constructs the ColorsGame instance with specified language and difficulty.
     * 
     * @param language The language of the game (e.g., "filipino").
     * @param difficulty The difficulty level of the game (e.g., "easy").
     */
    public ColorsGame() {
        this.gameData = GameData.getInstance();
        this.dataKey = DataKey.getInstance();
    }

    /**
     * Starts the Colors Game where the user can study colors and take a quiz.
     */
    public void startGame() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("What would you like to do?: \n1. Take a colors quiz \n2. Return to game selection");
        int option = keyboard.nextInt();
        if (option != 1) {
            System.out.println("Returning to game selection...");
            return;
        }

        teachColors();
        System.out.println("When you're ready for the quiz, hit the ENTER key.");
        keyboard.nextLine();  // Consume the newline
        keyboard.nextLine();  // Wait for Enter key

        askQuestions();
    }

    /**
     * Teaches colors by displaying each color's name and translation.
     */
    public void teachColors() {
        ArrayList<Word> colorList = gameData.getWords(dataKey);
        System.out.println("\n--- Learning Colors ---");
        for (Word color : colorList) {
            System.out.println(color.getWordText() + " = " + color.getWordTranslation());
        }
        System.out.println("--- End of Color List ---\n");
    }

    /**
     * Asks various question types related to colors, including multiple-choice, true/false, FITB, and matching.
     */
    public void askQuestions() {
        System.out.println("\n--- Colors Quiz ---");

        // Ask multiple-choice questions
        System.out.println("\n--- Multiple Choice Quiz ---");
        gameData.askQuestions(dataKey, 2, GameData.TYPE_MULTIPLE_CHOICE);

        // Ask true/false questions
        System.out.println("\n--- True/False Quiz ---");
        gameData.askQuestions(dataKey, 2, GameData.TYPE_TRUE_FALSE);

        // Ask fill-in-the-blank questions
        System.out.println("\n--- Fill-in-the-Blank Quiz ---");
        gameData.askQuestions(dataKey, 2, GameData.TYPE_FILL_IN_THE_BLANK);

        // Ask matching questions
        System.out.println("\n--- Matching Quiz ---");
        gameData.askQuestions(dataKey, 1, GameData.TYPE_MATCHING);
    }
}
