package com.learner.game.questions;

import java.util.ArrayList;
import java.util.UUID;

import com.learner.game.innerdata.TextObject;

public class QuestionFactory {

    public static Question createQuestion(QuestionType type, ArrayList<TextObject> textObjects, UUID gameUUID, UUID languageUUID) {
        
        UUID uuid = textObjects.get(0).getUUID();
        String questionText = ""; // Default text, will be generated in each specific type
        Question question;

        switch (type) {
            case FITB -> question = new FITBQuestion(uuid, gameUUID, languageUUID, questionText);
            case MATCHING -> question = new MatchingQuestion(uuid, gameUUID, languageUUID, questionText);
            case SEQUENCING -> question = new SequencingQuestion(textObjects.get(0).getUUID(), gameUUID, languageUUID, questionText);
            default -> throw new IllegalArgumentException("Unsupported question type: " + type);
        }

        question.generateQuestion(textObjects);
        return question;
    }
}
