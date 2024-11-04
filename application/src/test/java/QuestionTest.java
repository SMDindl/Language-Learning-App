package com.learner.game.questions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;

public class QuestionTest {
    
    private Question question;
    private UUID questionUUID;

    @BeforeEach
    void setUp() {
        questionUUID = UUID.randomUUID();
        // Assuming you cannot instantiate abstract classes directly, you might need a concrete subclass
        question = new FITBQuestion(questionUUID);  // Using FITBQuestion as an example
    }

    @Test
    void testGetUUIDReturnsCorrectUUID() {
        assertEquals(questionUUID, question.getUUID(), 
            "Expected getUUID to return the correct UUID.");
    }

    // More tests could be added for shared functionalities
}
