package com.learner.game.questions;

import java.util.ArrayList;
import java.util.UUID;

import com.learner.game.innerdata.TextObject;

public class SequencingQuestion extends Question {
    
    private ArrayList<String> sequence;

    public SequencingQuestion(UUID uuid, UUID gameUUID, UUID languageUUID, String questionText) {
        super(uuid, gameUUID, languageUUID, questionText, QuestionType.SEQUENCING);
        this.sequence = new ArrayList<>();
    }

    public SequencingQuestion(UUID uuid) {

        super(uuid, gameUUID, languageUUID, questionText, QuestionType.SEQUENCING);
        this.sequence = new ArrayList<>();
    }

    @Override
    public void generateQuestion(ArrayList<TextObject> textObjects) {
        for (TextObject textObject : textObjects) {
            sequence.add(textObject.getText());
        }
        this.questionText = "Arrange the following in the correct order.";
    }

    @Override
    public boolean validateAnswer(String userAnswer) {
        String[] userSequence = userAnswer.split(", ");
        if (userSequence.length != sequence.size()) {
            return false;
        }
        for (int i = 0; i < sequence.size(); i++) {
            if (!sequence.get(i).equalsIgnoreCase(userSequence[i].trim())) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<String> getSequence() {
        return sequence;
    }
}
