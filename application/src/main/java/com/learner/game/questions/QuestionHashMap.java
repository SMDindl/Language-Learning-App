package com.learner.game.questions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class QuestionHashMap {

    private final HashMap<UUID, ArrayList<Question>> questionMap;

    public QuestionHashMap() {
        this.questionMap = new HashMap<>();
    }

    public void addQuestion(UUID languageUUID, Question question) {
        questionMap.computeIfAbsent(languageUUID, k -> new ArrayList<>()).add(question);
    }

    public void removeQuestion(UUID languageUUID, UUID questionUUID) {
        ArrayList<Question> questions = questionMap.get(languageUUID);
        if (questions != null) {
            questions.removeIf(question -> question.getUuid().equals(questionUUID));
        }
    }

    public ArrayList<Question> getQuestions(UUID languageUUID) {
        return questionMap.getOrDefault(languageUUID, new ArrayList<>());
    }

    public HashMap<UUID, ArrayList<Question>> getAllQuestions() {
        return questionMap;
    }
}
