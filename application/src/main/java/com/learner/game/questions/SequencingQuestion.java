package com.learner.game.questions;

import java.util.ArrayList;
import java.util.UUID;

public class SequencingQuestion extends Question {

    private final ArrayList<String> correctSequence;

    public SequencingQuestion(UUID uuid, UUID gameUUID, String text, ArrayList<String> correctSequence) {
        super(uuid, gameUUID, text);
        this.correctSequence = new ArrayList<>(correctSequence); // Correct sequence stored in order
        this.questionType = QuestionType.SEQUENCING;
    }

    @Override
    public boolean validateAnswer(Object userAnswer) {
        if (userAnswer instanceof ArrayList<?>) {
            ArrayList<?> userSequence = (ArrayList<?>) userAnswer;
            return userSequence.equals(correctSequence);
        }
        return false;
    }

    public ArrayList<String> getCorrectSequence() { return correctSequence; }
}
