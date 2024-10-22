import java.util.Scanner;
import java.util.UUID;

import com.languageLearner.data.DataWriter;
import com.languageLearner.data.User;
import com.languageLearner.data.UserList;
import com.languageLearner.data.UserLoader;

public class LoginUIOLD {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Step 1: Load existing users from file
            System.out.println("\n--- Loading Existing Users ---");
            UserLoader userLoader = new UserLoader();
            userLoader.loadUsers();  // Load users from the file

            UserList userList = UserList.getInstance();

            // Login (Valid and Invalid)
            System.out.println("\n--- Login ---");
            System.out.print("Enter email: ");
            String email = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            User loggedInUser = login(email, password);
            if (loggedInUser != null) {
                System.out.println("Login successful! Welcome, " + loggedInUser.getDisplayName());
            } else {
                System.out.println("Login failed: Invalid email or password.");
            }

            // Add User (Valid and Invalid)
            System.out.println("\n--- Add New User ---");
            System.out.print("Enter new email: ");
            String newEmail = scanner.nextLine();
            System.out.print("Enter new username: ");
            String newUsername = scanner.nextLine();
            System.out.print("Enter new display name: ");
            String newDisplayName = scanner.nextLine();
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();

            boolean userAdded = userList.addUser(new User(newEmail, newUsername, newDisplayName, newPassword, UUID.randomUUID()));
            if (userAdded) {
                System.out.println("New user added: " + newUsername);
            } else {
                System.out.println("Failed to add user: Email or username already taken.");
            }

            // Save all users after adding new users
            System.out.println("\n--- Saving All Users ---");
            DataWriter dataWriter = new DataWriter();
            dataWriter.writeAllUsers();  // Write all users (including the new one) to the file

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    // Method for user login
    public static User login(String email, String password) {
        UserList userList = UserList.getInstance();
        for (User user : userList.getUsers()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user; // Login success
            }
        }
        return null; // Login failed
    }
}
