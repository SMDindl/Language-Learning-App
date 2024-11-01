package com.learner.game.questions;

import java.util.UUID;

public abstract class Question {
    
    protected UUID uuid;
    protected UUID gameUUID;
    protected UUID languageUUID;
    protected String text;
    protected QuestionType questionType;

    public Question(UUID uuid, UUID gameUUID, UUID languageUUID, String text) {
        this.uuid = (uuid != null) ? uuid : UUID.randomUUID();
        this.languageUUID = languageUUID;
        this.gameUUID = gameUUID;
        this.text = text;
    }

    public abstract boolean validateAnswer(Object userAnswer);

    public UUID getUuid() { return uuid; }
    public UUID getGameUUID() { return gameUUID; }
    public UUID getLanguageUUID() { return languageUUID; }
    public String getText() { return text; }

    public void askQuestion() {
        System.out.println(text);
    }

    public void provideFeedback(boolean isCorrect) {
        System.out.println(isCorrect ? "Correct!" : "Incorrect.");
    }
}
