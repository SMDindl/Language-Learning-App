package com.learner.game.questions;

import java.util.ArrayList;
import java.util.UUID;

import com.learner.game.innerdata.TextObject;

public abstract class Question {
    protected UUID uuid;
    protected UUID gameUUID;
    protected UUID languageUUID;
    protected String questionText;
    protected QuestionType questionType;

    public Question(UUID uuid, UUID gameUUID, UUID languageUUID, String questionText, QuestionType questionType) {
        this.uuid = uuid;
        this.gameUUID = gameUUID;
        this.languageUUID = languageUUID;
        this.questionText = questionText;
        this.questionType = questionType;
    }

    public abstract void generateQuestion(ArrayList<TextObject> textObjects);

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
