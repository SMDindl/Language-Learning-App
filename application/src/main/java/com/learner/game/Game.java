package com.learner.game;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import com.learner.game.innerdata.GameInfo;
import com.learner.game.innerdata.TextObject;
import com.learner.game.questions.Question;

public class Game {

    private final UUID languageUUID;
    private final String gameTitle;
    private final Difficulty difficulty;
    private final UUID uuid;            
    private final ArrayList<TextObject> textObjects; // Stores TextObject instances
    private final ArrayList<Question> questions;     // Stores Question instances
    private final GameInfo info;

    public Game(UUID languageUUID, String gameTitle, Difficulty difficulty, UUID uuid, GameInfo info, ArrayList<TextObject> textObjects, ArrayList<Question> questions) {
        this.languageUUID = languageUUID;
        this.gameTitle = gameTitle;
        this.difficulty = difficulty;
        this.uuid = uuid;
        this.info = info;
        this.textObjects = textObjects;
        this.questions = questions;
    }

    // Method to add a Question to the Game
    public void addQuestion(Question question) {
        questions.add(question);
    }

    // Method to retrieve all Questions in the Game
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    // Retrieve a specific Question by UUID
    public Question getQuestion(UUID questionUUID) {
        for (Question question : questions) {
            if (question.getUUID().equals(questionUUID)) {
                return question;
            }
        }
        return null;
    }

    // Retrieve all Question UUIDs
    public ArrayList<UUID> getQuestionUUIDs() {
        ArrayList<UUID> questionUUIDs = new ArrayList<>();
        for (Question question : questions) {
            questionUUIDs.add(question.getUUID());
        }
        return questionUUIDs;
    }

    // Getter for TextObjects
    public ArrayList<TextObject> getTextObjects() {
        return textObjects.isEmpty() ? null : textObjects;
    }

    // Retrieve a specific TextObject by UUID
    public TextObject getTextObject(UUID textObjectUUID) {
        return textObjects.stream().filter(t -> t.getUUID().equals(textObjectUUID)).findFirst().orElse(null);
    }

    // Retrieve a random TextObject
    public TextObject getRandomTextObject() {
        if (textObjects.isEmpty()) return null;
        Random random = new Random();
        return textObjects.get(random.nextInt(textObjects.size()));
    }

    // Retrieve the next TextObject based on a given UUID
    public TextObject getNextTextObject(UUID textObjectUUID) {
        int index = -1;
        for (int i = 0; i < textObjects.size(); i++) {
            if (textObjects.get(i).getUUID().equals(textObjectUUID)) {
                index = i;
                break;
            }
        }
        return index == -1 ? null : textObjects.get((index + 1) % textObjects.size());
    }

    // Getters for other attributes
    public UUID getLanguageUUID() { return languageUUID; }
    public String getGameTitle() { return gameTitle; }
    public Difficulty getDifficulty() { return difficulty; }
    public UUID getUUID() { return uuid; }
    public GameInfo getInfo() { return info; }

    // toString method for debugging
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Game Details ===\n")
          .append("Title: ").append(gameTitle).append("\n")
          .append("Language UUID: ").append(languageUUID).append("\n")
          .append("Difficulty: ").append(difficulty).append("\n")
          .append("Game UUID: ").append(uuid).append("\n")
          .append("Info: ").append(info != null ? info.toString() : "No Info Available").append("\n")
          .append("TextObjects: ").append(textObjects.isEmpty() ? "No TextObjects" : textObjects.size()).append("\n")
          .append("Questions: ").append(questions.isEmpty() ? "No Questions" : questions.size()).append("\n");
        return sb.toString();
    }
}
