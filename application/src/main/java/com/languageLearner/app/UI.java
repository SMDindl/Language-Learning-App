package com.languageLearner.app;

import java.util.HashMap;
import java.util.Scanner;

import com.languageLearner.data.DataConstants;
import com.languageLearner.data.DataKey;
import com.languageLearner.data.GameData;
import com.languageLearner.data.User;
import com.languageLearner.data.UserList;

public class UI extends DataConstants {

    private static final String LANGUAGE_FILIPINO = "filipino";
    private static final LanguageLearningApplication app = LanguageLearningApplication.getInstance();

    public static void main(String[] args) {
        app.load();
        hardcodedLogin();
        playLoop();
    }

    private static void hardcodedLogin() { 
        User current = UserList.getInstance().login("sdindl@sc.email.edu", "Password2024");

        if (current != null) {
            System.out.println("\nLogin successful! Welcome, " + current.getDisplayName());
        } else {
            System.out.println("\nLogin failed.");
        }
    }

    private static void playLoop() {
        System.out.println("\nWelcome to the Hello Worlders' " + LANGUAGE_FILIPINO + " learning app.");
        while (true) {
            selectDifficulty();  // Loop through difficulty selection
        }
    }

    // Method to handle difficulty selection
    private static void selectDifficulty() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nSelect a difficulty:\n1. Easy\n2. Medium\n3. Hard\n4. Exit");

        switch (scanner.nextLine()) {
            case "1": gamesLoop(DataConstants.EASY); break;
            case "2": gamesLoop(DataConstants.MEDIUM); break;
            case "3": gamesLoop(DataConstants.HARD); break;
            case "4": System.exit(0);
            default: System.out.println("\nInvalid selection.");
        }
    }

    // Method to handle game selection and ensure flow stays within games
    private static void gamesLoop(String difficulty) {
        while (true) {  // Keep looping within the game selection process
            Scanner scanner = new Scanner(System.in);
            GameData gameData = GameData.getInstance();
            String[] gameTypes = {DataConstants.ALPHABET_GAME, DataConstants.COLORS_GAME, DataConstants.NUMBERS_GAME, DataConstants.STORIES_GAME};
            boolean gamesAvailable = false;
            int gameIndex = 1;  // Start game index at 1

            System.out.println("\nSelect a game:");
            
            // Clear mapping for each game loop
            HashMap<Integer, String> gameMapping = new HashMap<>();

            for (String gameType : gameTypes) {
                DataKey dataKey = DataKey.getInstance(LANGUAGE_FILIPINO, gameType, difficulty);
                if (gameData.getWords(dataKey) != null || gameData.getQuestions(dataKey) != null ||
                    gameData.getStories(dataKey) != null || gameData.getLetters(dataKey) != null) {
                    // Map the index with the actual game type
                    System.out.println(gameIndex + ". " + gameType);
                    gameMapping.put(gameIndex, gameType);
                    gameIndex++;
                    gamesAvailable = true;
                }
            }

            if (!gamesAvailable) {
                System.out.println("\nNo games available for this difficulty.");
                return;  // Go back to difficulty selection
            }

            System.out.print("\nEnter game number, 5 to switch difficulty, or 6 to exit: ");
            int selection = scanner.nextInt();

            if (selection >= 1 && selection < gameIndex) {
                String selectedGameType = gameMapping.get(selection); // Fetch the correct game based on user input
                System.out.println("\nPlaying " + selectedGameType + " in " + difficulty + " mode");
                app.startGame(DataKey.getInstance(LANGUAGE_FILIPINO, selectedGameType, difficulty));
                postGameOptions(selectedGameType, difficulty);  // After game options
            } else if (selection == 5) {
                return;  // Go back to difficulty selection
            } else if (selection == 6) {
                System.exit(0);  // Exit the program
            } else {
                System.out.println("\nInvalid selection. Please choose a valid option.");
            }
        }
    }

    // Post-game options: Play Again, Choose Another Game (stay in game selection), or Exit
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
                    app.startGame(DataKey.getInstance(LANGUAGE_FILIPINO, gameType, difficulty));  // Restart the same game
                    break;
                case "2":
                    System.out.println("\nChoosing another game...");
                    return;  // Return to the gamesLoop for game selection (same difficulty)
                case "3":
                    System.out.println("\nExiting...\n");
                    System.exit(0);  // Exit the program
                default:
                    System.out.println("\nInvalid selection. Please choose a valid option.");
            }
        }
    }
}
