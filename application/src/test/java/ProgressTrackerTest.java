package com.languageLearner.data;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProgressTrackerTest {
    private ProgressTracker tracker;

    @BeforeEach
    public void setUp() {
        tracker = new ProgressTracker("Filipino");
    }

    @Test
    public void testGetLanguage_ShouldReturnInitialLanguage() {
        assertEquals("Filipino", tracker.getLanguage(), "Language should be initialized to Filipino.");
    }

    @Test
    public void testSetLanguage_ShouldUpdateLanguage() {
        tracker.setLanguage("Spanish");
        assertEquals("Spanish", tracker.getLanguage(), "Language should be updated to Spanish.");
    }

    @Test
    public void testAddCompletedGame_ShouldAddGame_WhenGameNotPreviouslyAdded() {
        DataKey game = new DataKey("game1");
        tracker.addCompletedGame(game);
        assertTrue(tracker.getCompletedGames().contains(game), "Game should be added to completed games.");
    }

    @Test
    public void testAddCompletedGame_ShouldNotAddGame_WhenGameAlreadyAdded() {
        DataKey game = new DataKey("game1");
        tracker.addCompletedGame(game);
        tracker.addCompletedGame(game); // Adding again
        assertEquals(1, tracker.getCompletedGames().size(), "Game should only be added once.");
    }

    @Test
    public void testAddMissedQuestion_ShouldAddQuestion_WhenQuestionNotPreviouslyAdded() {
        Question question = new Question("Ano ang kapital ng Pilipinas?", "Maynila");
        tracker.addMissedQuestion(question);
        assertTrue(tracker.getMissedQuestions().contains(question), "Question should be added to missed questions.");
    }

    @Test
    public void testAddMissedQuestion_ShouldNotAddQuestion_WhenQuestionAlreadyAdded() {
        Question question = new Question("Ano ang kapital ng Pilipinas?", "Maynila");
        tracker.addMissedQuestion(question);
        tracker.addMissedQuestion(question); // Adding again
        assertEquals(1, tracker.getMissedQuestions().size(), "Question should only be added once.");
    }

    @Test
    public void testRemoveMissedQuestion_ShouldRemoveQuestion_WhenQuestionExists() {
        Question question = new Question("Ano ang kapital ng Pilipinas?", "Maynila");
        tracker.addMissedQuestion(question);
        tracker.removeMissedQuestion(question);
        assertFalse(tracker.getMissedQuestions().contains(question), "Question should be removed from missed questions.");
    }

    @Test
    public void testRemoveMissedQuestion_ShouldNotRemoveQuestion_WhenQuestionDoesNotExist() {
        Question question1 = new Question("Ano ang kapital ng Pilipinas?", "Maynila");
        Question question2 = new Question("Ano ang kapital ng Alemanya?", "Berlin");
        tracker.addMissedQuestion(question1);
        tracker.removeMissedQuestion(question2); // Attempting to remove a question that wasn't added
        assertEquals(1, tracker.getMissedQuestions().size(), "The size of missed questions should remain the same.");
    }
    
    @Test
    public void testAddCompletedGame_ShouldHandleNullGame() {
        tracker.addCompletedGame(null);
        assertEquals(0, tracker.getCompletedGames().size(), "Adding null should not change completed games.");
    }

    @Test
    public void testAddMissedQuestion_ShouldHandleNullQuestion() {
        tracker.addMissedQuestion(null);
        assertEquals(0, tracker.getMissedQuestions().size(), "Adding null should not change missed questions.");
    }
}
