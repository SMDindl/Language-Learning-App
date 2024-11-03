package com.learner.game.questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;
import com.learner.game.innerdata.TextObject;

/**
 * SequencingQuestion presents a list of items in a randomized order
 * and asks the user to arrange them in the correct sequence.
 * The correct order is stored internally and used for validation.
 */
public class SequencingQuestion extends Question {

    private final ArrayList<String> sequence;         // Shuffled sequence shown to the user
    private final ArrayList<String> correctSequence;  // The correct order of items

    /**
     * Constructs a SequencingQuestion with a unique identifier, game and language context,
     * question text, and type set to SEQUENCING.
     * 
     * @param uuid        Unique identifier for the question
     * @param gameUUID    Unique identifier for the game
     * @param languageUUID Unique identifier for the language
     * @param questionText Initial question text
     */
    public SequencingQuestion(UUID uuid, UUID gameUUID, UUID languageUUID, String questionText) {
        super(uuid, gameUUID, languageUUID, questionText, QuestionType.SEQUENCING);
        this.sequence = new ArrayList<>();
        this.correctSequence = new ArrayList<>();
    }

    /**
     * Generates the question by setting up the items to be sequenced.
     * The correct order is stored in correctSequence, and a shuffled
     * version is presented to the user as sequence.
     * 
     * @param textObjects List of TextObject items representing the sequence items.
     */
    @Override
    public void generateQuestion(ArrayList<TextObject> textObjects) {
        // Populate sequence and correctSequence with the items in the correct order
        for (TextObject textObject : textObjects) {
            String item = textObject.getText();
            sequence.add(item);
            correctSequence.add(item); // Save the correct order
        }

        // Shuffle sequence for display
        Collections.shuffle(sequence);

        // Build question text to display the shuffled sequence
        StringBuilder questionBuilder = new StringBuilder("Arrange the following items in the correct order:\n");
        for (int i = 0; i < sequence.size(); i++) {
            questionBuilder.append(i + 1).append(". ").append(sequence.get(i)).append("\n");
        }

        this.questionText = questionBuilder.toString();
    }

    /**
     * Validates the user's answer by comparing it with the correct sequence.
     * 
     * @param userAnswer The answer provided by the user, expected in a comma-separated
     *                   format, e.g., "Apple, Banana, Cherry".
     * @return true if the user's answer matches the correct sequence, false otherwise.
     */
    @Override
    public boolean validateAnswer(String userAnswer) {
        // Split userAnswer assuming it’s provided as a comma-separated sequence of words
        String[] userSequence = userAnswer.split(", ");
        if (userSequence.length != correctSequence.size()) {
            return false;
        }

        // Compare each item in the user's sequence with the correct sequence
        for (int i = 0; i < correctSequence.size(); i++) {
            if (!correctSequence.get(i).equalsIgnoreCase(userSequence[i].trim())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets the shuffled sequence displayed to the user.
     * 
     * @return The randomized sequence of items.
     */
    public ArrayList<String> getSequence() {
        return sequence;
    }

    /**
     * Gets the correct sequence of items.
     * 
     * @return The correct order of items in the sequence.
     */
    public ArrayList<String> getCorrectSequence() {
        return correctSequence;
    }
}
