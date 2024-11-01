package com.learner.game.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

public class MatchingQuestion extends Question {

    private final ArrayList<String> options;

    public MatchingQuestion(UUID uuid, UUID gameUUID, UUID languageUUID, String text, ArrayList<String> options) {
        super(uuid, gameUUID, languageUUID, text);
        this.options = options;
        this.questionType = QuestionType.MATCHING;
        shuffleOptions();
    }

    private void shuffleOptions() {
        Collections.shuffle(options);
    }

    @Override
    public boolean validateAnswer(Object userAnswer) {
        HashSet<String> correctPairSet = new HashSet<>(options);
        HashSet<String> userPairSet = new HashSet<>(Arrays.asList(userAnswer.toString().split(",")));
        return userPairSet.equals(correctPairSet);
    }

    public ArrayList<String> getOptions() { return options; }
}
