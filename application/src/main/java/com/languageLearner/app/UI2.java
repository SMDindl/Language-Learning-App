package com.languageLearner.app;

import com.languageLearner.data.DataConstants;
import com.languageLearner.data.DataKey;
import com.languageLearner.data.GameData;
import com.languageLearner.narration.Narrator;

public class UI2 extends DataConstants {

    private static final String LANGUAGE_FILIPINO = "filipino";
    private static final LanguageLearningApplication app = LanguageLearningApplication.getInstance();
    private static final GameData gameData = GameData.getInstance();

    public static void main(String[] args) {
        app.load();  // Load all necessary data

        // Execute Tim's account creation, login, and course progression
        simulateTimAccountCreation();

        // Execute Tim's course progression through hardcoded learning and questions
        simulateTimCourseProgress();

        // Execute Tammy's login, progress review, and study session
        simulateTammyProgressReview();
    }

    // Tim creates an account, logs in, and logs out before proceeding through the course
    private static void simulateTimAccountCreation() {
        System.out.println("\n---- Tim's Account Creation ----");

        // Show Tim is not in users.json, while Tammy is
        System.out.println("Initial users.json (Tammy exists, Tim does not):");
        displayUsersJson();

        // Attempt to create Tim's account with an existing username
        System.out.println("\nTim tries creating an account with username 'ttomacka'.");
        boolean accountCreated = app.signup("tim@example.com", "ttomacka", "Tim Tomacka", "Password2024");
        
        if (!accountCreated) {
            System.out.println("Account creation failed: Username 'ttomacka' already exists.");
        }

        // Successful creation with a new username
        System.out.println("\nTim changes his username to 'ttom'.");
        accountCreated = app.signup("tim@example.com", "ttom", "Tim Tomacka", "Password2024");

        if (accountCreated) {
            System.out.println("Account successfully created.");
            app.logout();
        }

        // Show Tim is now in users.json
        System.out.println("\nUpdated users.json (Tim added):");
        displayUsersJson();

        // Tim logs in successfully
        System.out.println("\nTim logs in.");
        app.signin("tim@example.com", "Password2024");
    }

    // Tim progresses through the course with narration
    private static void simulateTimCourseProgress() {
        System.out.println("\n---- Tim's Course Progression ----");

        Narrator.playSoundRussell("Welcome to the Filipino learning course, Tim!");

        // Selecting and initiating a learning module
        DataKey easyStoryKey = DataKey.getInstance(LANGUAGE_FILIPINO, DataConstants.STORIES_GAME, DataConstants.EASY);
        System.out.println("Tim begins learning with a story.");
        Narrator.playSoundRussell("Let’s read a story.");
        app.startGame(easyStoryKey);

        // Answering a series of questions
        System.out.println("Tim proceeds to answer questions:");
        answerQuestionsForTim();

        // Display progress after the session
        System.out.println("\nDisplaying Tim's progress...");
        displayProgressForCurrentUser();

        // Logging out after session
        System.out.println("Tim logs out.");
        app.logout();
    }

    // Tammy logs in, reviews her progress, and goes through a study session
    private static void simulateTammyProgressReview() {
        System.out.println("\n---- Tammy's Review and Study Session ----");

        // Show Tammy is in users.json
        System.out.println("Displaying Tammy in users.json:");
        displayUsersJson();

        // Tammy logs in
        System.out.println("\nTammy logs in.");
        app.signin("tammy@example.com", "Password123");

        // Tammy checks her progress
        System.out.println("Tammy views her progress:");
        displayProgressForCurrentUser();

        // Tammy prints study sheet for struggling words
        System.out.println("\nTammy reviews her struggling words:");
        Narrator.playSoundRussell("Let's review the words you are struggling with.");
        printStudySheet();

        // Tammy answers questions for struggling words
        System.out.println("\nTammy answers questions based on struggling words:");
        answerStruggleQuestionsForTammy();

        // Display updated progress for Tammy after study session
        System.out.println("\nUpdated users.json (showing Tammy's progress after review):");
        displayUsersJson();

        // Tammy logs out
        System.out.println("Tammy logs out.");
        app.logout();
    }

    // Hardcoded answer flow for Tim’s course questions with narration
    private static void answerQuestionsForTim() {
        // Tim answers 5 different types of questions
        int correctAnswers = 3;
        int incorrectAnswers = 2;
        
        Narrator.playSoundRussell("What is the color of grapes?");
        System.out.println("Tim answers: Lila");

        // Repeat for other question types
        System.out.println("Tim got " + correctAnswers + " correct and " + incorrectAnswers + " incorrect.");
    }

    // Tammy’s session includes reviewing and answering struggling questions
    private static void answerStruggleQuestionsForTammy() {
        int correctAnswers = 8;
        int incorrectAnswers = 2;

        Narrator.playSoundRussell("First struggling word: asul (blue)");
        System.out.println("Tammy answers correctly.");

        System.out.println("Tammy got " + correctAnswers + " correct and " + incorrectAnswers + " incorrect.");
    }

    // Print a study sheet for Tammy’s struggling words
    private static void printStudySheet() {
        System.out.println("Printing Tammy’s study sheet for struggling words...");
        // Code to simulate text file output or formatted console output
    }

    // Display users.json
    private static void displayUsersJson() {
        // Placeholder to simulate outputting current user data from users.json
        // Example: System.out.println(UserList.getInstance().toJson());
    }

    // Display progress for current user
    private static void displayProgressForCurrentUser() {
        // Placeholder to simulate displaying the current user's progress
        // Example: System.out.println(app.getCurrentUser().getProgress());
    }
}
