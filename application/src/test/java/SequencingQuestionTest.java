package com.learner.game.questions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;

public class SequencingQuestionTest {
    
    private SequencingQuestion seqQuestion;
    private UUID questionUUID;

    @BeforeEach
    void setUp() {
        questionUUID = UUID.randomUUID();
        String[] correctOrder = {"Step 1", "Step 2", "Step 3"};
        seqQuestion = new SequencingQuestion(questionUUID, correctOrder);
    }

    @Test
    void testValidateAnswerReturnsTrueForCorrectSequence() {
        String[] userAnswer = {"Step 1", "Step 2", "Step 3"};
        assertTrue(seqQuestion.validateAnswer(userAnswer), 
            "Expected validateAnswer to return true for the correct sequence.");
    }

    @Test
    void testValidateAnswerReturnsFalseForIncorrectSequence() {
        String[] userAnswer = {"Step 3", "Step 2", "Step 1"};
        assertFalse(seqQuestion.validateAnswer(userAnswer), 
            "Expected validateAnswer to return false for an incorrect sequence.");
    }

    @Test
    void testGetCorrectOrderReturnsCorrectSequence() {
        assertArrayEquals(new String[]{"Step 1", "Step 2", "Step 3"}, seqQuestion.getCorrectOrder(), 
            "Expected getCorrectOrder to return the correct sequence.");
    }
}
