package com.languageLearner.data;

import java.util.ArrayList;

public class Question {
    // Attributes
    private String questionText;
    private int correctAnswerIndex;
    private ArrayList<String> answers;
    private String image;

    // Constructor
    public Question(String questionText, int correctAnswerIndex, ArrayList<String> answers) {
        this.questionText = questionText;
        this.correctAnswerIndex = correctAnswerIndex;
        this.answers = answers;
    }

    public Question(String questionText, int correctAnswerIndex, ArrayList<String> answers, String image) {
        this.questionText = questionText;
        this.correctAnswerIndex = correctAnswerIndex;
        this.answers = answers;
        this.image = image;
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
}
