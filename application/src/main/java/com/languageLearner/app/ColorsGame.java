package com.languageLearner.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.languageLearner.data.DataKey;
import com.languageLearner.data.GameData;
import com.languageLearner.data.Question;
import com.languageLearner.data.Word;
import com.languageLearner.narration.Narrator;

public class ColorsGame {
    private GameData gameData;
    private DataKey dataKey;

    public ColorsGame() {
        this.gameData = GameData.getInstance();
        this.dataKey = DataKey.getInstance();  // Singleton instance of DataKey for ColorsGame
    }

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

    public void teachColors() {
        ArrayList<Word> colorList = gameData.getWords(dataKey);
        for (Word color : colorList) {
            System.out.println(color.getWordText() + " = " + color.getWordTranslation());
        }
    }

    public void askQuestions() {
        Scanner keyboard = new Scanner(System.in);
        ArrayList<Question> questionList = gameData.getQuestions(dataKey);

        // Iterate through the question list
        for (Question question : questionList) {
            question.askQuestion(); // Display the question text and options if applicable
            Narrator.playSoundMiguel(question.getText());

            switch (question.getType()) {
                case "multiple_choice":
                    int userChoice = keyboard.nextInt() - 1;
                    question.provideFeedback(question.validateAnswer(userChoice));
                    break;

                case "true_false":
                    System.out.println("Enter true or false:");
                    boolean userAnswer = keyboard.nextBoolean();
                    question.provideFeedback(question.validateAnswer(userAnswer));
                    break;

                case "FITB":
                    System.out.println("Fill in the blank:");
                    String userText = keyboard.next();
                    question.provideFeedback(question.validateAnswer(userText));
                    break;

                default:
                    System.out.println("Unknown question type.");
                    break;
            }
        }

        // Hardcoded matching question to appear last
        askHardcodedMatchingQuestion(keyboard);

        //Matching Question
        System.out.println("Match the color in English with the color in Fillipino");
        System.out.println("Red : Berde");
        System.out.println("Blue : Dilaw");
        System.out.println("Green : Asul");
        System.out.println("Yellow: Pula");
    }

    private void askHardcodedMatchingQuestion(Scanner keyboard) {
        // Example word list for matching question
        List<Word> matchingWords = List.of(
            new Word("Red", "Vermelho"),
            new Word("Blue", "Azul"),
            new Word("Green", "Verde"),
            new Word("Yellow", "Amarelo")
        );

        // Create the hardcoded matching question
        Question matchingQuestion = new Question("matching", matchingWords, "Colors");

        // Display the matching question
        System.out.println("\nLast Question: Match the colors with their translations.");
        matchingQuestion.askQuestion(); // Display matching question text

        // Collect user answers for matching
        List<String> userMatches = new ArrayList<>();
        for (int i = 0; i < matchingQuestion.getOptions().size(); i++) {
            System.out.println((i + 1) + ": " + matchingQuestion.getOptions().get(i));
            userMatches.add(keyboard.next()); // Collect user's matching choices
        }

        // Provide feedback based on the matching question
        matchingQuestion.provideFeedback(matchingQuestion.validateAnswer(userMatches));
    }
}
