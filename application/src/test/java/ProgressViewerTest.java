package com.languageLearner.app;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.languageLearner.data.ProgressTracker;
import com.languageLearner.data.Question;
import com.languageLearner.data.User;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class ProgressViewerTest {

    private User user;
    private ProgressTracker tracker;
    private final PrintStream originalOut = System.out; // Store the original output stream
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        // Setup a user and their progress tracker
        user = new User("testUser", "Test User");
        tracker = new ProgressTracker("English");
        user.addProgressTracker(tracker); // Assume User has a method to add tracker

        // Set up output stream capture
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testDisplayProgressWithNoProgressTracker() {
        // Create a user without a progress tracker
        User userWithoutTracker = new User("noTrackerUser", "No Tracker User");
        ProgressViewer.displayProgress(userWithoutTracker, "Spanish");

        // Verify output
        String output = outputStream.toString();
        assertTrue(output.contains("No progress tracker available for language: Spanish"));
    }

    @Test
    void testDisplayProgressWithProgressTracker() {
        // Add completed games and missed questions
        tracker.addCompletedGame(new DataKey("game1"));
        tracker.addMissedQuestion(new Question("What is the capital of France?", "Paris"));

        // Call the display method
        ProgressViewer.displayProgress(user, "English");
        String output = outputStream.toString();

        // Verify expected output
        assertTrue(output.contains("Completed games: 1"));
        assertTrue(output.contains("Missed questions: 1"));
    }

    @Test
    void testReviewMissedQuestionsWithNoMissedQuestions() {
        ProgressViewer.reviewMissedQuestions(user, "English");
        
        // Capture output
        String output = outputStream.toString();
        assertTrue(output.contains("No missed questions to review for language: English"));
    }

    @Test
    void testReviewMissedQuestionsWithMissedQuestions() {
        Question missedQuestion = new Question("What is the capital of France?", "Paris");
        tracker.addMissedQuestion(missedQuestion);
        
        // Simulate user reviewing missed questions
        ProgressViewer.reviewMissedQuestions(user, "English");
        String output = outputStream.toString();

        // Verify the question was reviewed and removed
        assertFalse(tracker.getMissedQuestions().contains(missedQuestion));
        assertTrue(output.contains("You reviewed the missed question: What is the capital of France?"));
    }

    @AfterEach
    void tearDown() {
        // Restore the original System.out
        System.setOut(originalOut);
    }
}
