package com.languageLearner.app;

import com.languageLearner.data.DataKey;
import com.languageLearner.data.GameData;
import com.languageLearner.data.Question;
import com.languageLearner.data.Word;

import java.util.ArrayList;
import java.util.Scanner;

public class ColorsGameTest {
    
    public static void main(String[] args) {
        ColorsGameTest tester = new ColorsGameTest();
        tester.runTest();
    }

    public void runTest() {
        // Assuming difficulties are "easy", "medium", "hard"
        String[] difficulties = {"easy", "medium", "hard"};
        
        // Loop through each difficulty level and start the ColorsGame for each one
        for (String difficulty : difficulties) {
            System.out.println("\n--- Testing ColorsGame for difficulty: " + difficulty + " ---");
            
            // Set the difficulty level in DataKey
            DataKey dataKey = DataKey.getInstance();
            dataKey.setDifficulty(difficulty);
            dataKey.setGameType("colorsGame");
            dataKey.setLanguage("filipino");  // Assuming language is Filipino

            // Initialize and start ColorsGame
            ColorsGame colorsGame = new ColorsGame();
            colorsGame.startGame();
            
            // Retrieve questions from GameData for the specified difficulty and validate each question
            ArrayList<Question> questionList = GameData.getInstance().getQuestions(dataKey);
            if (questionList == null || questionList.isEmpty()) {
                System.out.println("No questions found for difficulty: " + difficulty);
                continue;
            }

            for (Question question : questionList) {
                // Display question and simulate answering
                System.out.println("\nQuestion: " + question.getText());
                
                if (question.getType().equals("multiple_choice")) {
                    // Simulate selecting the first option for multiple-choice questions
                    int userAnswer = 0; // Choosing the first option
                    boolean isCorrect = question.validateAnswer(userAnswer);
                    question.provideFeedback(isCorrect);
                } else if (question.getType().equals("true_false")) {
                    // Simulate answering "true" for true/false questions
                    boolean userAnswer = true;
                    boolean isCorrect = question.validateAnswer(userAnswer);
                    question.provideFeedback(isCorrect);
                } else if (question.getType().equals("FITB")) {
                    // Simulate answering with the correct answer for fill-in-the-blank
                    String userAnswer = question.getCorrectAnswer();
                    boolean isCorrect = question.validateAnswer(userAnswer);
                    question.provideFeedback(isCorrect);
                } else if (question.getType().equals("matching")) {
                    // Simulate matching answer
                    String userAnswer = question.getCorrectAnswer();
                    boolean isCorrect = question.validateAnswer(userAnswer);
                    question.provideFeedback(isCorrect);
                }
            }
        }
        
        System.out.println("\n--- ColorsGame Test Completed ---");
    }
}
