package com.languageLearner.app;

import java.util.ArrayList;
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
        this.dataKey = DataKey.getInstance();;
    }

    public void startGame() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("What would you like to do?: \n1. Take a colors quiz \n2. Return to game selection");
        int option = keyboard.nextInt();
        if (option != 1) {
            System.out.println("Returning to game selection...");
            return;
        }

        //User picks the color to work on
        // Word selection = pickColor();
        teachColor();
        System.out.println("When you're ready for the quiz, hit the ENTER key.");
        //keyboard.next();

        askQuestion();
    }

    // public Word pickColor() {
    //     Scanner keyboard = new Scanner(System.in);
    //     System.out.println("\nWhich letter would you like to study?: \n");
    //     ArrayList<Word> colorList = gameData.getWords(dataKey);
    //     for(int i = 0; i < colorList.size(); i++) {
    //         //Prints out all the colors
    //         System.out.println((i+1) + ". " + colorList.get(i).getWordText());
    //     }
    //     Word selection = colorList.get(keyboard.nextInt() - 1);
    //     return selection;
    // }

    public void teachColor() {
        ArrayList<Word> newColors = gameData.getWords(dataKey);
        for(int i = 0; i < newColors.size(); i++) {
            //Might be better to store it as a different list, one that has the word and then the information being taught
            //For now, just storing it as a list of Word to print out the word and then the translation

            // System.out.println(newColors.get(i).getWordText());
            // System.out.println(newColors.get(i).getWordTranslation());
            Word color = newColors.get(i);
            System.out.println(color.getWordText() + " = " + color.getWordTranslation());
            // Narrator.playSoundMiguel(color.getWordText());
            // Narrator.playSoundRussell("equals " + color.getWordTranslation());
        }

        
    }

    public void askQuestion() {
        Scanner keyboard = new Scanner(System.in);
        ArrayList<Question> questionList = gameData.getQuestions(dataKey);
        for(int i = 0; i < questionList.size(); i++) {
            System.out.println(questionList.get(i).displayQuestion());
            Narrator.playSoundMiguel(questionList.get(i).getQuestionText());
            int num = keyboard.nextInt() - 1;
            provideFeedback(validateAnswer(num, questionList.get(i)));
        }
    }

    public boolean validateAnswer(int answer, Question question) {
        System.out.println("anw" + answer + "       " + question.getCorrectAnswerIndex());
        return answer == (question.getCorrectAnswerIndex());
     }

    public void provideFeedback(boolean isCorrect) {
        if(isCorrect) 
            System.out.println("Well done!");
        else
            System.out.println("Better luck next time");
    }
}