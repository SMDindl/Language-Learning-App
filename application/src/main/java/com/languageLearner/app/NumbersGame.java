package com.languageLearner.app;

import java.util.ArrayList;
import java.util.Scanner;

import com.languageLearner.data.DataKey;
import com.languageLearner.data.GameData;
import com.languageLearner.data.Question;
import com.languageLearner.data.Word;

public class NumbersGame {
    private GameData gameData;

    public NumbersGame() {
        this.gameData = GameData.getInstance();
    }

    public void startGame() {
        // Adding for git commit testing, remove asap - Marshall
    }

    public void teachNumbers(Numbers numbers) {
        ArrayList<Numbers> newNumbers = numbers.getTeachWords();
        for (int i = 0; i < newNumbers.size(); i++) {
            // Might be better to store it as a different list, one that has the word and
            // then the information being taught
            // For now, just storing it as a list of Word to print out the word and then the
            // translation
            System.out.println(newNumbers.get(i).getWordText());
            System.out.println(newNumbers.get(i).getWordTranslation());
        }
    }

    public void askQuestion() {
        Scanner keyboard = new Scanner(System.in);
        DataKey dataKey = DataKey.getInstance();
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