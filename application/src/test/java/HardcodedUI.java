import java.util.ArrayList; // Importing necessary classes

import com.languageLearner.app.StoriesGame;
import com.languageLearner.data.DataConstants;
import com.languageLearner.data.DataKey;
import com.languageLearner.data.DataLoader;
import com.languageLearner.data.GameData;
import com.languageLearner.data.Letter;
import com.languageLearner.data.Page;
import com.languageLearner.data.ProgressTracker;
import com.languageLearner.data.Question;
import com.languageLearner.data.Story;
import com.languageLearner.data.User;
import com.languageLearner.data.UserList;
import com.languageLearner.data.Word;

/**
 * Tester class for loading and printing game data and user list.
 */
public class HardcodedUI extends DataConstants {

    public static void main(String[] args) {

        // Load the game data and users
        DataLoader dataLoader = new DataLoader();
        GameData gameData = GameData.getInstance();
        dataLoader.loadGameData(); // Load game data
        dataLoader.loadUsers(); // Load user data

        // Print all game data by language, game type, and difficulty
        String language = DataConstants.FILIPINO; // Example language
        printAllGameData(gameData, language);

        // Print all users in the user list
        printAllUsers(UserList.getInstance());



        // Simulate login for the hardcoded user "sdindl@sc.email.edu"
        User current = UserList.getInstance().login("sdindl@sc.email.edu", "Password2024");

        if (current != null) {
            System.out.println("Login successful! Welcome, " + current.getDisplayName());
        } else {
            System.out.println("Login failed.");
            return;
        }

        // Hardcoded DataKey for Filipino stories game, easy difficulty
        System.out.println("Picking: Filipino Easy StoriesGame");
        DataKey dataKey = DataKey.getInstance("filipino", "storiesGame", "medium");

        // Start the StoriesGame (automatically goes through the story and then a quiz)
        StoriesGame storiesGame = new StoriesGame();
        storiesGame.startGame(); // Story will be read, then quiz will follow

        // Simulate finishing the story and quiz
        System.out.println("Story and quiz completed!");
    }



/*
 * 
 * METHODS TO PRINT ALL INFO
 * 
 */

    // Method to print all game data
    public static void printAllGameData(GameData gameData, String language) {
        String[] gameTypes = {DataConstants.ALPHABET_GAME, DataConstants.COLORS_GAME, DataConstants.NUMBERS_GAME, DataConstants.STORIES_GAME};
        String[] difficulties = {DataConstants.EASY, DataConstants.MEDIUM, DataConstants.HARD};

        for (String gameType : gameTypes) {
            System.out.println("\n=== Game: " + gameType + " ===");

            for (String difficulty : difficulties) {
                DataKey dataKey = DataKey.getInstance(language, gameType, difficulty);
                System.out.println("--- Difficulty: " + difficulty + " ---");

                // Print words
                if (gameData.getWords(dataKey) != null) {
                    printWords(gameData, dataKey);
                }

                // Print questions
                if (gameData.getQuestions(dataKey) != null) {
                    printQuestions(gameData, dataKey);
                }

                // Print stories
                if (gameData.getStories(dataKey) != null) {
                    printStories(gameData, dataKey);
                }

                // Print letters
                if (gameData.getLetters(dataKey) != null) {
                    printLetters(gameData, dataKey);
                }
            }
        }
    }

    // Method to print all users from UserList
    public static void printAllUsers(UserList userList) {
        System.out.println("\n=== User List ===");

        for (User user : userList.getUsers()) {
            System.out.println("\nUser:");
            System.out.println("UUID: " + user.getUuid());
            System.out.println("Username: " + user.getUsername());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Display Name: " + user.getDisplayName());
            System.out.println("Password: " + user.getPassword());

            System.out.println("--- Progress Trackers ---");
            for (ProgressTracker tracker : user.getProgressTrackers()) {
                System.out.println("Language: " + tracker.getLanguage());
                System.out.println("Completed Games: ");
                for (DataKey completedGame : tracker.getCompletedGames()) {
                    System.out.println("  " + completedGame.toString());
                }
            }
        }
    }

    // Method to print words
    private static void printWords(GameData gameData, DataKey dataKey) {
        System.out.println("---- Words ----");
        ArrayList<Word> words = gameData.getWords(dataKey);
        if (words != null && !words.isEmpty()) {
            for (Word word : words) {
                System.out.println("Word: " + word.getWordText() + ", Translation: " + word.getWordTranslation());
            }
        } else {
            System.out.println("No words available.");
        }
    }

    // Method to print questions
    private static void printQuestions(GameData gameData, DataKey dataKey) {
        System.out.println("---- Questions ----");
        ArrayList<Question> questions = gameData.getQuestions(dataKey);
        if (questions != null && !questions.isEmpty()) {
            for (Question question : questions) {
                System.out.println("Question: " + question.getQuestionText());
                System.out.println("Correct Answer: " + question.getCorrectAnswer());
            }
        } else {
            System.out.println("No questions available.");
        }
    }

    // Method to print stories
    private static void printStories(GameData gameData, DataKey dataKey) {
        System.out.println("---- Stories ----");
        ArrayList<Story> stories = gameData.getStories(dataKey);
        if (stories != null && !stories.isEmpty()) {
            for (Story story : stories) {
                System.out.println("Story Title: " + story.getTitle());
                System.out.println("Author: " + story.getAuthor());
                for (Page page : story.getPages()) {
                    System.out.println("Page Number: " + page.getPageNumber());
                    System.out.println("Page Content: " + page.getText());
                }
            }
        } else {
            System.out.println("No stories available.");
        }
    }

    // Method to print letters
    private static void printLetters(GameData gameData, DataKey dataKey) {
        System.out.println("---- Letters ----");
        ArrayList<Letter> letters = gameData.getLetters(dataKey);
        if (letters != null && !letters.isEmpty()) {
            for (Letter letter : letters) {
                System.out.println("Letter: " + letter.getLetter() + ", Pronunciation: " + letter.getPronunciation());
            }
        } else {
            System.out.println("No letters available.");
        }
    }
}
