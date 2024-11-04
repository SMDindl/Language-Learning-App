package com.learner.game.questions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.UUID;

public class MultipleChoiceQuestionTest {
    
    private MultipleChoiceQuestion mcQuestion;
    private UUID questionUUID;

    @BeforeEach
    void setUp() {
        questionUUID = UUID.randomUUID();
        String[] options = {"A", "B", "C", "D"};
        mcQuestion = new MultipleChoiceQuestion(questionUUID, "What is 2 + 2?", options, "C");
    }

    @Test
    void testValidateAnswerReturnsTrueForCorrectAnswer() {
        assertTrue(mcQuestion.validateAnswer("C"), 
            "Expected validateAnswer to return true for the correct answer.");
    }

    @Test
    void testValidateAnswerReturnsFalseForIncorrectAnswer() {
        assertFalse(mcQuestion.validateAnswer("A"), 
            "Expected validateAnswer to return false for an incorrect answer.");
    }

    @Test
    void testValidateAnswerHandlesNullAnswerGracefully() {
        assertFalse(mcQuestion.validateAnswer(null), 
            "Expected validateAnswer to return false for null input.");
    }

    @Test
    void testGetOptionsReturnsCorrectOptions() {
        assertArrayEquals(new String[]{"A", "B", "C", "D"}, mcQuestion.getOptions(), 
            "Expected getOptions to return the correct array of options.");
    }
}
