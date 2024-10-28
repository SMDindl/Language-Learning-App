import java.util.Scanner;

import com.languageLearner.app.LanguageLearningApplication;
import com.languageLearner.data.DataWriter;

public class UIold {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LanguageLearningApplication app = LanguageLearningApplication.getInstance(); // Access the facade

        try {
            // Load data (users and game data)
            app.load();

            boolean isRunning = true;

            // Loop to show login/signup/exit options
            while (isRunning) {

                // Welcome message
                // Option to login, signup, or exit the program
                System.out.println("\n--- Welcome to the Language Learning App ---");
                System.out.println("1. Login");
                System.out.println("2. Signup");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                String option = scanner.nextLine();

                switch (option) {
                    case "1": // LOGIN
                        // Login process
                        String email = getInput(scanner, "Enter email: ");
                        String password = getInput(scanner, "Enter password: ");
                        if (app.signin(email, password)) {
                            System.out.println("Login successful! Welcome.");
                            // After login, proceed to main menu
                            showMainMenu(scanner, app);
                        } else {
                            System.out.println("Login failed: Invalid email or password.");
                        }
                        break;

                    case "2": // SIGN UP
                        // Signup process
                        String newEmail = getInput(scanner, "Enter new email: ");
                        String newUsername = getInput(scanner, "Enter new username: ");
                        String newDisplayName = getInput(scanner, "Enter new display name: ");
                        String newPassword = getInput(scanner, "Enter new password: ");
                        if (app.signup(newEmail, newUsername, newDisplayName, newPassword)) {
                            System.out.println("New user added: " + newUsername);
                        } else {
                            System.out.println("Failed to add user: Email or username already taken.");
                        }
                        break;

                    case "3": // EXIT APP
                        System.out.println("Exiting the application. Goodbye!");
                        isRunning = false; // Exit the loop and program
                        break;

                    default: // DEFAULT
                        System.out.println("Invalid option. Please choose 1, 2, or 3.");
                        break;
                }
            }

            // Save all users after any changes (MIGHT WANT TO CHANGE SAVE CHANGES)
            System.out.println("\n--- Saving All Users ---");
            DataWriter dataWriter = new DataWriter();
            dataWriter.writeAllUsers();  // Write all users (including any new ones) to the file

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    // Method to handle user input and prompt
    private static String getInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // Main menu after login 
    // Play or Logout
    private static void showMainMenu(Scanner scanner, LanguageLearningApplication app) {
        boolean isLoggedIn = true;

        while (isLoggedIn) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Play");
            System.out.println("2. Logout");
            System.out.print("Choose an option: ");
            String mainOption = scanner.nextLine();

            switch (mainOption) {
                case "1":
                    // Play game (stub)
                    System.out.println("Playing... (stub)");
                    System.out.println("done playing!");
                    break;

                case "2":
                    // Logout
                    app.logout();
                    System.out.println("You have logged out.");
                    isLoggedIn = false; // Exit to the login/signup menu
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1 or 2.");
                    break;
            }
        }
    }
}
