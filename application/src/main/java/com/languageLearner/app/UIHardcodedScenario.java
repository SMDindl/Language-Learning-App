package com.languageLearner.app;

import com.languageLearner.data.DataConstants;
import com.languageLearner.data.UserList;
import com.languageLearner.narration.Narrator;

public class UIHardcodedScenario extends DataConstants {
    
    public static void main(String[] args) {
        Narrator.playSoundRussell("");

        // Load users and game data
        LanguageLearningApplication app = LanguageLearningApplication.getInstance();
        app.load();

        // Tim's scenario
        hardcodedTimScenario(app);
        
        // Tammy's scenario
        hardcodedTammyScenario(app);
    }

    private static void hardcodedTimScenario(LanguageLearningApplication app) {
        System.out.println("=== Tim Tomacka's Scenario ===");

        // Check initial users
        System.out.println("Checking initial users:");
        UserList userList = UserList.getInstance();
        System.out.println(userList.getUsers());

        // Tim attempts to create an account with an existing username
        String emailTim = "ttomacka@email.com";
        String usernameTim = "ttomacka"; // Existing username from Tammy
        String displayNameTim = "Tim";
        String passwordTim = "Password123";

        System.out.println("Tim attempts to create an account with username: " + usernameTim);
        boolean signupAttempt1 = app.signup(emailTim, usernameTim, displayNameTim, passwordTim);
        System.out.println(signupAttempt1 ? "Signup successful!" : "Signup failed - username already exists.");

        // Tim changes username and successfully creates account
        usernameTim = "ttom"; // New username
        System.out.println("Tim changes his username to: " + usernameTim);
        boolean signupAttempt2 = app.signup(emailTim, usernameTim, displayNameTim, passwordTim);
        System.out.println(signupAttempt2 ? "Signup successful!" : "Signup failed.");

        // Check users.json
        System.out.println("Users after Tim's signup: " + userList.getUsers());

        // Tim logs out
        app.logout();

        // Tim successfully logs in
        System.out.println("Tim attempts to login:");
        boolean loginSuccess = app.signin(emailTim, passwordTim);
        System.out.println(loginSuccess ? "Login successful! Welcome, " + displayNameTim : "Login failed.");
        
        // Proceeding through the course
        System.out.println("Tim is now going through the course...");
        app.setCurrentLanguage("filipino");
        app.viewProgress(); // Check progress

        // Simulate answering questions (mocking)
        System.out.println("Tim answers questions... (mocking interaction)");
        // In real implementation, questions would be presented, and answers would be processed.

        // Check progress screen
        app.viewProgress();
        
        // Log out
        app.logout();
    }

    private static void hardcodedTammyScenario(LanguageLearningApplication app) {
        System.out.println("=== Tammy Tomacka's Scenario ===");
        
        // Show Tammy's initial entry in users.json
        System.out.println("Tammy's initial entry in users.json:");
        UserList userList = UserList.getInstance();
        System.out.println(userList.getUsers());

        // Tammy logs in
        String emailTammy = "ttammy@email.com";
        String passwordTammy = "TammyPassword1";
        System.out.println("Tammy logs in with email: " + emailTammy);
        boolean loginSuccess = app.signin(emailTammy, passwordTammy);
        System.out.println(loginSuccess ? "Login successful! Welcome, Tammy!" : "Login failed.");

        // Tammy checks progress
        app.viewProgress();

        // Tammy chooses to review work she's struggling with
        System.out.println("Tammy chooses to review her struggling words and phrases...");
        // Implement review logic here (mocking it)
        
        // Log out
        app.logout();
        System.out.println("Tammy has logged out.");
    }
}
