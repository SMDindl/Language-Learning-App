package com.learner.game.questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

import com.learner.game.Game;
import com.learner.game.innerdata.TextObject;

/**
 * MatchingQuestion presents a list of items on the left and a randomized list
 * of matching options on the right. The user is asked to match each item on
 * the left with the correct option on the right.
 */
public class MatchingQuestion extends Question {

    private final ArrayList<String> leftSide;        // The words to match
    private final ArrayList<String> rightSide;       // The meanings, shuffled for display
    private final HashMap<String, String> correctPairs; // Maps each word to its correct meaning

    /**
     * Constructs a MatchingQuestion with a unique identifier, game and language context,
     * question text, and type set to MATCHING.
     * 
     * @param uuid        Unique identifier for the question
     * @param gameUUID    Unique identifier for the game
     * @param languageUUID Unique identifier for the language
     * @param questionText Initial question text
     */
    public MatchingQuestion(UUID uuid) {
        super(uuid, QuestionType.MATCHING);
        this.leftSide = new ArrayList<>();
        this.rightSide = new ArrayList<>();
        this.correctPairs = new HashMap<>();
    }

    /**
     * Generates the question by populating the left and right lists of items.
     * The correct pairs are stored in correctPairs, and rightSide is shuffled for display.
     * 
     * @param textObjects List of TextObject items representing the matching pairs.
     */
    @Override
    public void generateQuestion() {
        ArrayList<TextObject> textObjects = new ArrayList<>();                        // Create ArrayList of textObjects
        TextObject theTextObject = gameManager.findTextObjectByUUID(this.getUUID()); // Retrieve the TextObject using the uuid
        textObjects.add(theTextObject);                                             // add textObject to arrayList
        Game game = gameManager.findGameByUUID(gameUUID);                          // Retrive the game

        // Establish an ArrayList of textObjects
        for(int i = 0; i < 3; i++) {
            theTextObject = game.getNextTextObject(theTextObject.getUUID());
            textObjects.add(theTextObject);
        }

        // Populate leftSide, rightSide, and correctPairs
        for (TextObject textObject : textObjects) {
            String word = textObject.getText();
            String meaning = textObject.getEnglishText();

            leftSide.add(word);      // Add the word to the left side
            rightSide.add(meaning);  // Add the meaning to the right side
            correctPairs.put(word, meaning);  // Store the correct pair
        }

        // Shuffle rightSide to display options in a random order
        Collections.shuffle(rightSide);

        // Build the question text with shuffled options
        StringBuilder questionBuilder = new StringBuilder("Match each word with its correct meaning:\n");
        for (int i = 0; i < leftSide.size(); i++) {
            questionBuilder.append(i + 1).append(". ").append(leftSide.get(i)).append("\n");
        }
        questionBuilder.append("\nOptions:\n");
        for (int i = 0; i < rightSide.size(); i++) {
            questionBuilder.append((char)('A' + i)).append(". ").append(rightSide.get(i)).append("\n");
        }

        this.questionText = questionBuilder.toString();
    }

    /**
     * Validates the user's answer by comparing it to the correct pairs.
     * 
     * @param userAnswer The answer provided by the user, expected in a format where
     *                   each item is matched with a letter, e.g., "1:B, 2:A, 3:C".
     * @return true if the user's answer matches the correct pairs, false otherwise.
     */
    @Override
    public boolean validateAnswer(String userAnswer) {
        // Expected format: "1:A, 2:B, 3:C", where number matches leftSide and letter matches rightSide
        String[] pairs = userAnswer.split(", ");
        if (pairs.length != leftSide.size()) {
            return false;
        }

        for (String pair : pairs) {
            String[] match = pair.split(":");
            if (match.length != 2) {
                return false; // Invalid format if it’s not in "1:A" format
            }

            try {
                // Convert the left index from user input
                int leftIndex = Integer.parseInt(match[0].trim()) - 1;
                if (leftIndex < 0 || leftIndex >= leftSide.size()) {
                    return false;
                }

                // Convert the right letter to index
                char rightOption = match[1].trim().toUpperCase().charAt(0);
                int rightIndex = rightOption - 'A';
                if (rightIndex < 0 || rightIndex >= rightSide.size()) {
                    return false;
                }

                // Get the word and selected meaning from leftSide and shuffled rightSide
                String word = leftSide.get(leftIndex);
                String selectedMeaning = rightSide.get(rightIndex);

                // Check if the selected meaning matches the correct one in correctPairs
                if (!correctPairs.get(word).equalsIgnoreCase(selectedMeaning)) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false; // Invalid number format in user input
            }
        }

        return true; // All pairs matched correctly
    }

    /**
     * Gets the list of items on the left side (e.g., words to match).
     * 
     * @return The left-side items in the question.
     */
    public ArrayList<String> getLeftSide() {
        return leftSide;
    }

    /**
     * Gets the list of items on the right side (e.g., meanings or translations).
     * 
     * @return The right-side items in the question, in randomized order.
     */
    public ArrayList<String> getRightSide() {
        return rightSide;
    }
}
