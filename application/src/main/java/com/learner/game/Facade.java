package com.learner.game;

import java.util.ArrayList;
import java.util.List;
import com.learner.game.loadwrite.DataLoader;

public class Facade {

    private static Facade instance;
    private final FacadeForGame gameFacade = new FacadeForGame();
    private final GameManager gameManager = GameManager.getInstance();
    private final UserList userList = UserList.getInstance();

    private Language currentLanguage;
    private Difficulty currentDifficulty;
    private User currentUser;

    private Facade() {}

    public static Facade getInstance() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }

    // Data loading
    public void loadData(String gameDataFilePath, String userDataFilePath) {
        DataLoader.loadGameData(gameDataFilePath);
        DataLoader.loadUserData(userDataFilePath);
    }

    // User session management
    public String loginUser(String email, String password) {
        User user = userList.login(email, password);
        if (user != null) {
            currentUser = user;
            return "Login successful.";
        }
        return "Login failed. Incorrect email or password.";
    }

    public String registerUser(String email, String username, String displayName, String password) {
        User newUser = new User(email, username, displayName, password);
        return userList.addUser(newUser) ? "Registration successful." : "Email or username already exists.";
    }

    public String logoutUser() {
        if (currentUser == null) return "No user is currently logged in.";
        currentUser = null;
        currentLanguage = null;
        currentDifficulty = null;
        return "Logout successful.";
    }

    public boolean isUserLoggedIn() {
        return currentUser != null;
    }

    public String quitApplication() {
        if (isUserLoggedIn()) {
            logoutUser();
        }
        return "Application closed.";
    }

    // Language and difficulty selection
    public ArrayList<String> getAvailableLanguages() {
        ArrayList<String> languages = new ArrayList<>();
        for (Language language : gameManager.getAllLanguages()) {
            languages.add(language.getLanguageName());
        }
        return languages;
    }

    public String selectLanguage(int index) {
        ArrayList<Language> allLanguages = gameManager.getAllLanguages();
        if (index >= 0 && index < allLanguages.size()) {
            currentLanguage = allLanguages.get(index);
            return "Language set to " + currentLanguage.getLanguageName() + ".";
        }
        return "Invalid language selection.";
    }

    public ArrayList<String> getAvailableDifficulties() {
        ArrayList<String> difficulties = new ArrayList<>();
        difficulties.add(Difficulty.EASY.name());
        difficulties.add(Difficulty.MEDIUM.name());
        difficulties.add(Difficulty.HARD.name());
        return difficulties;
    }

    public String selectDifficulty(Difficulty difficulty) {
        currentDifficulty = difficulty;
        return "Difficulty set to " + difficulty.name() + ".";
    }

    // Game-related methods delegated to GameFacade
    public ArrayList<String> getAvailableGames() {
        return (currentLanguage != null && currentDifficulty != null) 
                ? gameFacade.getAvailableGames(currentLanguage.getUUID(), currentDifficulty) 
                : new ArrayList<>(List.of("Select language and difficulty first."));
    }

    public String selectGame(int gameIndex) {
        if (currentLanguage == null || currentDifficulty == null) {
            return "Select language and difficulty first.";
        }
        ArrayList<Game> availableGames = gameManager.getGamesByDifficulty(currentLanguage.getUUID(), currentDifficulty);
        if (gameIndex >= 0 && gameIndex < availableGames.size()) {
            Game selectedGame = availableGames.get(gameIndex);
            return gameFacade.selectGame(selectedGame.getUUID());
        }
        return "Invalid game selection.";
    }

    // Navigation and quiz methods
    public String showCurrentTextObject() {
        return gameFacade.showCurrentTextObject();
    }

    public String nextTextObject() {
        return gameFacade.nextTextObject();
    }

    public String previousTextObject() {
        return gameFacade.previousTextObject();
    }

    public String startQuiz() {
        return gameFacade.startQuiz();
    }

    public String getNextQuizQuestion() {
        return gameFacade.getNextQuizQuestion();
    }

    public boolean validateQuizAnswer(String answer) {
        return gameFacade.validateQuizAnswer(answer);
    }

    public String endGameSession() {
        if (currentUser == null || currentLanguage == null) return "No active game session.";
        return gameFacade.endGameSession(currentUser, currentLanguage.getUUID());
    }

    public User getCurrentUser() {
        return currentUser;
    }
}

    // Will we need game methods directly interconnected to facade?
    // Methods of game / game logic needs to be created,
    // every game is just built upon text info,
    // user, getting next text info, until the end, this the option to go back
    // once the end is hit we will be asking questions,
    // we can pull 3 multiple choice and create 2 fill in the blank, 
    // create 1 matching, and then only give a sequncing if the gameUUID, if within a
    // list of gameUUIDs that will be stored in gameConstants (bc we only want to give sequence problems when 
    // the sequence matters, like for a story), 