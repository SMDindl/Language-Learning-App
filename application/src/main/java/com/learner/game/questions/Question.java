package com.learner.game.questions;

import java.util.UUID;

import com.learner.game.GameManager;

public abstract class Question {

    protected UUID uuid;
    protected UUID gameUUID;
    protected UUID languageUUID;
    protected QuestionType questionType;
    protected GameManager gameManager = GameManager.getInstance();

    protected String questionText; // Not assigned by constructor, generated differently per question type

    public Question(UUID uuid, QuestionType questionType) {
        this.uuid = uuid;
        this.questionType = questionType;
        
        if(questionType != QuestionType.MULTIPLE_CHOICE) {
            this.gameUUID = gameManager.findGameUUIDByQuestionOrTextObjectUUID(uuid); // Retrieve gameUUID and languageUUID based on the textObject UUID
        
            // Use the gameUUID to get the corresponding Game instance, then find its languageUUID
            if (gameUUID != null) {
                this.languageUUID = gameManager.findGameByUUID(gameUUID).getLanguageUUID();
            }
        }
    }

    public abstract void generateQuestion();

    public abstract boolean validateAnswer(String userAnswer);

    public UUID getUUID() {
        return uuid;
    }

    public UUID getGameUUID() {
        return gameUUID;
    }

    public UUID getLanguageUUID() {
        return languageUUID;
    }

    public String getQuestionText() {
        return questionText;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }
}
