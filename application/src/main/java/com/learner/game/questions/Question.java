package com.learner.game.questions;

import java.util.UUID;

public abstract class Question {
    
    protected UUID uuid;
    protected UUID gameUUID;
    protected String text;
    protected QuestionType questionType;

    public Question(UUID uuid, UUID gameUUID, String text) {
        this.uuid = (uuid != null) ? uuid : UUID.randomUUID();
        this.gameUUID = gameUUID;
        this.text = text;
    }

    public abstract boolean validateAnswer(Object userAnswer);

    public UUID getUuid() { return uuid; }
    public UUID getGameUuid() { return gameUUID; }
    public String getText() { return text; }

    public void askQuestion() {
        System.out.println(text);
    }

    public void provideFeedback(boolean isCorrect) {
        System.out.println(isCorrect ? "Correct!" : "Incorrect.");
    }
}
