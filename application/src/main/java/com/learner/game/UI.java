package com.learner.game;

import java.util.Scanner;

import com.learner.game.loadwrite.DataConstants;

public class UI {

    private final Facade facade = Facade.getInstance();
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        facade.loadData(DataConstants.GAME_DATA_FILE, DataConstants.USER_FILE);

        while (true) {
            if (!facade.isUserLoggedIn()) {
                displayMainMenu();
            } else {
                selectLanguage();
                selectDifficulty();
                selectGame();
                navigateContent();
                startQuiz();
                System.out.println(facade.endGameSession());
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Select Language");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> handleLogin();
            case 2 -> handleRegistration();
            case 3 -> selectLanguage();
            case 4 -> exitApplication();
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    private void handleLogin() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.println(facade.loginUser(email, password));
    }

    private void handleRegistration() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter display name: ");
        String displayName = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.println(facade.registerUser(email, username, displayName, password));
    }

    private void selectLanguage() {
        System.out.println("\n--- Select Language ---");
        var languages = facade.getAvailableLanguages();
        if (languages.isEmpty()) {
            System.out.println("No languages available.");
            return;
        }
        for (int i = 0; i < languages.size(); i++) {
            System.out.println((i + 1) + ". " + languages.get(i));
        }
        System.out.print("Choose a language: ");
        int choice = scanner.nextInt() - 1;
        scanner.nextLine();

        if (choice >= 0 && choice < languages.size()) {
            System.out.println(facade.selectLanguage(choice));
        } else {
            System.out.println("Invalid choice. Please try again.");
            selectLanguage(); // Retry if invalid choice
        }
    }

    private void selectDifficulty() {
        System.out.println("\n--- Select Difficulty ---");
        var difficulties = facade.getAvailableDifficulties();
        for (int i = 0; i < difficulties.size(); i++) {
            System.out.println((i + 1) + ". " + difficulties.get(i));
        }
        System.out.print("Choose a difficulty: ");
        int choice = scanner.nextInt() - 1;
        scanner.nextLine();

        if (choice >= 0 && choice < difficulties.size()) {
            Difficulty difficulty = Difficulty.valueOf(difficulties.get(choice));
            System.out.println(facade.selectDifficulty(difficulty));
        } else {
            System.out.println("Invalid choice. Please try again.");
            selectDifficulty(); // Retry if invalid choice
        }
    }

    private void selectGame() {
        System.out.println("\n--- Select Game ---");
        var games = facade.getAvailableGames();
        if (games.isEmpty() || games.contains("Select language and difficulty first.")) {
            System.out.println("Please select a language and difficulty first.");
            return;
        }
        for (int i = 0; i < games.size(); i++) {
            System.out.println((i + 1) + ". " + games.get(i));
        }
        System.out.print("Choose a game: ");
        int choice = scanner.nextInt() - 1;
        scanner.nextLine();

        if (choice >= 0 && choice < games.size()) {
            System.out.println(facade.selectGame(choice));
        } else {
            System.out.println("Invalid choice. Please try again.");
            selectGame(); // Retry if invalid choice
        }
    }

    private void navigateContent() {
        System.out.println("\n--- Content Navigation ---");
        System.out.println("Use 1 for Next, 2 for Previous, and 3 to Exit Navigation.");
        while (true) {
            System.out.println(facade.showCurrentTextObject());
            System.out.print("Choose (1: Next, 2: Previous, 3: Exit): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println(facade.nextTextObject());
            } else if (choice == 2) {
                System.out.println(facade.previousTextObject());
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void startQuiz() {
        System.out.println(facade.startQuiz());
        while (true) {
            String question = facade.getNextQuizQuestion();
            if ("Quiz complete!".equals(question)) break;

            System.out.println("Question: " + question);
            System.out.print("Your Answer: ");
            String answer = scanner.nextLine();
            System.out.println(facade.validateQuizAnswer(answer) ? "Correct!" : "Incorrect.");
        }
    }

    private void exitApplication() {
        System.out.println(facade.quitApplication());
        System.exit(0);
    }

    public static void main(String[] args) {
        UI ui = new UI();
        ui.run();
    }
}
