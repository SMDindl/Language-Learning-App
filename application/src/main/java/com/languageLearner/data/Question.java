package com.languageLearner.data;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents a multiple-choice question in the language learning application.
 */
public class Question {
    private String questionText;
    private ArrayList<String> answers;
    private int correctAnswerIndex;
    private String image;
    private DataKey dataKey;
    private String categoryId;
    private UUID uuid;

    /**
     * Constructs a Question with all fields, including optional image and categoryId.
     *
     * @param questionText       The text of the question.
     * @param answers            The list of possible answers.
     * @param correctAnswerIndex The index of the correct answer in the answers list.
     * @param dataKey            The DataKey for the question's game type, language, and difficulty.
     * @param categoryId         The specific category identifier (e.g., a letter or story).
     * @param image              The image associated with the question, if any.
     * @param uuid               The unique identifier for the question.
     */
    public Question(String questionText, ArrayList<String> answers, int correctAnswerIndex,
                    DataKey dataKey, String categoryId, String image, UUID uuid) {
        this.questionText = questionText;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
        this.dataKey = dataKey;
        this.categoryId = categoryId;
        this.image = image;
        this.uuid = uuid;
    }

    /**
     * Constructs a Question without an image.
     *
     * @param questionText       The text of the question.
     * @param answers            The list of possible answers.
     * @param correctAnswerIndex The index of the correct answer in the answers list.
     * @param dataKey            The DataKey for the question's game type, language, and difficulty.
     * @param categoryId         The specific category identifier.
     * @param uuid               The unique identifier for the question.
     */
    public Question(String questionText, ArrayList<String> answers, int correctAnswerIndex,
                    DataKey dataKey, String categoryId, UUID uuid) {
        this(questionText, answers, correctAnswerIndex, dataKey, categoryId, null, uuid);
    }

    /**
     * Constructs a Question without a categoryId or image.
     *
     * @param questionText       The text of the question.
     * @param answers            The list of possible answers.
     * @param correctAnswerIndex The index of the correct answer in the answers list.
     * @param dataKey            The DataKey for the question's game type, language, and difficulty.
     * @param uuid               The unique identifier for the question.
     */
    public Question(String questionText, ArrayList<String> answers, int correctAnswerIndex,
                    DataKey dataKey, UUID uuid) {
        this(questionText, answers, correctAnswerIndex, dataKey, null, null, uuid);
    }

    /**
     * Constructs a Question without a dataKey, categoryId, or image.
     *
     * @param questionText       The text of the question.
     * @param answers            The list of possible answers.
     * @param correctAnswerIndex The index of the correct answer in the answers list.
     * @param uuid               The unique identifier for the question.
     */
    public Question(String questionText, ArrayList<String> answers, int correctAnswerIndex, UUID uuid) {
        this(questionText, answers, correctAnswerIndex, null, null, null, uuid);
    }

    /**
     * Gets the question text.
     *
     * @return The text of the question.
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Gets the list of possible answers.
     *
     * @return The list of answers.
     */
    public ArrayList<String> getAnswers() {
        return answers;
    }

    /**
     * Gets the index of the correct answer in the answers list.
     *
     * @return The index of the correct answer.
     */
    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    /**
     * Gets the correct answer text.
     *
     * @return The correct answer.
     */
    public String getCorrectAnswer() {
        return answers.get(correctAnswerIndex);
    }

    /**
     * Gets the image associated with the question, if any.
     *
     * @return The image URL or path, or null if not available.
     */
    public String getImage() {
        return image;
    }

    /**
     * Gets the DataKey for the question, indicating language, game type, and difficulty.
     *
     * @return The DataKey.
     */
    public DataKey getDataKey() {
        return dataKey;
    }

    /**
     * Gets the category identifier for the question.
     *
     * @return The categoryId, or null if not available.
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * Gets the unique identifier (UUID) for the question.
     *
     * @return The UUID.
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Validates if the provided answer index matches the correct answer.
     *
     * @param answerIndex The index of the user's selected answer.
     * @return True if the answer is correct, false otherwise.
     */
    public boolean validateAnswer(int answerIndex) {
        return answerIndex == correctAnswerIndex;
    }

    /**
     * Formats and displays the question and its answers.
     *
     * @return The formatted question with possible answers.
     */
    public String displayQuestion() {
        StringBuilder questionDisplay = new StringBuilder("\nQuestion: " + questionText + "\nAnswers:\n");
        for (int i = 0; i < answers.size(); i++) {
            questionDisplay.append(i + 1).append(". ").append(answers.get(i)).append("\n");
        }
        return questionDisplay.toString();
    }
}
