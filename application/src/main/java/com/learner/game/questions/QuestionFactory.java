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
            case FITB:
                question = new FillInTheBlankQuestion(uuid, gameUUID, languageUUID, questionText);
                break;
            case MATCHING:
                question = new MatchingQuestion(uuid, gameUUID, languageUUID, questionText);
                break;
            case SEQUENCING:
                question = new SequencingQuestion(uuid, gameUUID, languageUUID, questionText);
                break;
            default:
                throw new IllegalArgumentException("Unsupported question type: " + type);
        }

        // Generate the question based on textObjects passed
        question.generateQuestion(textObjects);
        return question;
    }
}
