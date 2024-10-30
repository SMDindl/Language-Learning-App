package com.languageLearner.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import com.languageLearner.app.Question;

/**
 * Singleton
 */
public class GameData2 {

    // Singleton instance
    private static GameData2 instance;

    // Language management
    private UUIDMap<String> languages;

    // Difficulties management
    private final ArrayList<String> DIFFICULTIES = new ArrayList<>(Arrays.asList("EASY", "MEDIUM", "HARD"));

    // Game data management
    private UUIDMap<Game> games;                    // list of all games per language, each instance of game has their language uuid stored w/ it
    private UUIDMap<Game> gamesWithGameIDs;             // games alongside with their own uuid, Game.getUUID(), for easy management

    // Question data mangement
    private UUIDMap<ArrayList<Question>> questions;         // questions alongside to their game's respective uuid
    private UUIDMap<Question> questionsWithQuestionIDs;     // questions linked with their own uuid, question.getUUID(), for easy searching

    // TextObject data mangement
    private UUIDMap<ArrayList<TextObject>> textObject;    // questions linked to their game's respective uuid
    private UUIDMap<TextObject> textObjectWithTextObjectIDs;       // questions linked with their own uuid, question.getUUID(), for easy searching

    // GameInfo mangement
    private UUIDMap<ArrayList<GameInfo>> gameInfo;    // questions linked to their game's respective uuid
    private UUIDMap<GameInfo> gameInfoWithGameInfoIDs;       // questions linked with their own uuid, question.getUUID(), for easy searching

    // generatedQuestions

    private GameData2() {
        // Language mangement
        languages = new UUIDMap<>();

        // Game mangement
        games =  new UUIDMap<>();
        gamesWithGameIDs = new UUIDMap<>();

        // Questions management
        questions = new UUIDMap<>();
        questionsWithQuestionIDs = new UUIDMap<>();

        // TextObject management
        textObject = new UUIDMap<>();
        textObjectWithTextObjectIDs = new UUIDMap<>();

        gameInfo = new UUIDMap<>();
        gameInfoWithGameInfoIDs = new  UUIDMap<>();
    }

    public static GameData2 getInstance() {
        if(instance == null) {
            instance = new GameData2();
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
    public void addLanguage(UUID languageUUID, String languageName) {
        languages.add(languageUUID, languageName);
    }

    /**
     * Adds a game if it doesn't already exist.
     *
     * @param uuid      the UUID of the game
     * @param game  the name of the language
     * @return true if the language was added, false if it was a duplicate
     */
    public void addGame(Game game, UUID languageUUID) {
        games.add(languageUUID, game);
        gamesWithGameIDs.add(game.getUUID(), game);
    }

    /**
     * Adds a question associated with a specific game UUID.
     *
     * @param uuid the UUID of the game
     * @param question the Question to add
     */
    public void addQuestion(UUID uuid, Question question) {
        questions.add(uuid, question);  // Adds question to the game's UUID list
        questionsWithQuestionIDs.add(question.getUUID(), question);  // Adds question with its specific UUID
    }

    /**
     * Adds a TextObject associated with a specific game UUID.
     *
     * @param uuid the UUID of the game
     * @param textObject the TextObject to add
     */
    public void addTextObject(UUID uuid, TextObject textObject) {
        this.textObject.addToList(uuid, textObject);  // Adds TextObject to the game's UUID list
        this.textObjectWithTextObjectIDs.add(textObject.getUUID(), textObject);  // Adds TextObject with its specific UUID
    }





}
