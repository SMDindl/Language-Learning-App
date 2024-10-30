package com.languageLearner.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import com.languageLearner.app.Question;

/**
 * Singleton class to manage game data
 */
@SuppressWarnings("FieldMayBeFinal")
public class GameData {

    // Singleton instance
    private static GameData instance;

    // Language management
    private UUIDMap<String> languages;

    // Difficulties management
    private final ArrayList<String> DIFFICULTIES = new ArrayList<>(Arrays.asList("EASY", "MEDIUM", "HARD"));

    // All of these different data types store the game thing, but paired with different uuids to help handle different scenarios
    // Game data management
    private UUIDMap<Game> games;                    // list of all games per language, each instance of game has their language uuid stored w/ it
    private UUIDMap<Game> gamesWithGameIDs;         // games alongside with their own uuid, Game.getUUID(), for easy management

    // Question data management
    private UUIDMap<Question> questionsWithGameIDs;     // questions linked to their game's UUID
    private UUIDMap<Question> questionsWithQuestionIDs; // questions linked with their own UUID

    // TextObject data management
    private UUIDMap<TextObject> textObjectsWithGameIDs; // TextObjects linked to their game's UUID
    private UUIDMap<TextObject> textObjectsWithTextObjectIDs; // TextObjects linked with their own UUID

    // GameInfo management
    private UUIDMap<GameInfo> gameInfoWithGameIDs;      // GameInfo linked to their game's UUID
    private UUIDMap<GameInfo> gameInfoWithGameInfoIDs;  // GameInfo linked with their own UUID

    private GameData() {
        // Initialize language management
        languages = new UUIDMap<>();

        // Initialize game management
        games = new UUIDMap<>();
        gamesWithGameIDs = new UUIDMap<>();

        // Initialize question management
        questionsWithGameIDs = new UUIDMap<>();
        questionsWithQuestionIDs = new UUIDMap<>();

        // Initialize TextObject management
        textObjectsWithGameIDs = new UUIDMap<>();
        textObjectsWithTextObjectIDs = new UUIDMap<>();

        // Initialize GameInfo management
        gameInfoWithGameIDs = new UUIDMap<>();
        gameInfoWithGameInfoIDs = new UUIDMap<>();
    }

    public static GameData getInstance() {
        if (instance == null) {
            instance = new GameData();
        }
        return instance;
    }

    public UUIDMap<String> getLanguages() {
        return languages;
    }

    public ArrayList<String> getDifficulties() {
        return DIFFICULTIES;
    }

    public ArrayList<Game> getGames() {
        return games.get(UUID.fromString("1bafb0ae-3462-4ec3-9cc2-a98ff2898e72"));
    }

    // Adder methods
    // Includes ablities for adding:
    // languages, games, questions, textObjects, gameInfo
    /**
     * Adds a language if it doesn't already exist.
     *
     * @param languageUUID the UUID of the language
     * @param languageName the name of the language
     */
    public void addLanguage(UUID languageUUID, String languageName) {
        languages.add(languageUUID, languageName);
    }

    /**
     * Adds a game if it doesn't already exist.
     *
     * @param game         the Game to add
     * @param languageUUID the UUID of the language the game belongs to
     */
    public void addGame(UUID languageUUID, Game game) {
        games.add(languageUUID, game);
        gamesWithGameIDs.add(game.getUUID(), game);
    }

    /**
     * Adds a question associated with a specific game UUID.
     *
     * @param gameUUID the UUID of the game
     * @param question the Question to add
     */
    public void addQuestion(UUID gameUUID, Question question) {
        questionsWithGameIDs.add(gameUUID, question);             // Adds question to the list of questions by game UUID
        questionsWithQuestionIDs.add(question.getUUID(), question); // Adds question by its specific UUID
    }

    /**
     * Adds a TextObject associated with a specific game UUID.
     *
     * @param gameUUID  the UUID of the game
     * @param textObject the TextObject to add
     */
    public void addTextObject(UUID gameUUID, TextObject textObject) {
        textObjectsWithGameIDs.add(gameUUID, textObject);               // Adds TextObject by game UUID
        textObjectsWithTextObjectIDs.add(textObject.getUUID(), textObject); // Adds TextObject by its specific UUID
    }

    /**
     * Adds GameInfo associated with a specific game UUID.
     *
     * @param gameUUID the UUID of the game
     * @param gameInfo the GameInfo to add
     */
    public void addGameInfo(UUID gameUUID, GameInfo gameInfo) {
        gameInfoWithGameIDs.add(gameUUID, gameInfo);                   // Adds GameInfo by game UUID
        gameInfoWithGameInfoIDs.add(gameInfo.getUUID(), gameInfo);     // Adds GameInfo by its specific UUID
    }
}
