package com.languageLearner.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProgressTrackerTest {

    private ProgressTracker progressTracker;

    @BeforeEach
    public void setUp() {
        progressTracker = new ProgressTracker();
        // Initialize your progress tracker with any necessary data
    }

    @Test
    public void testTrackProgress() {
        // Example: Add some data to your progress tracker
        progressTracker.trackProgress("someCriteria");
        assertTrue(progressTracker.isProgressTracked("someCriteria"));
    }

    @Test
    public void testGetProgress() {
        // Example: Get progress and assert the result
        int progress = progressTracker.getProgress("someCriteria");
        assertEquals(expectedValue, progress);
    }

    // Add more tests as needed...
}
