import java.util.ArrayList;

public class Question {
    // Attributes
    private String questionText;
    private int correctAnswerIndex;
    private ArrayList<String> answers;

    // Constructor
    public Question(String questionText, int correctAnswerIndex, ArrayList<String> answers) {
        this.questionText = questionText;
        this.correctAnswerIndex = correctAnswerIndex;
        this.answers = answers;
    }

    // Methods
    public String getQuestionText() {
        return questionText;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public String getCorrectAnswer() {
        if (correctAnswerIndex >= 0 && correctAnswerIndex < answers.size()) {
            return answers.get(correctAnswerIndex);
        } else {
            return "Invalid answer index";
        }
    }

    // Additional method to display the question and its answers
    public String displayQuestion() {
        StringBuilder questionDisplay = new StringBuilder("Question: " + questionText + "\nAnswers:\n");
        for (int i = 0; i < answers.size(); i++) {
            questionDisplay.append(i + 1).append(". ").append(answers.get(i)).append("\n");
        }
        return questionDisplay.toString();
    }
}
