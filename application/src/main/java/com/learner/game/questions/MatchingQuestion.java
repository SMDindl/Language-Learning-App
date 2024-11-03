package com.learner.game.questions;

import java.util.ArrayList;
import java.util.UUID;

import com.learner.game.innerdata.TextObject;

public class MatchingQuestion extends Question {
    
    private ArrayList<String> matches;

    public MatchingQuestion(UUID uuid, UUID gameUUID, UUID languageUUID, String questionText) {
        super(uuid, gameUUID, languageUUID, questionText, QuestionType.MATCHING);
        this.matches = new ArrayList<>();
    }

    @Override
    public void generateQuestion(ArrayList<TextObject> textObjects) {
        for (TextObject textObject : textObjects) {
            matches.add(textObject.getText());
        }
        this.questionText = "Match the words with their meanings.";
    }

    @Override
    public boolean validateAnswer(String userAnswer) {
        String[] pairs = userAnswer.split(", ");
        for (String pair : pairs) {
            String[] match = pair.split(":");
            if (match.length != 2 || !matches.contains(match[0].trim())) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<String> getMatches() {
        return matches;
    }
}
