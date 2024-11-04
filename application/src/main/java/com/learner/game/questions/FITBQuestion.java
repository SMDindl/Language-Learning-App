package com.learner.game.questions;

import java.util.UUID;

import com.learner.game.innerdata.TextObject;

public class FITBQuestion extends Question {
    
    private String answer;

    public FITBQuestion(UUID uuid) {
        super(uuid, QuestionType.FITB);
    }

    @Override
    public void generateQuestion() {
        TextObject textObject = gameManager.findTextObjectByUUID(this.getUUID()); // Retrieve the TextObject using the uuid

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
