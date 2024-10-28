package com.languageLearner.app;

import com.languageLearner.data.DataConstants;
import com.languageLearner.data.DataKey;
import com.languageLearner.data.DataLoader;
import com.languageLearner.data.DataWriter;
import com.languageLearner.data.User;
import com.languageLearner.data.UserList;

public class LanguageLearningApplication extends DataConstants {

    private User currentUser;
    private String currentLanguage;
    private static LanguageLearningApplication instance;

    // Private constructor for singleton pattern
    private LanguageLearningApplication() {
        setCurrentLanguage("filipino");
    }

    // Get the instance of the facade
    public static LanguageLearningApplication getInstance() {
        if (instance == null) {
            instance = new LanguageLearningApplication();
        }
        return instance;
    }

    // Load users and game data when the application starts
    public void load() {
        DataLoader dataLoader = new DataLoader();
        dataLoader.loadGameData();
        dataLoader.loadUsers();
    }

    // User signup (add a new user)
    public boolean signup(String email, String username, String displayName, String password) {
        User newUser = new User(email, username, displayName, password);
        return UserList.getInstance().addUser(newUser);
    }

    // User login
    public boolean signin(String email, String password) {
        User user = UserList.getInstance().login(email, password);
        if (user != null) {
            this.currentUser = user;
            this.currentLanguage = LANGUAGE; // Set default language upon login
            System.out.println("Login successful for user: " + user.getUsername());
            return true; // Successful login
        }
        return false; // Login failed
    }

    // Check if a user is currently logged in
    public boolean isLoggedIn() {
        return currentUser != null;
    }

    // Logout current user and save progress
    public void logout() {
        if (currentUser != null) {
            saveUserData();
            System.out.println("User " + currentUser.getUsername() + " has been logged out.");
        }
        this.currentUser = null;
        this.currentLanguage = null;
    }

    // Start a game based on DataKey
    public void startGame(DataKey dataKey) {
        String gameType = dataKey.getGameType();
        switch (gameType) {
            case DataConstants.COLORS_GAME:
                ColorsGame colorsGame = new ColorsGame();
                colorsGame.startGame();
                
                break;
            case DataConstants.ALPHABET_GAME:
                AlphabetGame alphabetGame = new AlphabetGame();
                alphabetGame.startGame();
                addGameToTrack();
                break;
            case DataConstants.NUMBERS_GAME:
                NumbersGame numbersGame = new NumbersGame();
                addGameToTrack();
                numbersGame.startGame();
                break;
            case DataConstants.STORIES_GAME:
                StoriesGame storiesGame = new StoriesGame();
                addGameToTrack();
                storiesGame.startGame();
                break;
            default:
                System.out.println("Invalid game type.");
        }
    }

    // Facade method to view progress for the current language
    public void viewProgress() {
        if (isLoggedIn() && currentLanguage != null) {
            ProgressViewer.displayProgress(currentUser, currentLanguage);
        } else {
            System.out.println("No user is currently logged in or language not set.");
        }
    }

    // Facade method to review missed questions for the current language
    public void reviewMissedQuestions() {
        if (isLoggedIn() && currentLanguage != null) {
            ProgressViewer.reviewMissedQuestions(currentUser, currentLanguage);
        } else {
            System.out.println("No user is currently logged in or language not set.");
        }
    }

    // Save user data to JSON file on logout or app exit
    private void saveUserData() {
        DataWriter dataWriter = new DataWriter();
        dataWriter.writeAllUsers();
    }

    // Exit application with logout and save data
    public void exit() {
        if (currentUser != null) {
            logout();
        }
        System.exit(0);
    }

    // Set current language without command-line output (facade requirement)
    public void setCurrentLanguage(String language) {
        this.currentLanguage = language;
    }

    // Getters and Setters for currentUser
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void addGameToTrack() {
        currentUser.addCompletedGame(DataKey.getInstance());
    }
}
