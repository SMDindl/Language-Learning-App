package com.learner.game.questions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.learner.game.innerdata.TextObject;
import java.util.UUID;

public class FITBQuestionTest {  // Make the class public
    
    private FITBQuestion fitbQuestion;
    private UUID questionUUID;
    private TextObject textObject;

    @BeforeEach
    void setUp() {
        questionUUID = UUID.randomUUID();
        fitbQuestion = new FITBQuestion(questionUUID);
        
        // Set up the TextObject mock with linked text and the actual text
        textObject = new TextObject("What is the capital of France?", "Paris");
        // Here, you might set up how the game manager will retrieve this textObject
    }

    @Test
    void testGenerateQuestionCreatesCorrectQuestionText() {
        fitbQuestion.generateQuestion();
        
        String expectedQuestionText = "What is the capital of France? _____";
        assertEquals(expectedQuestionText, fitbQuestion.getQuestionText());
        assertEquals("Paris", fitbQuestion.getAnswer());
    }

    @Test
    void testValidateAnswerReturnsTrueForCorrectAnswer() {
        fitbQuestion.generateQuestion(); // Ensure the question is generated first
        assertTrue(fitbQuestion.validateAnswer("Paris"), 
            "Expected validateAnswer to return true for the correct answer.");
    }

    @Test
    void testValidateAnswerReturnsFalseForIncorrectAnswer() {
        fitbQuestion.generateQuestion(); // Ensure the question is generated first
        assertFalse(fitbQuestion.validateAnswer("Berlin"), 
            "Expected validateAnswer to return false for an incorrect answer.");
    }

    @Test
    void testValidateAnswerHandlesNullAnswerGracefully() {
        fitbQuestion.generateQuestion(); // Ensure the question is generated first
        assertFalse(fitbQuestion.validateAnswer(null), 
            "Expected validateAnswer to return false for null input.");
    }

    @Test
    void testValidateAnswerIgnoresCaseForCorrectAnswer() {
        fitbQuestion.generateQuestion(); // Ensure the question is generated first
        assertTrue(fitbQuestion.validateAnswer("PARIS"), 
            "Expected validateAnswer to return true regardless of case for the correct answer.");
    }

    @Test
    void testGetAnswerReturnsCorrectAnswer() {
        fitbQuestion.generateQuestion(); // Ensure the question is generated first
        assertEquals("Paris", fitbQuestion.getAnswer(), 
            "Expected getAnswer to return the correct answer.");
    }
}
