package com.languageLearner.test;

import com.languageLearner.app.StoriesGame;
import com.languageLearner.data.DataKey;
import com.languageLearner.data.User;
import com.languageLearner.data.UserList;
import com.languageLearner.data.UserLoader;


public class HardcodedUI {

    public static void main(String[] args) {
        // Load users
        UserLoader userLoader = new UserLoader();
        userLoader.loadUsers();

        // Simulate login for the hardcoded user "sdindl@sc.email.edu"
        UserList userList = UserList.getInstance();
        User loggedInUser = userList.login("sdindl@sc.email.edu", "Password2024");

        if (loggedInUser != null) {
            System.out.println("Login successful! Welcome, " + loggedInUser.getDisplayName());
        } else {
            System.out.println("Login failed.");
            return;
        }

        // Hardcoded DataKey for Filipino stories game, easy difficulty
        DataKey dataKey = DataKey.getInstance("filipino", "storiesGame", "easy");

        // Start the StoriesGame (automatically goes through the story and then a quiz)
        StoriesGame storiesGame = new StoriesGame(dataKey);
        storiesGame.startGame(); // Story will be read, then quiz will follow

        // Simulate finishing the story and quiz
        System.out.println("Story and quiz completed!");
    }
    
}
