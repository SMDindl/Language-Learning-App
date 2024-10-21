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
        this.dataKey = DataKey.getInstance();
    }

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

    public Word pickNumber() {
        System.out.println("\nWhich number from 0-9 would you like to learn?: \n");
        ArrayList<Word> numbersList = gameData.getWords(dataKey);
        //User will be prompted to select a number
        Word selection = numbersList.get(0);
        return selection;
    }

    public void teachNumber(Word number) {
        System.out.println(number.getWordText());
        System.out.println(number.getWordTranslation());
    }

    public void askQuestion() {
        Scanner keyboard = new Scanner(System.in);
        ArrayList<Question> questionList = gameData.getQuestions(dataKey);
        for (int i = 0; i < questionList.size(); i++) {
            System.out.println(questionList.get(i).displayQuestion());
            provideFeedback(validateAnswer(keyboard.nextLine(), questionList.get(i)));
        }
    }

    public boolean validateAnswer(String answer, Question question) {
        return answer.equals(question.getCorrectAnswer());
    }

    public void provideFeedback(boolean isCorrect) {
        if (isCorrect)
            System.out.println("Well done!");
        else
            System.out.println("Better luck next time");
    }
}