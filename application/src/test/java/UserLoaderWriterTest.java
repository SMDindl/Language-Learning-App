
import com.languageLearner.data.User;
import com.languageLearner.data.UserList;
import com.languageLearner.data.UserLoader;
import com.languageLearner.data.DataWriter;

import java.util.UUID;

public class UserLoaderWriterTest {

    public static void main(String[] args) {
        try {
            // Step 1: Load existing users from file
            System.out.println("\n--- Loading Existing Users ---");
            UserLoader userLoader = new UserLoader();
            userLoader.loadUsers();  // Load users from the file

            // Step 2: Display loaded users
            UserList userList = UserList.getInstance();
            System.out.println("Loaded Users:");
            for (User user : userList.getUsers()) {
                System.out.println("Username: " + user.getUsername() + ", Email: " + user.getEmail());
            }

            // Step 3: Create a new user and add to UserList
            System.out.println("\n--- Creating a New User ---");
            String newEmail = "newUser@example.com";
            String newUsername = "newUser";
            String newDisplayName = "New User";
            String newPassword = "newPassword123";
            User newUser = new User(newEmail, newUsername, newDisplayName, newPassword, UUID.randomUUID());
            
            // Add the new user to the UserList
            userList.addUser(newUser);
            System.out.println("New user added: " + newUsername);

            // Step 4: Display all users after adding the new user
            System.out.println("\nAll Users After Adding New User:");
            for (User user : userList.getUsers()) {
                System.out.println("Username: " + user.getUsername() + ", Email: " + user.getEmail());
            }

            // Step 5: Use DataWriter to save all users back to the file
            System.out.println("\n--- Saving All Users ---");
            DataWriter dataWriter = new DataWriter();
            dataWriter.writeAllUsers();  // Write all users (including the new one) to the file

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
