package com.learner.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import com.learner.game.innerdata.TextObject;
import com.learner.game.questions.Question;
import com.learner.game.questions.QuestionType;

public class GameManager {

    private static GameManager instance; // Singleton instance


    private final HashMap<Language, ArrayList<UUID>> languages; // Key = Language instance, value = list of Game UUIDs for that language
    private final HashMap<UUID, ArrayList<Game>> easyGames;     // Key = UUID of each language, value = list of Game instances for that language's easy games
    private final HashMap<UUID, ArrayList<Game>> mediumGames;   // Key = UUID of each language, value = list of Game instances for that language's medium games
    private final HashMap<UUID, ArrayList<Game>> hardGames;     // Key = UUID of each language, value = list of Game instances for that language's hard games
    private final HashMap<UUID, ArrayList<Question>> questions; // Key = UUID of the game, value = list of Question instances for that Game
    private final HashMap<UUID, ArrayList<UUID>> textObjects;   // Key = UUID of the game, value = list of textObject UUIDs

    // Private constructor to prevent instantiation from outside
    private GameManager() {
        languages = new HashMap<>();
        easyGames = new HashMap<>();
        mediumGames = new HashMap<>();
        hardGames = new HashMap<>();
        questions = new HashMap<>();
        textObjects = new HashMap<>();
    }

    // Public method to get the singleton instance
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    // Method to initialize a language in the languages map if needed
    public void initializeLanguage(Language language) {
        languages.computeIfAbsent(language, k -> new ArrayList<>());
    }

    // Method to add a single game UUID to an existing language by language UUID
    public void addGameUUIDToLanguage(UUID languageUUID, UUID gameUUID) {
        Language language = getLanguageByUUID(languageUUID);
        
        if (language != null) {
            languages.get(language).add(gameUUID);
        } else {
            System.out.println("Language with UUID " + languageUUID + " not found.");
        }
    }

    // Helper method to find a Language by its UUID
    private Language getLanguageByUUID(UUID languageUUID) {
        for (Language language : languages.keySet()) {
            if (language.getUUID().equals(languageUUID)) {
                return language;
            }
        }
        return null;
    }

    // Getter for languages, returning a list of Language instances
    public ArrayList<Language> getLanguages() {
        return new ArrayList<>(languages.keySet());
    }

    public HashMap<Language, ArrayList<UUID>> getLanguageMap() {
        return languages;
    }

    public void addTextObjectUUID(UUID gameUUID, UUID textObjectUUID) {
        textObjects.computeIfAbsent(gameUUID, k -> new ArrayList<>()).add(textObjectUUID);
    }

    // Methods to add games by difficulty
    public void addEasyGame(UUID languageUUID, Game game) {
        easyGames.computeIfAbsent(languageUUID, k -> new ArrayList<>()).add(game);
    }

    public void addMediumGame(UUID languageUUID, Game game) {
        mediumGames.computeIfAbsent(languageUUID, k -> new ArrayList<>()).add(game);
    }

    public void addHardGame(UUID languageUUID, Game game) {
        hardGames.computeIfAbsent(languageUUID, k -> new ArrayList<>()).add(game);
    }

    // Getters for games by difficulty
    public ArrayList<Game> getEasyGames(UUID languageUUID) {
        return easyGames.getOrDefault(languageUUID, new ArrayList<>());
    }

    public ArrayList<Game> getMediumGames(UUID languageUUID) {
        return mediumGames.getOrDefault(languageUUID, new ArrayList<>());
    }

    public ArrayList<Game> getHardGames(UUID languageUUID) {
        return hardGames.getOrDefault(languageUUID, new ArrayList<>());
    }

    // TextObject Management

    public TextObject findTextObject(UUID textObjectUUID) {
        return null;
    }

    public ArrayList<TextObject> getGameTextObjects(UUID gameUUID) {
        return null;
    }

    public TextObject getRandomTextObjectOfGame(UUID gameUUID) {
        return null;
    }

    // get the next text object in the list of text objects (for that game)
    public TextObject getNextTextObjectOfGame(UUID textObjectUUID) {
        TextObject t = findTextObject(textObjectUUID);
        ArrayList<TextObject> ts = getGameTextObjects(t.getGameUUID());


        return null;

    }


    // Question Mangament

    // Method to add a question to the question list for a specific game
    public void addQuestion(UUID gameUUID, Question question) {
        questions.computeIfAbsent(gameUUID, k -> new ArrayList<>()).add(question);
    }

    // Method to find a gameUUID 
    public UUID findGameUUIDByQuestionUUID(UUID questionUUID) {
        // Iterate over each entry in the questions map
        for (HashMap.Entry<UUID, ArrayList<Question>> entry : questions.entrySet()) {
            UUID gameUUID = entry.getKey();
            ArrayList<Question> questionList = entry.getValue();
    
            // Check if any question in the list has the matching UUID
            for (Question question : questionList) {
                if (question.getUUID().equals(questionUUID)) {
                    return gameUUID;  // Return the gameUUID where the questionUUID is found
                }
            }
        }
        return null;  // Return null if no matching gameUUID is found for the questionUUID
    }

    // Method to generate a question type
    public Question generateNewQuestion(UUID gameUUID, QuestionType questionType) {
        Question question;
        switch(questionType) {
            case FITB:

            case MATCHING:

            case SEQUENCING:

            case MULTIPLE_CHOICE:
            // Nothing will generate
        }
        return null;
    }

    public Question rebuildQuestion(UUID uuid, ArrayList<UUID> uuidArray, ArrayList<UUID> uuidArray2) {
        if(uuidArray.isEmpty()) {           // FITB Question

        } else {

        }
        return null;
    }

    // Getter for questions associated with a specific game
    public ArrayList<Question> getQuestions(UUID gameUUID) {
        return questions.getOrDefault(gameUUID, new ArrayList<>());
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append("\u001B[33m").append("GAME MANAGER TO STRING:").append("\u001B[0m\n");
    
        // Itterate through each language
        for (Language language : getLanguages()) {
            s.append(language.toString()).append("\n");
    
            s.append("Easy ").append(language.getLanguageName()).append(" games:\n");
            s.append(getEasyGames(language.getUUID())).append("\n");

            s.append("Medium ").append(language.getLanguageName()).append(" games:\n");
            s.append(getMediumGames(language.getUUID())).append("\n");

            s.append("Hard ").append(language.getLanguageName()).append(" games:\n");
            s.append(getHardGames(language.getUUID())).append("\n");
        }
    
        s.append("\u001B[33m").append("END OF GAME MANAGER TO STRING\n\n").append("\u001B[0m");
        return s.toString();
    }
    
}

