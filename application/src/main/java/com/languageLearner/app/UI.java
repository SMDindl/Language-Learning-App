package com.languageLearner.app;

import java.util.HashMap;
import java.util.Scanner;

import com.languageLearner.data.DataConstants;
import com.languageLearner.data.DataKey;
import com.languageLearner.data.GameData;
import com.languageLearner.data.User;
import com.languageLearner.data.UserList;
import com.languageLearner.narration.Narrator;

public class UI extends DataConstants {

    private static final String LANGUAGE_FILIPINO = "filipino";
    private static final LanguageLearningApplication app = LanguageLearningApplication.getInstance();

    public static void main(String[] args) {
        Narrator.playSoundRussell("");

        // Load users and data, and attempt hardcoded login for testing
        app.load();
        hardcodedLogin();
        playLoop();
    }

    // Login flow
    private static void loginFlow() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to the Hello Worlders' Filipino learning app!");

            System.out.println("1. Login\n2. Sign up\n3. Exit");
            System.out.print("Select an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    if (app.signin(email, password)) {
                        System.out.println("Login successful! Welcome, " + app.getCurrentUser().getDisplayName());
                        playLoop();
                    } else {
                        System.out.println("Login failed. Please try again.");
                    }
                    break;
                case "2":
                    System.out.println("Signup: ");
                    System.out.print("Enter your Email: ");
                    String email2 = scanner.nextLine();
                    System.out.print("Enter your Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter your Display Name: ");
                    String displayName = scanner.nextLine();
                    System.out.print("Enter your Password: ");
                    String password2 = scanner.nextLine();
                    
                    boolean signupSuccess = app.signup(email2, username, displayName, password2);
                    if (signupSuccess) {
                        System.out.println("Signup successful! Welcome, " + displayName + "!");
                    } else {
                        System.out.println("Signup failed. Please try again.");
                    }
                    break;
                case "3":
                    app.exit();
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }
    }

    // Hardcoded login for testing
    private static void hardcodedLogin() { 
        User current = UserList.getInstance().login("sdindl@email.sc.edu", "Password2024");
        if (current != null) {
            app.setCurrentUser(current); 
            System.out.println("\nLogin successful! Welcome, " + current.getDisplayName());
            playLoop();
        } else {
            System.out.println("\nHardcoded login failed.");
        }
    }

    // Main application loop after login
    private static void playLoop() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nWelcome to the Hello Worlders' Filipino learning app.");
        
        while (app.isLoggedIn()) {
            System.out.println("\n1. Play Games\n2. View Progress\n3. Review Missed Questions\n4. Logout\n5. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    app.setCurrentLanguage("filipino");
                    selectDifficulty();
                    break;
                case "2":
                    app.viewProgress();
                    break;
                case "3":
                    app.reviewMissedQuestions();
                    break;
                case "4":
                    app.logout();
                    System.out.println("You have been logged out.\n");
                    loginFlow();
                    return;
                case "5":
                    app.exit();
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }
    }

    // Method to handle difficulty selection with a back option
    private static void selectDifficulty() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nSelect a difficulty:\n1. Easy\n2. Medium\n3. Hard\n4. Back to Main Menu");

        switch (scanner.nextLine()) {
            case "1":
                gamesLoop(DataConstants.EASY);
                break;
            case "2":
                gamesLoop(DataConstants.MEDIUM);
                break;
            case "3":
                gamesLoop(DataConstants.HARD);
                break;
            case "4":
                return; // Go back to main menu without logging out
            default:
                System.out.println("\nInvalid selection.");
                selectDifficulty(); // Retry difficulty selection if invalid input
        }
    }

    // Game selection loop with a back option
    private static void gamesLoop(String difficulty) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            GameData gameData = GameData.getInstance();
            String[] gameTypes = {DataConstants.ALPHABET_GAME, DataConstants.COLORS_GAME, DataConstants.NUMBERS_GAME, DataConstants.STORIES_GAME};
            boolean gamesAvailable = false;
            int gameIndex = 1;

            System.out.println("\nSelect a game:");
            HashMap<Integer, String> gameMapping = new HashMap<>();

            for (String gameType : gameTypes) {
                DataKey dataKey = DataKey.getInstance(LANGUAGE_FILIPINO, gameType, difficulty);
                if (gameData.getWords(dataKey) != null || gameData.getQuestions(dataKey) != null ||
                    gameData.getStories(dataKey) != null || gameData.getLetters(dataKey) != null) {
                    System.out.println(gameIndex + ". " + gameType);
                    gameMapping.put(gameIndex, gameType);
                    gameIndex++;
                    gamesAvailable = true;
                }
            }

            if (!gamesAvailable) {
                System.out.println("\nNo games available for this difficulty.");
                return; // Go back to difficulty selection
            }

            System.out.print("\nEnter game number, " + gameIndex + " to go back, or " + (gameIndex + 1) + " to exit: ");
            int selection = scanner.nextInt();

            if (selection >= 1 && selection < gameIndex) {
                String selectedGameType = gameMapping.get(selection);
                System.out.println("\nPlaying " + selectedGameType + " in " + difficulty + " mode");
                app.startGame(DataKey.getInstance(LANGUAGE_FILIPINO, selectedGameType, difficulty));
                postGameOptions(selectedGameType, difficulty);
            } else if (selection == gameIndex) {
                return; // Go back to selectDifficulty
            } else if (selection == gameIndex + 1) {
                app.exit();
            } else {
                System.out.println("\nInvalid selection. Please choose a valid option.");
            }
        }
    }

    // Post-game options: Play Again, Choose Another Game, or Exit
    private static void postGameOptions(String gameType, String difficulty) {
        Scanner scanner = new Scanner(System.in);
        boolean continueGame = true;

        while (continueGame) {
            System.out.println("\nWhat would you like to do?\n1. Play Again\n2. Choose Another Game\n3. Exit");
            System.out.print("\nEnter 1, 2, or 3: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.println("\nPlaying " + gameType + " again on " + difficulty + " difficulty.");
                    app.startGame(DataKey.getInstance(LANGUAGE_FILIPINO, gameType, difficulty));
                    break;
                case "2":
                    return; // Go back to gamesLoop to select another game
                case "3":
                    app.exit();
                default:
                    System.out.println("\nInvalid selection. Please choose a valid option.");
            }
        }
    }
}
