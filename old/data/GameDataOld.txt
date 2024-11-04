package com.languageLearner.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

import com.languageLearner.app.Question;

/**
 * Singleton
 */
public class GameDataOld {

    // Singleton instance
    private static GameDataOld instance;

    // Language management
    private HashMap<UUID, String> languages;      // list of all language UUIDS, alongside name of the language

    // Difficulties management
    private final ArrayList<String> DIFFICULTIES = new ArrayList<>(Arrays.asList("EASY", "MEDIUM", "HARD"));

    // Game data management
    private ArrayList<Game> games;                          // list of all games per language, each instance of game has their language uuid stored w/ it
    private HashMap<Game, UUID> gamesWithUUIDs;             // games alongside with their own uuid, Game.getUUID(), for easy management

    // Question data mangement
    private HashMap<UUID, ArrayList<Question>> questions;   // questions alongside to their game's respective uuid
    private HashMap<Question, UUID> questionsWithUUIDs;     // questions linked with their own uuid, question.getUUID(), for easy searching

    // TextObject data mangement
    private HashMap<UUID, ArrayList<TextObject>> textObject;    // questions linked to their game's respective uuid
    private HashMap<TextObject, UUID> textObjectWithUUIDs;       // questions linked with their own uuid, question.getUUID(), for easy searching

    // GameInfo mangement
    private HashMap<UUID, ArrayList<GameInfo>> gameInfo;    // questions linked to their game's respective uuid
    private HashMap<GameInfo, UUID> gameInfoWithUUIDs;       // questions linked with their own uuid, question.getUUID(), for easy searching

    // generatedQuestions

    private GameDataOld() {
        // Language mangement
        languages = new HashMap<>();

        // Game mangement
        games = new ArrayList<>();
        gamesWithUUIDs = new HashMap<>();

        // Questions management
        questions = new HashMap<>();
        questionsWithUUIDs = new HashMap<>();

        // TextObject management
        textObject = new HashMap<>();
        textObjectWithUUIDs = new HashMap<>();

        gameInfo = new HashMap<>();
        gameInfoWithUUIDs = new  HashMap<>();
    }

    public static GameDataOld getInstance() {
        if(instance == null) {
            instance = new GameDataOld();
        }
        return instance;
    }

    /**
     * Adds a language if it doesn't already exist.
     *
     * @param uuid      the UUID of the language
     * @param language  the name of the language
     * @return true if the language was added, false if it was a duplicate
     */
    public boolean addLanguage(UUID languageUUID, String languageName) {
        if (!languages.containsKey(languageUUID)) {
            languages.put(languageUUID, languageName);
            return true;
        }
        return false;
    }

    /**
     * Adds a game to:
     * - ArrayList<Game> games
     * Adds a game and game.UUID to:
     * - HashMap<Game, UUID> gamesWithUUIDs
     *
     * @param game the Game object to add
     * @return true if the game was added, false if it was a duplicate
     */
    public boolean addGame(Game game) {
        UUID uuid = game.getUUID();
        if (!gamesWithUUIDs.containsValue(uuid)) {
            games.add(game);                        // Add to list of games
            gamesWithUUIDs.put(game, uuid);         // Add to list of game+uuid 
            return true;
        }
        return false;
    }

    // private HashMap<UUID, ArrayList<Question>> questions;   // questions alongside to their game's respective uuid
    // private HashMap<Question, UUID> questionsWithUUIDs;     // questions linked with their own uuid, question.getUUID(), for easy searching

    /**
     * Adds a question to:
     * - HashMap<UUID, ArrayList<Question>> questions
     * Adds a question and question.getUUID to;
     * - HashMap<Question, UUID> questionsWithUUIDs
     * 
     * @param question
     * @return
     */
    public boolean addQuestion(Question question) {
        UUID uuid = question.getUUID();
        ArrayList<Question> questionList = questions.get(question.getGameUUID());
        if (!gamesWithUUIDs.containsValue(uuid)) {
            
            questionsWithUUIDs.put(question, uuid);                 // Add to list of question+uuid 
            return true;
        }
        return false;
    }

    // Getters 
    public HashMap<UUID, String> getLanguages() {
        return languages;
    }

    public ArrayList<String> getDifficulties() {
        return DIFFICULTIES;
    }

    public HashMap<Game, UUID> getGamesWithUUIDs() {
        return gamesWithUUIDs;
    }

    /**
     * Returns List of gameTitles based upon the uuid of the language
     */
    public ArrayList<String> getGameTitlesByUUID(UUID targetUUID) {
        ArrayList<String> gameNames = new ArrayList<>();
    
        // Iterate through each entry in the gamesWithUUIDs map
        for (HashMap.Entry<Game, UUID> entry : gamesWithUUIDs.entrySet()) {
            
            if (entry.getValue().equals(targetUUID)) { // Check if the UUID matches the target UUID
                gameNames.add(entry.getKey().getGameTitle());
            }
        }
    
        return gameNames;
    }

    /**
     * Returns List of gamesByUUID
     * 
     */
    public ArrayList<Game> getGameByUUID(UUID targetUUID) {
        ArrayList<Game> gamesList = new ArrayList<>();
    
        // Iterate through each entry in the gamesWithUUIDs map
        for (HashMap.Entry<Game, UUID> entry : gamesWithUUIDs.entrySet()) {
            
            if (entry.getValue().equals(targetUUID)) { // Check if the UUID matches the target UUID
                gamesList.add(entry.getKey());
            }
        }
    
        return gamesList;
    }

    /**
     * Return questions for specific uuid 
     * 
     * @param targetUUID
     * @return
     */
    public ArrayList<Question> questionsForGame(UUID targetUUID) {
        ArrayList<Question> questions = new ArrayList<>();
    
        // Iterate through each entry in the gamesWithUUIDs map
        for (HashMap.Entry<Question, UUID> entry : questionsWithUUIDs.entrySet()) {
            
            if (entry.getValue().equals(targetUUID)) { // Check if the UUID matches the target UUID
                questions.add(entry.getKey());
            }
        }
    
        return questions;
    }


}
