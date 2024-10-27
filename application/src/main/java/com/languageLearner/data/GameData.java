package com.languageLearner.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class GameData {
    
    private static GameData instance;
    private HashMap<DataKey, ArrayList<Word>> wordsMap;
    private HashMap<DataKey, ArrayList<Question>> questionsMap;
    private HashMap<DataKey, ArrayList<Story>> storiesMap;
    private HashMap<DataKey, ArrayList<Letter>> lettersMap;

    // Singleton private constructor
    private GameData() {
        wordsMap = new HashMap<>();
        questionsMap = new HashMap<>();
        storiesMap = new HashMap<>();
        lettersMap = new HashMap<>();
    }

    // Singleton instance retrieval
    public static GameData getInstance() {
        if (instance == null) {
            instance = new GameData();
        }
        return instance;
    }

    // Methods to add data directly
    public void addWords(DataKey dataKey, ArrayList<Word> words) {
        wordsMap.put(dataKey, words);
    }

    public void addQuestions(DataKey dataKey, ArrayList<Question> questions) {
        questionsMap.put(dataKey, questions);
    }

    public void addStories(DataKey dataKey, ArrayList<Story> stories) {
        storiesMap.put(dataKey, stories);
    }

    public void addLetters(DataKey dataKey, ArrayList<Letter> letters) {
        lettersMap.put(dataKey, letters);
    }

        // Method to retrieve words for a specific DataKey
    public ArrayList<Word> getWords(DataKey dataKey) {
        return wordsMap.get(dataKey);
    }

    // Retrieve questions data for a specific DataKey
    public ArrayList<Question> getQuestions(DataKey dataKey) {
        return questionsMap.get(dataKey);
    }

    // Retrieve letters data for a specific DataKey
    public ArrayList<Letter> getLetters(DataKey dataKey) {
        return lettersMap.get(dataKey);
    }

    // Retrieve stories data for a specific DataKey
    public ArrayList<Story> getStories(DataKey dataKey) {
        return storiesMap.get(dataKey);
    }

    // Method to retrieve available games for a specific language and difficulty
    public Set<String> getAvailableGamesForDifficulty(DataKey dataKey) {
        Set<String> availableGames = new HashSet<>();

        if (wordsMap.containsKey(dataKey)) availableGames.add(DataConstants.COLORS_GAME);
        if (questionsMap.containsKey(dataKey)) availableGames.add(DataConstants.ALPHABET_GAME);
        if (storiesMap.containsKey(dataKey)) availableGames.add(DataConstants.STORIES_GAME);
        if (lettersMap.containsKey(dataKey)) availableGames.add(DataConstants.NUMBERS_GAME);

        return availableGames;
    }

    // Implementation to handle a list of words as a matching question
    public void doMatching(ArrayList<Word> words) { // Need to consider DataKey being used as well as it the list is nested within an object

    }
    
    // Implementation to handle a list of words as a fill in the blank question
    public void doFITB(ArrayList<Word> words) { // Need to consider DataKey being used as well as it the list is nested within an object
    
    } 

    // Method to populate game data
    public void populateData(HashMap<DataKey, ArrayList<Word>> words, 
                             HashMap<DataKey, ArrayList<Question>> questions,
                             HashMap<DataKey, ArrayList<Story>> stories, 
                             HashMap<DataKey, ArrayList<Letter>> letters) {
        this.wordsMap = words;
        this.questionsMap = questions;
        this.storiesMap = stories;
        this.lettersMap = letters;
    }
}