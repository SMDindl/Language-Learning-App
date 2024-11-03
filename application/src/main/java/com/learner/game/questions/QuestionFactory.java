package com.learner.game.questions;

import java.util.ArrayList;
import java.util.UUID;

import com.learner.game.innerdata.TextObject;

/**
 * Generates the info needed for question types:
 * - Fill the blank (FITB),
 * - Matching,
 * - Sequencing
 * Based upon info provided by textObject uuid
 */
public class QuestionFactory {

    public static Question createQuestion(QuestionType type, UUID textObjectUUID) {
        
        Question question;

        switch (type) {
            case FITB -> question = new FITBQuestion(textObjectUUID);
            case MATCHING -> question = new MatchingQuestion(textObjectUUID);
            case SEQUENCING -> question = new SequencingQuestion(textObjectUUID);
            default -> throw new IllegalArgumentException("Unsupported question type for creation/generation: " + type);
        }

        question.generateQuestion();

        return question;
    }
}
