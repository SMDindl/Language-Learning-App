package com.learner.game.questions;

import java.util.UUID;

public class FITBQuestion extends Question {
    
    private final String correctAnswer;

    public FITBQuestion(UUID uuid, UUID gameUUID, String text, String correctAnswer) {
        super(uuid, gameUUID, text);
        this.correctAnswer = correctAnswer;
        this.questionType = QuestionType.FITB;
    }

    @Override
    public boolean validateAnswer(Object userAnswer) {
        return correctAnswer.equalsIgnoreCase(userAnswer.toString());
    }

    public String getCorrectAnswer() { return correctAnswer; }
}
