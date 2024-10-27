package com.languageLearner.data;

import java.util.ArrayList;
import java.util.UUID;

public class Question {
    // Attributes
    private String questionText;
    private int correctAnswerIndex;
    private ArrayList<String> answers;
    private String image;
    private UUID id;

    // Constructor (w/ image)
    public Question(String questionText, ArrayList<String> answers, int correctAnswerIndex, String image) {
        this.questionText = questionText;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
        this.image = image;
    }

    // Constructor without image
    public Question(String questionText, ArrayList<String> answers, int correctAnswerIndex) {
        this.questionText = questionText;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
        this.image = null; // No image provided
    }

    // Methods
    public String getQuestionText() {
        return questionText;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public String getCorrectAnswer() {
        if (correctAnswerIndex >= 0 && correctAnswerIndex < answers.size()) {
            return answers.get(correctAnswerIndex);
        } else {
            return "Invalid answer index";
        }
    }

    // Additional method to display the question and its answers
    public String displayQuestion() {
        StringBuilder questionDisplay = new StringBuilder("\nQuestion: " + questionText + "\nAnswers:\n");
        for (int i = 0; i < answers.size(); i++) {
            questionDisplay.append(i + 1).append(". ").append(answers.get(i)).append("\n");
        }
        return questionDisplay.toString();
    }

    // public void askQuestion() {
    //     Scanner keyboard = new Scanner(System.in);
    //     ArrayList<Question> questionList = gameData.getQuestions(dataKey);
    //     for(int i = 0; i < questionList.size(); i++) {
    //         System.out.println(questionList.get(i).displayQuestion());
    //         Narrator.playSoundMiguel(questionList.get(i).getQuestionText());
    //         int num = keyboard.nextInt() - 1;
    //         provideFeedback(validateAnswer(num, questionList.get(i)));
    //     }
    // }

    // public boolean validateAnswer(int answer, Question question) {
    //     System.out.println("anw" + answer + "       " + question.getCorrectAnswerIndex());
    //     return answer == (question.getCorrectAnswerIndex());
    //  }

    // public void provideFeedback(boolean isCorrect) {
    //     if(isCorrect) 
    //         System.out.println("Well done!");
    //     else
    //         System.out.println("Better luck next time");
    // }
}
