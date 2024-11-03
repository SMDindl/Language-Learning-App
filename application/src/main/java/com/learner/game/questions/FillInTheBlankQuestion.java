package com.learner.game.questions;

import java.util.ArrayList;
import java.util.UUID;

import com.learner.game.innerdata.TextObject;

public class FillInTheBlankQuestion extends Question {
    
    private String answer;

    public FillInTheBlankQuestion(UUID uuid, UUID gameUUID, UUID languageUUID, String questionText) {
        super(uuid, gameUUID, languageUUID, questionText, QuestionType.FITB);
    }

    @Override
    public void generateQuestion(ArrayList<TextObject> textObjects) {
        TextObject textObject = textObjects.get(0);
        this.questionText = textObject.getLinkedText().replace(textObject.getText(), "_____");
        this.answer = textObject.getText();
    }

    @Override
    public boolean validateAnswer(String userAnswer) {
        return userAnswer != null && userAnswer.trim().equalsIgnoreCase(answer);
    }

    public String getAnswer() {
        return answer;
    }
}
