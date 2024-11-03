package com.learner.game.questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

import com.learner.game.innerdata.TextObject;

public class MultipleChoiceQuestion extends Question {

    private final ArrayList<String> options;
    private int correctAnswerIndex;

    public MultipleChoiceQuestion(UUID uuid, UUID gameUUID, UUID languageUUID, String text, ArrayList<String> options) {
        super(uuid, gameUUID, languageUUID, text, QuestionType.MULTIPLE_CHOICE);
        this.options = new ArrayList<>(options); 
        this.correctAnswerIndex = 0;  
        shuffleOptions();
    }

    // Shuffle options while keeping track of the correct answer index
    private void shuffleOptions() {
        String correctAnswer = options.get(correctAnswerIndex); // Store the original correct answer
        Collections.shuffle(options); // Shuffle the options
        correctAnswerIndex = options.indexOf(correctAnswer); // Update index of the correct answer after shuffling
    }
    
    // Validate based on userAnswer as a String, assuming it’s the option text
    @Override
    public boolean validateAnswer(String userAnswer) {
        return options.get(correctAnswerIndex).equalsIgnoreCase(userAnswer.trim());
    }

    public void askQuestion() {
        System.out.println(getQuestionText());
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ": " + options.get(i)); // Display options with indices starting from 1
        }
    }

    public ArrayList<String> getOptions() {
        return new ArrayList<>(options); // Return a copy to prevent external modification
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    // Implement generateQuestion as a no-op to satisfy the abstract method
    @Override
    public void generateQuestion(ArrayList<TextObject> textObjects) {
        // No operation needed for MultipleChoiceQuestion since options are directly loaded from JSON
    }
}
