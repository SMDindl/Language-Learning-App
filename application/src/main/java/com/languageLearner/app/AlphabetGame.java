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

    public AlphabetGame() {
        this.gameData = GameData.getInstance();
        this.dataKey = DataKey.getInstance();
    }

    public void startGame() {
        System.out.println("What would you like to do?: \n1. Take a letters quiz \n2. Return to game selection");
        int prompt = 1;
        if (prompt == 2) {
            //Implement this to go back to the game selection
        }

        //User picks the letter to work on
        Letter selection = pickLetter();
        teachLetter(selection);
        System.out.println("When you're ready for the quiz, hit the ENTER key.");
        //keyboard.next();

        askQuestion();
    }

    public Letter pickLetter() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("\nWhich letter would you like to study?: \n");
        ArrayList<Letter> letterList = gameData.getLetters(dataKey);
        for(int i = 0; i < letterList.size(); i++) {
            //Prints out all the letters
            System.out.println((i+1) + ". " + letterList.get(i).getLetter());
        }
        Letter selection = letterList.get(keyboard.nextInt() - 1);
        return selection;
    }

    public void teachLetter(Letter letter) {
        letter.getPronunciation();
        letter.getExampleWords();
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