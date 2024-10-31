package com.learner.game.questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class MultipleChoiceQuestion extends Question {

    private final ArrayList<String> options;
    private int correctAnswerIndex;

    public MultipleChoiceQuestion(UUID uuid, UUID gameUUID, String text, ArrayList<String> options) {
        super(uuid, gameUUID, text);
        this.options = options;
        this.correctAnswerIndex = 0;  // Since the correct answer is initially at position 0
        this.questionType = QuestionType.MULTIPLE_CHOICE;
        shuffleOptions();
    }

    // Shuffle options while keeping track of the correct answer index
    private void shuffleOptions() {
        Collections.shuffle(options);
        correctAnswerIndex = options.indexOf(options.get(0));  // Update index after shuffling
    }

    @Override
    public boolean validateAnswer(Object userAnswer) {
        return (userAnswer instanceof Integer) && (correctAnswerIndex == (Integer) userAnswer);
    }

    @Override
    public void askQuestion() {
        super.askQuestion();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ": " + options.get(i));
        }
    }

    public ArrayList<String> getOptions() { return options; }
    public int getCorrectAnswerIndex() { return correctAnswerIndex; }
}
