package com.languageLearner.questions;

import com.learner.model.innerdata.TextObject;
import com.learner.model.questions.FITBQuestion;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class FITBQuestionTests {
    
    private FITBQuestion question;
    private TextObject textObject;

    @Before
    public void setUp() {
        // Setup a sample FITBQuestion with a mock TextObject
        String linkedText = "The quick brown fox jumps over the lazy dog.";
        String textToReplace = "fox"; // This is the answer
        UUID textObjectUUID = UUID.randomUUID(); // Unique UUID for the text object
        
        // Create a TextObject that FITBQuestion will use
        textObject = new TextObject(textObjectUUID, linkedText, textToReplace);
        
        // Create an instance of FITBQuestion
        UUID questionUUID = UUID.randomUUID();  // Unique UUID for the question
        question = new FITBQuestion(questionUUID);
        
        // Assume a method to add the TextObject to the gameManager or context (in your real setup)
        // gameManager.addTextObject(textObject);  // This would be necessary to link it
    }

    @Test
    public void testGenerateQuestion() {
        // Call the generateQuestion() method to replace "fox" with "_____"
        question.generateQuestion();

        // Check that the question text has been generated correctly
        assertEquals("The quick brown _____ jumps over the lazy dog.", question.getQuestionText());

        // Check that the answer is set correctly
        assertEquals("fox", question.getAnswer());
    }

    @Test
    public void testValidateCorrectAnswer() {
        // Generate the question first
        question.generateQuestion();

        // Validate the correct answer
        assertTrue("The answer should be correct.", question.validateAnswer("fox"));
    }

    @Test
    public void testValidateIncorrectAnswer() {
        // Generate the question first
        question.generateQuestion();

        // Validate an incorrect answer
        assertFalse("The answer should be incorrect.", question.validateAnswer("dog"));
    }

    @Test
    public void testValidateAnswerWithSpaces() {
        // Generate the question first
        question.generateQuestion();

        // Validate the correct answer with spaces around it
        assertTrue("The answer with spaces should be accepted.", question.validateAnswer("  fox  "));
    }

    @Test
    public void testValidateNullAnswer() {
        // Generate the question first
        question.generateQuestion();

        // Validate a null answer
        assertFalse("The answer should not be valid for null input.", question.validateAnswer(null));
    }
}
