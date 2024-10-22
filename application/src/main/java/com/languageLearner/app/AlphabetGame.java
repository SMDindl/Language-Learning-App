package com.languageLearner.app;

import java.util.ArrayList;
import java.util.Scanner;

import com.languageLearner.data.DataKey;
import com.languageLearner.data.GameData;
import com.languageLearner.data.Letter;
import com.languageLearner.data.Question;

public class AlphabetGame {
    private GameData gameData;
    private DataKey dataKey;

    public AlphabetGame(DataKey dataKey) {
        this.gameData = GameData.getInstance();
        this.dataKey = dataKey;
    }

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

    public void teachLetter(Letter letter) {
        System.out.println(letter.getPronunciation());
        System.out.println(letter.getExampleWords());
    }

    public void askQuestion() {
        Scanner keyboard = new Scanner(System.in);
        DataKey dataKey = DataKey.getInstance();
        ArrayList<Question> questionList = gameData.getQuestions(dataKey);
        for(int i = 0; i < questionList.size(); i++) {
            System.out.println(questionList.get(i).displayQuestion());
            provideFeedback(validateAnswer(keyboard.nextLine(), questionList.get(i)));
        }
    }

    public boolean validateAnswer(String answer, Question question) {
        return answer.equals(question.getCorrectAnswer());
     }

    public void provideFeedback(boolean isCorrect) {
        if(isCorrect) 
            System.out.println("Well done!");
        else
            System.out.println("Better luck next time");
    }
}